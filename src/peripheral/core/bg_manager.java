package peripheral.core;

import peripheral.configuration.configuration_universal;
import peripheral.logs.debug;
import peripheral.manager.main_controller;

public class bg_manager extends peripheral
{

  public bg_manager(String com_port_name, String baud_rate, String data_bits, String stop_bits, String parity, boolean is_serial_printer)
  {
    super(com_port_name, baud_rate, data_bits, stop_bits, parity, is_serial_printer, false);
    wait_for_answer();
  }

  @Override
  protected boolean is_status_ok()
  {
    return true;
  }

  boolean send_first_command = true;
  
  public void wait_for_answer()
  {
    new Thread(new Runnable()
    {

      @Override
      public void run()
      {
        if (comport_instance != null)
        {
          while (true)
          {
            try
            {
              if (comport_instance.isDSR() && comport_instance.isCTS())
              {
                if(send_first_command)
                {
                  comport_instance.set_pectab(configuration_universal.INITIAL_COMMAND_1);
                  comport_instance.set_pectab(configuration_universal.INITIAL_COMMAND_2);
                  Thread.sleep(100);
                  comport_instance.clean();
                  send_first_command = false;
                }
                String data_received = comport_instance.get_available_data();
                if (!data_received.equals("") && !data_received.contains(configuration_universal.SUBSTRING_BGR_ANSWER))
                {
                  main_controller.peripheral_manager_view_instance.text_area_data_received_unique.setText(data_received + "\n" + main_controller.peripheral_manager_view_instance.text_area_data_received_unique.getText());
                  comport_instance.set_pectab(configuration_universal.COMMAND_AFTER_ANSWER);
                  Thread.sleep(100);
                  comport_instance.clean();
                }
                else
                {
                  debug.set_debug("data_received: [" + data_received + "]");
                  comport_instance.clean();
                }
              }
              else
              {
                send_first_command = true;
              }

              Thread.sleep(configuration_universal.SLEEP_THREAD_BG);
            }
            catch (InterruptedException ex)
            {
              debug.set_debug(ex.getMessage());
            }
          }
        }
      }
    }).start();

  }
}
