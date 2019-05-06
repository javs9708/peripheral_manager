package peripheral.manager;

import java.io.File;
import javax.swing.ImageIcon;
import peripheral.core.bc_manager;
import views.peripheral_manager_view;

public class main_controller 
{
	bc_manager bc_manager_instance;
        peripheral_manager_view peripheral_manager_view_instance;
        
        File fichero     = new File(".");
        
        ImageIcon red_error = new ImageIcon(fichero.getAbsolutePath()+"/build/classes/images/red_error.png");
        
	
	public main_controller()
	{
		//TODO start perripherals
		bc_manager_instance = new bc_manager("COM5", "19200", "8", "1", "0", true);
                
                peripheral_manager_view_instance = new peripheral_manager_view(this);
                peripheral_manager_view_instance.setVisible(true);
                
                if(!bc_manager_instance.j_comport_instance.com_port_opened){
                    peripheral_manager_view_instance.status_label.setText("NOT AVAILABLE");
                    peripheral_manager_view_instance.check_icon.setIcon(red_error);
                    
                }
                
		
                
		
	}
}
