package peripheral.manager;

import peripheral.core.bc_manager;

public class main_controller 
{
	bc_manager bc_manager_instance;
	
	public main_controller()
	{
		//TODO start perripherals
		bc_manager_instance = new bc_manager("COM5", "19200", "8", "1", "0", true);
		
		//TODO start user interface
		
	}
}
