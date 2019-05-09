package peripheral.manager;

import com.fazecast.jSerialComm.SerialPort;
import java.io.File;
import javax.swing.ImageIcon;
import peripheral.core.bc_manager;
import peripheral.core.bg_manager;
import peripheral.core.bp_manager;
import peripheral.core.bt_manager;
import peripheral.core.oc_manager;
import views.peripheral_manager_view;

public class main_controller 
{
	bc_manager bc_manager_instance;
        bt_manager bt_manager_instance;
        bp_manager bp_manager_instance;
        bg_manager bg_manager_instance;
        oc_manager oc_manager_instance;
        
        peripheral_manager_view peripheral_manager_view_instance;
        
        File fichero     = new File(".");
        
        ImageIcon red_error = new ImageIcon(fichero.getAbsolutePath()+"/build/classes/images/red_error.png");
        ImageIcon green_ok = new ImageIcon(fichero.getAbsolutePath()+"/build/classes/images/green_ok.png");
        
        public main_controller()
        {
            peripheral_manager_view_instance = new peripheral_manager_view(this);
            
            bt_manager_instance = new bt_manager("COM3", "19200", "8", "1", "None", true);
            bp_manager_instance = new bp_manager("COM6", "19200", "8", "1", "None", true);
            bc_manager_instance = new bc_manager("COM4", "19200", "8", "1", "None", true);
            bg_manager_instance = new bg_manager("COM5", "19200", "8", "1", "None", true);
            oc_manager_instance = new oc_manager("COM7", "19200", "8", "1", "None", true);
          
            available_buttons();
            
            SerialPort[] ports = SerialPort.getCommPorts();

        for (SerialPort serial_port : ports)
        {  
           String cta = peripheral_manager_view_instance.com_text_area.getText();
           peripheral_manager_view_instance.com_text_area.setText(cta+"-"+serial_port.getSystemPortName());
        }
            peripheral_manager_view_instance.setVisible(true);
        }

        public void setpectab(String value){
            peripheral_manager_view_instance.text_area_data_sent.setText(value);
            if(peripheral_manager_view_instance.title.getText().equals("BP"))
            {
                String data_received = bp_manager_instance.j_comport_instance.set_pectab(value);
                peripheral_manager_view_instance.text_area_data_received.setText(data_received);
            }
            else if(peripheral_manager_view_instance.title.getText().equals("BT"))
            {
                String data_received = bt_manager_instance.j_comport_instance.set_pectab(value);
                peripheral_manager_view_instance.text_area_data_received.setText(data_received);
            }                     
        }
	public void mc()
	{ 
                peripheral_not_available();
               
                if(bt_manager_instance.isOpenedBt)
                {
                    if(peripheral_manager_view_instance.title.getText().equals("BT"))
                    {
                       bt_manager_available();
                    }                                    
                }
                if(bp_manager_instance.isOpenedBp)
                {
                    if(peripheral_manager_view_instance.title.getText().equals("BP"))
                    {
                       bp_manager_available();
                    }    
                }
                
                if(bc_manager_instance.isOpenedBc){
                    if(peripheral_manager_view_instance.title.getText().equals("BC"))
                    {
                       bc_manager_available();
                    }    
                }
                if(bg_manager_instance.isOpenedBg){
                    if(peripheral_manager_view_instance.title.getText().equals("BG"))
                    {
                       bp_manager_available();
                    }  
                }
                if(oc_manager_instance.isOpenedOc){
                    if(peripheral_manager_view_instance.title.getText().equals("OC"))
                    {
                       bp_manager_available();
                    } 
                }
                
		
                
		
	}
        
        public void bt_manager_available()
        {
            peripheral_manager_view_instance.status_label.setText("AVAILABLE");
            peripheral_manager_view_instance.check_icon.setIcon(green_ok);
            
            String com_port       = bt_manager_instance.j_comport_instance.get_com_port_name();
            String com_port_label = peripheral_manager_view_instance.COM_port_label.getText();
            peripheral_manager_view_instance.COM_port_label.setText(com_port_label+" "+com_port);

            String baud_rate       = bt_manager_instance.j_comport_instance.get_baud_rate();
            String baud_rate_label = peripheral_manager_view_instance.baud_rate_label.getText();
            peripheral_manager_view_instance.baud_rate_label.setText(baud_rate_label+" "+baud_rate);

            String data_bits       = bt_manager_instance.j_comport_instance.get_data_bits();
            String data_bits_label = peripheral_manager_view_instance.data_bits_label.getText();
            peripheral_manager_view_instance.data_bits_label.setText(data_bits_label+" "+data_bits);

            String stop_bits       = bt_manager_instance.j_comport_instance.get_stop_bits();
            String stop_bits_label = peripheral_manager_view_instance.stop_bits_label.getText();
            peripheral_manager_view_instance.stop_bits_label.setText(stop_bits_label+" "+stop_bits);

            String parity         = bt_manager_instance.j_comport_instance.get_parity();
            String parity_label = peripheral_manager_view_instance.parity_label.getText();
            peripheral_manager_view_instance.parity_label.setText(parity_label+" "+parity);
            
            peripheral_manager_view_instance.print_button.setEnabled(true);
            
        }
        
        public void bp_manager_available()
        {
            peripheral_manager_view_instance.status_label.setText("AVAILABLE");
            peripheral_manager_view_instance.check_icon.setIcon(green_ok);

            String com_port       = bp_manager_instance.j_comport_instance.get_com_port_name();
            String com_port_label = peripheral_manager_view_instance.COM_port_label.getText();
            peripheral_manager_view_instance.COM_port_label.setText(com_port_label+" "+com_port);

            String baud_rate       = bp_manager_instance.j_comport_instance.get_baud_rate();
            String baud_rate_label = peripheral_manager_view_instance.baud_rate_label.getText();
            peripheral_manager_view_instance.baud_rate_label.setText(baud_rate_label+" "+baud_rate);

            String data_bits       = bp_manager_instance.j_comport_instance.get_data_bits();
            String data_bits_label = peripheral_manager_view_instance.data_bits_label.getText();
            peripheral_manager_view_instance.data_bits_label.setText(data_bits_label+" "+data_bits);

            String stop_bits       = bp_manager_instance.j_comport_instance.get_stop_bits();
            String stop_bits_label = peripheral_manager_view_instance.stop_bits_label.getText();
            peripheral_manager_view_instance.stop_bits_label.setText(stop_bits_label+" "+stop_bits);

            String parity         = bp_manager_instance.j_comport_instance.get_parity();
            String parity_label = peripheral_manager_view_instance.parity_label.getText();
            peripheral_manager_view_instance.parity_label.setText(parity_label+" "+parity);
            
            peripheral_manager_view_instance.print_button.setEnabled(true);
            
        }
        
        public void bc_manager_available()
        {
            peripheral_manager_view_instance.status_label.setText("AVAILABLE");
            peripheral_manager_view_instance.check_icon.setIcon(green_ok);

            String com_port       = bc_manager_instance.j_comport_instance.get_com_port_name();
            String com_port_label = peripheral_manager_view_instance.COM_port_label.getText();
            peripheral_manager_view_instance.COM_port_label.setText(com_port_label+" "+com_port);

            String baud_rate       = bc_manager_instance.j_comport_instance.get_baud_rate();
            String baud_rate_label = peripheral_manager_view_instance.baud_rate_label.getText();
            peripheral_manager_view_instance.baud_rate_label.setText(baud_rate_label+" "+baud_rate);

            String data_bits       = bc_manager_instance.j_comport_instance.get_data_bits();
            String data_bits_label = peripheral_manager_view_instance.data_bits_label.getText();
            peripheral_manager_view_instance.data_bits_label.setText(data_bits_label+" "+data_bits);

            String stop_bits       = bc_manager_instance.j_comport_instance.get_stop_bits();
            String stop_bits_label = peripheral_manager_view_instance.stop_bits_label.getText();
            peripheral_manager_view_instance.stop_bits_label.setText(stop_bits_label+" "+stop_bits);

            String parity         = bc_manager_instance.j_comport_instance.get_parity();
            String parity_label = peripheral_manager_view_instance.parity_label.getText();
            peripheral_manager_view_instance.parity_label.setText(parity_label+" "+parity);
            
            
            
        }
        
        public void bg_manager_available()
        {
            peripheral_manager_view_instance.status_label.setText("AVAILABLE");
            peripheral_manager_view_instance.check_icon.setIcon(green_ok);

            String com_port       = bg_manager_instance.j_comport_instance.get_com_port_name();
            String com_port_label = peripheral_manager_view_instance.COM_port_label.getText();
            peripheral_manager_view_instance.COM_port_label.setText(com_port_label+" "+com_port);

            String baud_rate       = bg_manager_instance.j_comport_instance.get_baud_rate();
            String baud_rate_label = peripheral_manager_view_instance.baud_rate_label.getText();
            peripheral_manager_view_instance.baud_rate_label.setText(baud_rate_label+" "+baud_rate);

            String data_bits       = bg_manager_instance.j_comport_instance.get_data_bits();
            String data_bits_label = peripheral_manager_view_instance.data_bits_label.getText();
            peripheral_manager_view_instance.data_bits_label.setText(data_bits_label+" "+data_bits);

            String stop_bits       = bg_manager_instance.j_comport_instance.get_stop_bits();
            String stop_bits_label = peripheral_manager_view_instance.stop_bits_label.getText();
            peripheral_manager_view_instance.stop_bits_label.setText(stop_bits_label+" "+stop_bits);

            String parity         = bg_manager_instance.j_comport_instance.get_parity();
            String parity_label = peripheral_manager_view_instance.parity_label.getText();
            peripheral_manager_view_instance.parity_label.setText(parity_label+" "+parity);
            
        }
        
        public void oc_manager_available()
        {
            peripheral_manager_view_instance.status_label.setText("AVAILABLE");
            peripheral_manager_view_instance.check_icon.setIcon(green_ok);

            String com_port       = oc_manager_instance.j_comport_instance.get_com_port_name();
            String com_port_label = peripheral_manager_view_instance.COM_port_label.getText();
            peripheral_manager_view_instance.COM_port_label.setText(com_port_label+" "+com_port);

            String baud_rate       = oc_manager_instance.j_comport_instance.get_baud_rate();
            String baud_rate_label = peripheral_manager_view_instance.baud_rate_label.getText();
            peripheral_manager_view_instance.baud_rate_label.setText(baud_rate_label+" "+baud_rate);

            String data_bits       = oc_manager_instance.j_comport_instance.get_data_bits();
            String data_bits_label = peripheral_manager_view_instance.data_bits_label.getText();
            peripheral_manager_view_instance.data_bits_label.setText(data_bits_label+" "+data_bits);

            String stop_bits       = oc_manager_instance.j_comport_instance.get_stop_bits();
            String stop_bits_label = peripheral_manager_view_instance.stop_bits_label.getText();
            peripheral_manager_view_instance.stop_bits_label.setText(stop_bits_label+" "+stop_bits);

            String parity         = oc_manager_instance.j_comport_instance.get_parity();
            String parity_label = peripheral_manager_view_instance.parity_label.getText();
            peripheral_manager_view_instance.parity_label.setText(parity_label+" "+parity);
            
        }

        public void peripheral_not_available()
        {
            peripheral_manager_view_instance.status_label.setText("NOT AVAILABLE");
            peripheral_manager_view_instance.check_icon.setIcon(red_error);

            peripheral_manager_view_instance.COM_port_label.setText("COM port :");
            peripheral_manager_view_instance.baud_rate_label.setText("Baud rate :");
            peripheral_manager_view_instance.data_bits_label.setText("Data bits :");
            peripheral_manager_view_instance.stop_bits_label.setText("Stop bits :");
            peripheral_manager_view_instance.parity_label.setText("Parity :");
            
            peripheral_manager_view_instance.print_button.setEnabled(false);
         
        }
        
        public void available_buttons()
        {
          if(bt_manager_instance.isOpenedBt)
                {
                   peripheral_manager_view_instance.check_atb.setIcon(green_ok);                  
                }
            else
            {
                peripheral_manager_view_instance.check_atb.setIcon(red_error);
            }
            if(bp_manager_instance.isOpenedBp)
            {
                 peripheral_manager_view_instance.check_btp.setIcon(green_ok);
            }
            else
            {
                peripheral_manager_view_instance.check_btp.setIcon(red_error);
            }
            
            if(bc_manager_instance.isOpenedBc)
            {
                 peripheral_manager_view_instance.check_bcr.setIcon(green_ok);
            }
            else
            {
                peripheral_manager_view_instance.check_bcr.setIcon(red_error);
            }
            if(bg_manager_instance.isOpenedBg)
            {
                 peripheral_manager_view_instance.check_bg.setIcon(green_ok);
            }
            else
            {
                peripheral_manager_view_instance.check_bg.setIcon(red_error);
            }
            if(oc_manager_instance.isOpenedOc)
            {
                 peripheral_manager_view_instance.check_ocr.setIcon(green_ok);
            }
            else
            {
                peripheral_manager_view_instance.check_ocr.setIcon(red_error);
            }  
        }
}
