package peripheral.core;

import peripheral.configuration.configuration_universal;
import peripheral.logs.debug;

public class bp_manager extends peripheral
{

  public String header_test_pectab = "PT##?W8B#@;#T//#T//CI#T//CI#0101110112011301210122012301310132013301410142014301C#0401N02#0525A02D#0619E07E54D#0725G07G54Q#0803M02D#0906B54C04D#0B02D#0C05A54C20D#0D05F61G36D#0E18H07I54Q#0F12N20N54D#1004C16C59B#1105C64F07D#1204B59C11D#1304B64F02D#1403F54G02D#1503H54H02D#1617N02M54D#1704D54E02D#1813K02D#1935K16D#1A06H36H61D#1B35O02D#1C35P02D#1D20N011129D#1E13A15D#20BSB441041#2104N36O62H#2206I02K54D#2310I09K61Q#2403O36N61D";
  public String body_test_pectab = "CP#1C01#01W#05INK#06JHON SMITH#07LON#08#09INK 01#0B #0C12020#0D12:15#0ELON#0F01#1002#1111#12GATE#13SEAT#14DEP#15ARR#16 #17NAME#18BOARDING TIME#1930 min prior to departure#1A#1BBOARDING PASS MUST BE PRESENTED    #1CAT GATE#1D123456789#1EBOARDING PASS#20123456789#21#22#23#2401";

  public bp_manager(String com_port_name, String baud_rate, String data_bits, String stop_bits, String parity, boolean is_serial_printer)
  {
    super(com_port_name, baud_rate, data_bits, stop_bits, parity, is_serial_printer);
  }



  private boolean waiting_for_status = false;
  private boolean last_boolean_status = false;
  
  @Override
  protected boolean is_status_ok()
  {
    if (waiting_for_status == false)
    {
      waiting_for_status = true;
      String[] answer = j_comport_instance.set_pectab("QS").split(";");

      if (answer.length >= 6)
      {
        String sub_3 = answer[3];
        String sub_4 = answer[4];
        String sub_6 = answer[6];
        debug.set_debug("ATB answer[3]: " + sub_3);
        debug.set_debug("ATB answer[4]: " + sub_4);
        debug.set_debug("ATB answer[6]: " + sub_6);

        if (sub_3.equals("1") && sub_6.equals("0"))
        {
          var_last_status = configuration_universal.PRINTER_OK;
          debug.set_debug("Status: " + var_last_status + " BP");
          waiting_for_status = false;
          last_boolean_status = true;
          return true;
        }
        else
        {
          // Paper jam
          if (sub_3.equals("1") && sub_4.equals("0") && sub_6.equals("2"))
          {
            var_last_status = configuration_universal.PAPER_OUT;
          }
          else if (sub_3.equals("0") && (sub_4.equals("1") || sub_4.equals("0")) && (sub_6.equals("0") || sub_6.equals("2"))) // cover open
          {
            var_last_status = configuration_universal.COVER_OPEN;
          }
          else if (sub_3.equals("1") && sub_4.equals("1") && sub_6.equals("2"))// paper out
          {
            var_last_status = configuration_universal.PAPER_JAM;
          }
          else
          {
            var_last_status = configuration_universal.CONNECTION_ERROR;
          }

          debug.set_debug("Status: " + var_last_status + " BP");

          waiting_for_status = false;
          last_boolean_status = false;
          return false;
        }
      }
      else
      {
        var_last_status = configuration_universal.CONNECTION_ERROR;
        debug.set_debug("Status: " + var_last_status + " BP");
        waiting_for_status = false;
        last_boolean_status = false;
        return false;
      }
    }
    else
    {
      return last_boolean_status;
    }
  }
}
