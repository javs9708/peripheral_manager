package peripheral.core;

import peripheral.configuration.configuration_universal;
import peripheral.logs.debug;
import peripheral.manager.main_controller;

public class bc_manager extends peripheral
{

    public bc_manager(String com_port_name, String baud_rate, String data_bits, String stop_bits, String parity, boolean is_serial_printer)
    {
        super(com_port_name, baud_rate, data_bits, stop_bits, parity, is_serial_printer, false);
        comport_instance.set_dtr();
        comport_instance.set_rts();
        wait_for_answer();
    }
	
  @Override
  protected boolean is_status_ok()
  {
    return true;
  }
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
              if (comport_instance.is_open() == configuration_universal.DEVICE_OK)
              {
                String data_received = comport_instance.get_available_data();
                if (!data_received.equals(""))
                {
                  main_controller.peripheral_manager_view_instance.text_area_data_received_unique.setText(data_received + "\n" + main_controller.peripheral_manager_view_instance.text_area_data_received_unique.getText());
                  comport_instance.clean();
                }
              }
              Thread.sleep(configuration_universal.SLEEP_THREAD_BC);
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

  @Override
  public boolean is_available()
  {
    if (!waiting_for_is_available)
    {
      waiting_for_is_available = true;

      if (comport_instance.is_open() == configuration_universal.DEVICE_OK && is_status_ok() && comport_instance.isCTS() && comport_instance.isDSR())
      {
        
        waiting_for_is_available = false;
        last_available_answer = true;
        return true;
      }
      else
      {
        waiting_for_is_available = false;
        last_available_answer = false;
        return false;
      }
    }
    else
    {
      debug.set_debug("Busy waiting for is available");
      return last_available_answer;
    }
  }
  
}
	
	

