package peripheral.core;

public class oc_manager extends peripheral
{

  public oc_manager(String com_port_name, String baud_rate, String data_bits, String stop_bits, String parity, boolean is_serial_printer)
  {
    super(com_port_name, baud_rate, data_bits, stop_bits, parity, is_serial_printer);
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
          j_comport_instance.get_available_data();
        }
      }
    }).start();

  }

}
