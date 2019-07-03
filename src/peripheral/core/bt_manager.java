package peripheral.core;

import peripheral.configuration.configuration_universal;
import peripheral.logs.debug;

public class bt_manager extends peripheral
{

  public String header_test_pectab = "BTT0102~J 540270=#01B1 1112063441#02B1 K101433441#03B1 1312063441#04B1 K309453441#05B1 1036110541#06B1 1020110541#07B1 1003110541#08B1 5394460541#09C0 1234070202#10C0 1228080201#11C0 1220080201#12C0 1213080201#13C0 J216390402#14C0 1208080201#15C0 1198080202#16C0 1010030201#17C0 1010290201#22C0 1054130202#23C0 5353390202#24C0 1178090201#25C0 1167130606#26C0 1162100202#33C0 5248450201#34C0 5260440606#35C0 5263440202#42C0 1043030201#43C0 1028020201#45C0 5365470202#46C0 5369470201#47C0 5372360201#48C0 5372470201#50C0 5376470201#51C0 5383470201#52C0 5379470201#53C0 5387470201#54C0 5397430202#55C0 1043280201#56C0 1028260201#60C0 5381180202#61C0 1038010201#62C0 1021010201#63C0 1005010201#";
  public String body_test_pectab = "BTP010201#010000000224#020000000224#030000000224#040000000224#050000000224#060000000224#070000000224#080000000224#09EASYJET#10ALDUS/COREYMR#11OSC1#1205OCT#13134#14WEIGHT: 0KG#150000 U2 000224#160000 U2 000224#17EZY5680/TXL#220000000224#230000000224#24TO#25TXL#26EZY5680#33TO#34TXL#35EZY5680#420000 U2 000224#430000 U2 000224#45EASYJET#46ALDUS/COREYMR#47OSC1#4805OCT#50BERLIN TEGEL#51TXL  EZY5680#52  #53  #540000 U2 000224#55EZY5680/TXL#56EZY5680/TXL#60134#6105OCT#6205OCT#6305OCT#";

  public bt_manager(String com_port_name, String baud_rate, String data_bits, String stop_bits, String parity, boolean is_serial_printer)
  {
    super(com_port_name, baud_rate, data_bits, stop_bits, parity, is_serial_printer, false);
  }

  
  private boolean waiting_for_status = false;
  private boolean last_boolean_status = false;

  @Override
  protected boolean is_status_ok()
  {
    if (waiting_for_status == false)
    {
      waiting_for_status = true;

      String[] answer = comport_instance.set_pectab("QS").split(";");

      if (answer.length >= 6)
      {
        String sub_3 = answer[3];
        String sub_4 = answer[4];
        String sub_6 = answer[6];
        debug.set_debug("ATB answer[3]: " + sub_3);
        debug.set_debug("ATB answer[4]: " + sub_4);
        debug.set_debug("ATB answer[6]: " + sub_6);

        if ((sub_3.equals("1") && (sub_4.equals("1") || sub_4.equals("0")) && sub_6.equals("0")))
        {
          var_last_status = configuration_universal.PRINTER_OK;
          debug.set_debug("Status: " + var_last_status + "BT");
          waiting_for_status = false;
          last_boolean_status = true;
          return true;
        }
        else
        {
          // cover open
          if (sub_3.equals("0"))
          {
            var_last_status = configuration_universal.COVER_OPEN;
          }
          // paper out
          else if (sub_6.equals("2"))
          {
            var_last_status = configuration_universal.PAPER_OUT;
          }
          else
          {
            var_last_status = configuration_universal.PAPER_JAM;
          }
          debug.set_debug("Status: " + var_last_status + "BT");
          waiting_for_status = false;
          last_boolean_status = false;
          return false;
        }
      }
      else
      {
        var_last_status = configuration_universal.CONNECTION_ERROR;
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
