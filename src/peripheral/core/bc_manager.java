package peripheral.core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import peripheral.serial.j_com_port;

public class bc_manager{

	public j_com_port j_comport_instance;
	public bc_manager(String com_port_name, String baud_rate, String data_bits, String stop_bits, String parity, boolean is_serial_printer)
	{
		
		j_comport_instance = new j_com_port();
		j_comport_instance.open_port(com_port_name, baud_rate, data_bits, stop_bits, parity, is_serial_printer);
		
		j_comport_instance.is_cover_open();
		
		wait_for_answer();
	}
	
	
	public void wait_for_answer()
	{
                new Thread(new Runnable() {
		
		@Override
		public void run() {
		       
                while(true)
                    {
			j_comport_instance.get_available_data();
			//TODO call comport to check any data
			//TODO report to user interface any answer
			
                    }               
                } 		
                }).start();
		
	}
	
	
}
