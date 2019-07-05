package peripheral.core;

public class scale_manager extends peripheral
{

  public scale_manager(String com_port_name, String baud_rate, String data_bits, String stop_bits, String parity, boolean is_serial_printer)
  {
    super(com_port_name, baud_rate, data_bits, stop_bits, parity, is_serial_printer, false);
  }

  
  
  public void wait_for_answer()
  {
    new Thread(new Runnable()
    {

      @Override
      public void run()
      {

        while (true)
        {
          comport_instance.get_available_data();
        }
      }
    }).start();

  }

}
