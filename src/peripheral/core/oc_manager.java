package peripheral.core;

public class oc_manager {

	public oc_manager(String com_port_name, String baud_rate, String data_bits, String stop_bits, String parity, boolean is_serial_printer)
	{
		//TODO create new comport(...)
		wait_for_answer();
	}
	
	
	public void wait_for_answer()
	{
		//TODO call comport to check any data
		//TODO report to user interface any answer
	}
	
	
}
