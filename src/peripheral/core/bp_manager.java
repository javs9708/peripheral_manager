package peripheral.core;

import com.fazecast.jSerialComm.SerialPort;
import peripheral.logs.debug;
import peripheral.serial.j_com_port;
import static peripheral.serial.j_com_port.get_comport;

public class bp_manager {

	public j_com_port j_comport_instance;
        public boolean isOpenedBp = false;
        public String com_port    = "";
	public bp_manager(String com_port_name, String baud_rate, String data_bits, String stop_bits, String parity, boolean is_serial_printer)
	{
		
		j_comport_instance = new j_com_port();
		j_comport_instance.open_port(com_port_name, baud_rate, data_bits, stop_bits, parity, is_serial_printer);
		com_port = com_port_name;
		j_comport_instance.is_cover_open();
		
		wait_for_answer();
	}
	
        public void port_available()
        {
              SerialPort serial_port = null;
              serial_port = get_comport(com_port);
              System.out.println(com_port + " Present");
              
              
              if (serial_port != null)
              {
                isOpenedBp=true;
                try
                {
                  System.out.println(com_port + " isOpened " + isOpenedBp);
                  if (!isOpenedBp)
                  {
                    boolean openedSuccessfully = serial_port.openPort();
                    System.out.println("\nOpening " + serial_port.getSystemPortName() + ": " + serial_port.getDescriptivePortName() + " - " + serial_port.getPortDescription() + ": " + openedSuccessfully);
                    if (openedSuccessfully)
                    {
                      isOpenedBp = true;
                      System.out.println("Serial port opened.");
                      serial_port.setBaudRate(19200);                   
                    }
                  } else
                  {
                    System.out.println(com_port + " is Open");
                  }

                } catch (Exception e)
                {
                  System.out.println("\nSerial port NOT opened, Exception: " + e.getMessage());
                }
              } else
              {
                System.out.println(com_port + "=NULL NOT Present");
                isOpenedBp = false;
              }

              try
              {
                Thread.sleep(2000);
              } catch (InterruptedException e)
              {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }    
        }
        
	
	public void wait_for_answer()
	{
                new Thread(new Runnable() {
		
		@Override
		public void run() {
		       
                while(true)
                    {
			j_comport_instance.get_available_data();
			port_available();
			
                    }               
                } 		
                }).start();
		
	}
	
	
	
	
}
