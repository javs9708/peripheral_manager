package peripheral.manager;

import com.fazecast.jSerialComm.SerialPort;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import javax.swing.ImageIcon;
import peripheral.configuration.configuration_universal;
import peripheral.core.bg_manager;
import peripheral.core.scale_manager;
import peripheral.core.bp_manager;
import peripheral.core.bt_manager;
import peripheral.core.oc_manager;
import peripheral.core.peripheral;
import peripheral.server.server;
import views.peripheral_manager_view;

public class main_controller
{

  bg_manager bg_manager_instance = null;
  bt_manager bt_manager_instance = null;
  bp_manager bp_manager_instance = null;
  scale_manager scale_manager_instance = null;
  oc_manager oc_manager_instance = null;

  public static peripheral_manager_view peripheral_manager_view_instance;
  server server_instance;

  File fichero = new File(".");

  ImageIcon red_error = new ImageIcon(fichero.getAbsolutePath() + "/build/classes/images/red_error.png");
  ImageIcon green_ok = new ImageIcon(fichero.getAbsolutePath() + "/build/classes/images/green_ok.png");

  public main_controller() throws FileNotFoundException, UnsupportedEncodingException
  {
    peripheral_manager_view_instance = new peripheral_manager_view(this);
    peripheral_manager_view_instance.setVisible(true);

    server_instance = new server();

    for (int i = 0; i < server_instance.peripheral_counter; i++)
    {
      if (server_instance.json_com_ports[i][0].equals("bt"))
      {
        bt_manager_instance = new bt_manager(server_instance.json_com_ports[i][1], server_instance.json_com_ports[i][2], server_instance.json_com_ports[i][3], server_instance.json_com_ports[i][4], server_instance.json_com_ports[i][5],true);
      }
      if (server_instance.json_com_ports[i][0].equals("bp"))
      {
        bp_manager_instance = new bp_manager(server_instance.json_com_ports[i][1], server_instance.json_com_ports[i][2], server_instance.json_com_ports[i][3], server_instance.json_com_ports[i][4], server_instance.json_com_ports[i][5], true);
      }
      if (server_instance.json_com_ports[i][0].equals("bg"))
      {
        bg_manager_instance = new bg_manager(server_instance.json_com_ports[i][1], server_instance.json_com_ports[i][2], server_instance.json_com_ports[i][3], server_instance.json_com_ports[i][4], server_instance.json_com_ports[i][5], false);
      }
      if (server_instance.json_com_ports[i][0].equals("scale"))
      {
        scale_manager_instance = new scale_manager(server_instance.json_com_ports[i][1], server_instance.json_com_ports[i][2], server_instance.json_com_ports[i][3], server_instance.json_com_ports[i][4], server_instance.json_com_ports[i][5], false);
      }
      if (server_instance.json_com_ports[i][0].equals("oc"))
      {
        oc_manager_instance = new oc_manager(server_instance.json_com_ports[i][1], server_instance.json_com_ports[i][2], server_instance.json_com_ports[i][3], server_instance.json_com_ports[i][4], server_instance.json_com_ports[i][5], false);
      }
    }

    available_buttons();

    SerialPort[] ports = SerialPort.getCommPorts();

    for (SerialPort serial_port : ports)
    {
      String cta = peripheral_manager_view_instance.com_text_area.getText();
      peripheral_manager_view_instance.com_text_area.setText(cta + "-" + serial_port.getSystemPortName());
    }

  }

  public void test_printer_action()
  {

    if (peripheral_manager_view_instance.title.getText().equals("BP"))
    {
      peripheral_manager_view_instance.text_area_data_sent.setText(bp_manager_instance.header_test_pectab);
      String data_received_header = bp_manager_instance.send_pectab(bp_manager_instance.header_test_pectab);
      peripheral_manager_view_instance.text_area_data_received.setText("Header answer: " + data_received_header);
      if (data_received_header.toUpperCase().contains("OK"))
      {
        peripheral_manager_view_instance.text_area_data_sent.setText(peripheral_manager_view_instance.text_area_data_sent.getText() + "\n" + bp_manager_instance.body_test_pectab);
        String data_received_body = bp_manager_instance.send_pectab(bp_manager_instance.body_test_pectab);
        peripheral_manager_view_instance.text_area_data_received.setText(peripheral_manager_view_instance.text_area_data_received.getText() + "\nBody answer: " + data_received_body);
      }
    }
    else if (peripheral_manager_view_instance.title.getText().equals("BT"))
    {
      peripheral_manager_view_instance.text_area_data_sent.setText(bt_manager_instance.header_test_pectab);
      String data_received_header = bt_manager_instance.send_pectab(bt_manager_instance.header_test_pectab);
      peripheral_manager_view_instance.text_area_data_received.setText("Header answer: " + data_received_header);
      if (data_received_header.toUpperCase().contains("OK"))
      {
        peripheral_manager_view_instance.text_area_data_sent.setText(peripheral_manager_view_instance.text_area_data_sent.getText() + "\n" + bt_manager_instance.body_test_pectab);
        String data_received_body = bt_manager_instance.send_pectab(bt_manager_instance.body_test_pectab);
        peripheral_manager_view_instance.text_area_data_received.setText(peripheral_manager_view_instance.text_area_data_received.getText() + "\nBody answer: " + data_received_body);
      }
    }
    refresh_peripheral();
  }

  public void load_peripheral()
  {
    new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        load_peripheral_thread(true);
      }
    }).start();
  }

  public void refresh_peripheral()
  {
    new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        load_peripheral_thread(false);
      }
    }).start();
  }

  public void load_peripheral_thread(boolean load_details)
  {
//    if (load_details)
//    {
      set_default_details();
//    }
    /////////////////////////////// BT
    if (peripheral_manager_view_instance.title.getText().equals("BT"))
    {
      if (bt_manager_instance != null)
      {
        if (bt_manager_instance.is_available())
        {

          peripheral_set_details(configuration_universal.AVAILABLE_TEXT, load_details, bt_manager_instance);
        }
        else
        {
          peripheral_set_details(configuration_universal.UNAVAILABLE_TEXT, load_details, bt_manager_instance);
        }
      }
      else
      {
        peripheral_set_details(configuration_universal.UNASSIGNED_TEXT, load_details, bt_manager_instance);
      }
    }

    /////////////////////////////// BP
    if (peripheral_manager_view_instance.title.getText().equals("BP"))
    {
      if (bp_manager_instance != null)
      {
        if (bp_manager_instance.is_available())
        {

          peripheral_set_details(configuration_universal.AVAILABLE_TEXT, load_details, bp_manager_instance);
        }
        else
        {
          peripheral_set_details(configuration_universal.UNAVAILABLE_TEXT, load_details, bp_manager_instance);
        }
      }
      else
      {
        peripheral_set_details(configuration_universal.UNASSIGNED_TEXT, load_details, bp_manager_instance);
      }
    }

    /////////////////////////////// BG
    if (peripheral_manager_view_instance.title.getText().equals("BG"))
    {
      if (bg_manager_instance != null)
      {
        if (bg_manager_instance.is_available())
        {

          peripheral_set_details(configuration_universal.AVAILABLE_TEXT, load_details, bg_manager_instance);
        }
        else
        {
          peripheral_set_details(configuration_universal.UNAVAILABLE_TEXT, load_details, bg_manager_instance);
        }
      }
      else
      {
        peripheral_set_details(configuration_universal.UNASSIGNED_TEXT, load_details, bg_manager_instance);
      }
    }

    /////////////////////////////// SCALE
    if (peripheral_manager_view_instance.title.getText().equals("SCALE"))
    {
      if (scale_manager_instance != null)
      {
        if (scale_manager_instance.is_available())
        {

          peripheral_set_details(configuration_universal.AVAILABLE_TEXT, load_details, scale_manager_instance);
        }
        else
        {
          peripheral_set_details(configuration_universal.UNAVAILABLE_TEXT, load_details, scale_manager_instance);
        }
      }
      else
      {
        peripheral_set_details(configuration_universal.UNASSIGNED_TEXT, load_details, scale_manager_instance);
      }
    }

    /////////////////////////////// OC
    if (peripheral_manager_view_instance.title.getText().equals("OC"))
    {
      if (oc_manager_instance != null)
      {
        if (oc_manager_instance.is_available())
        {

          peripheral_set_details(configuration_universal.AVAILABLE_TEXT, load_details, oc_manager_instance);
        }
        else
        {
          peripheral_set_details(configuration_universal.UNAVAILABLE_TEXT, load_details, oc_manager_instance);
        }
      }
      else
      {
        peripheral_set_details(configuration_universal.UNASSIGNED_TEXT, load_details, oc_manager_instance);
      }
    }

  }

  public void peripheral_set_details(String status, boolean load_details, peripheral peripheral_instance)
  {
    if (peripheral_instance == null)
    {
      peripheral_manager_view_instance.check_icon.setIcon(red_error);
      peripheral_manager_view_instance.status_label.setText(configuration_universal.UNASSIGNED_TEXT);
    }
    else
    {
      if (status.equals(configuration_universal.AVAILABLE_TEXT))
      {
        peripheral_manager_view_instance.status_label.setText(status);
        peripheral_manager_view_instance.error_label.setText("");
      }
      else
      {
        peripheral_manager_view_instance.status_label.setText(status);
        if(peripheral_instance.var_last_status.equals(""))
        {
          peripheral_manager_view_instance.error_label.setText(configuration_universal.CONNECTION_ERROR);
        }
        else
        {
          peripheral_manager_view_instance.error_label.setText(peripheral_instance.var_last_status);
        }
      }

      if (status.equals(configuration_universal.AVAILABLE_TEXT))
      {
        peripheral_manager_view_instance.check_icon.setIcon(green_ok);
      }
      else
      {
        peripheral_manager_view_instance.check_icon.setIcon(red_error);
      }

      boolean force_load_details = false;
      if(peripheral_manager_view_instance != null)
      {
        String com_port_label = peripheral_manager_view_instance.COM_port_label.getText();
        String com_port = peripheral_instance.comport_instance.get_com_port_name();
        if(!com_port_label.contains(com_port))  
        {
          force_load_details = true;
        }
      }
      
      
      
      if (force_load_details)
      {
        String com_port = peripheral_instance.comport_instance.get_com_port_name();
        peripheral_manager_view_instance.COM_port_label.setText("COM port: " + com_port);

        String baud_rate = peripheral_instance.comport_instance.get_baud_rate();
        peripheral_manager_view_instance.baud_rate_label.setText("Baud rate: " + baud_rate);

        String data_bits = peripheral_instance.comport_instance.get_data_bits();
        peripheral_manager_view_instance.data_bits_label.setText("Data bits: " + data_bits);

        String stop_bits = peripheral_instance.comport_instance.get_stop_bits();
        peripheral_manager_view_instance.stop_bits_label.setText("Stop bits: " + stop_bits);

        String parity = peripheral_instance.comport_instance.get_parity();
        peripheral_manager_view_instance.parity_label.setText("Parity: " + parity);

        peripheral_manager_view_instance.print_button.setEnabled(true);
      }
    }
  }

  
  public void set_default_details()
  {
    peripheral_manager_view_instance.status_label.setText(configuration_universal.UNAVAILABLE_TEXT);
    peripheral_manager_view_instance.check_icon.setIcon(red_error);

    peripheral_manager_view_instance.COM_port_label.setText("COM port:");
    peripheral_manager_view_instance.baud_rate_label.setText("Baud rate:");
    peripheral_manager_view_instance.data_bits_label.setText("Data bits:");
    peripheral_manager_view_instance.stop_bits_label.setText("Stop bits:");
    peripheral_manager_view_instance.parity_label.setText("Parity:");

    peripheral_manager_view_instance.print_button.setEnabled(false);

  }

  public void available_buttons()
  {
    if (bt_manager_instance != null && bt_manager_instance.is_available())
    {
      peripheral_manager_view_instance.check_atb.setIcon(green_ok);
    }
    else
    {
      peripheral_manager_view_instance.check_atb.setIcon(red_error);
    }
    if (bp_manager_instance != null && bp_manager_instance.is_available())
    {
      peripheral_manager_view_instance.check_btp.setIcon(green_ok);
    }
    else
    {
      peripheral_manager_view_instance.check_btp.setIcon(red_error);
    }

    if (bg_manager_instance != null && bg_manager_instance.is_available())
    {
      peripheral_manager_view_instance.check_bgr.setIcon(green_ok);
    }
    else
    {
      peripheral_manager_view_instance.check_bgr.setIcon(red_error);
    }
    if (scale_manager_instance != null && scale_manager_instance.is_available())
    {
      peripheral_manager_view_instance.check_scale.setIcon(green_ok);
    }
    else
    {
      peripheral_manager_view_instance.check_scale.setIcon(red_error);
    }
    if (oc_manager_instance != null && oc_manager_instance.is_available())
    {
      peripheral_manager_view_instance.check_ocr.setIcon(green_ok);
    }
    else
    {
      peripheral_manager_view_instance.check_ocr.setIcon(red_error);
    }
  }
}
