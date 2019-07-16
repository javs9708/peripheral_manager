package peripheral.serial;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.File;

import peripheral.configuration.configuration_universal;
import peripheral.logs.debug;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.ImageIcon;
import views.peripheral_manager_view;

public class j_com_port extends com_port implements SerialPortDataListener
{
  
  SerialPort             serial_port;
  boolean                baud_rate_exist;
  boolean                data_bits_exist;
  boolean                stop_bits_exist;
  boolean                parity_exist;
  boolean                com_port_exist;
  OutputStream           outStream;
  InputStream            inStream;
  byte[]                 readBuffer;
  

  public boolean         com_port_opened   = false;

  String                 app_token         = "";
  String                 parameters        = "";
  String                 response          = "";

  
  boolean                is_serial_printer = false;

  // /////// Parameters

  String                 com_port_name     = "";
  String                 baud_rate         = "";
  String                 data_bits         = "";
  String                 stop_bits         = "";
  String                 parity            = "";

  private String         actual_error      = "";
  private String         last_error      = "";
  private String         last_error2      = "";

  private boolean        paper_low_bt      = false;
  private boolean        paper_low_bp      = false;

  public j_com_port()
  {
    baud_rate_exist = true;
    data_bits_exist = true;
    stop_bits_exist = true;
    parity_exist = true;
    com_port_exist = true;
    readBuffer = new byte[400];
  }

  /**
   * 
   * @return actual error from OCR
   */
  public String get_actual_error()
  {
    return actual_error;
  }

  /**
   * 
   * @param error
   *          , set error string
   */
  public void set_actual_error(String error)
  {
	  debug.set_debug(error);
    
  }

  public boolean is_connection_error()
  {
    if (!actual_error.equals("") && !actual_error.equals(configuration_universal.COVER_OPEN) && !actual_error.equals(configuration_universal.PAPER_JAM) && !actual_error.equals(configuration_universal.PAPER_OUT))
    {
      return true;
    }
    return false;
  }

  public boolean is_cover_open()
  {
    if (!actual_error.equals("") && actual_error.equals(configuration_universal.COVER_OPEN))
    {
      return true;
    } else
    {
      return false;
    }

  }

  public boolean is_paper_jam()
  {
    if (!actual_error.equals("") && actual_error.equals(configuration_universal.PAPER_JAM))
    {
      return true;
    } else
    {
      return false;
    }
  }

  public boolean is_paper_out()
  {
    if (!actual_error.equals("") && actual_error.equals(configuration_universal.PAPER_OUT))
    {
      return true;
    } else
    {
      return false;
    }
  }

  public void clean_error()
  {
    actual_error = "";
  }

  @Override
  public String get_com_port_name()
  {
    return com_port_name;
  }

  @Override
  public String get_baud_rate()
  {
    return baud_rate;
  }

  @Override
  public String get_data_bits()
  {
    return data_bits;
  }

  @Override
  public String get_stop_bits()
  {
    return stop_bits;
  }

  @Override
  public String get_parity()
  {
    return parity;
  }

  @Override
  public boolean open_port(String com_port_name, String baud_rate, String data_bits, String stop_bits, String parity, boolean is_serial_printer)
  {

    // Set parameters
    this.com_port_name     = com_port_name;
    this.baud_rate         = baud_rate;
    this.data_bits         = data_bits;
    this.stop_bits         = stop_bits;
    this.parity            = parity;
    this.is_serial_printer = is_serial_printer;
 

    parameters = "String com_port_name: " + com_port_name + ", String baud_rate: " + baud_rate + ", String data_bits: " + data_bits + ", String stop_bits: " + stop_bits + ", String parity: " + parity;
    response = "";
    this.app_token = "";

    
    if (com_port_opened == false)
    {
     
      try
      {
        baud_rate.replaceAll("\\s{0,}", "");
        parity.replaceAll("\\s{0,}", "");
        data_bits.replaceAll("\\s{0,}", "");
        stop_bits.replaceAll("\\s{0,}", "");
        com_port_name.replaceAll("\\s{0,}", "");

        serial_port = get_comport(com_port_name);

        if (serial_port == null)
        {
          debug.set_debug("Serial port [" + com_port_name + "] Not visible");
          set_actual_error("Serial port [" + com_port_name + "] Not visible");
          return false;
        }

        boolean openedSuccessfully = serial_port.openPort();
        debug.set_debug("Opening " + serial_port.getSystemPortName() + ": " + serial_port.getDescriptivePortName() + " - " + serial_port.getPortDescription() + ": " + openedSuccessfully);
        if (!openedSuccessfully)
        {
          debug.set_debug("The serial port [" + com_port_name + "] can not be opened");
          set_actual_error("The serial port [" + com_port_name + "] can not be opened");
          return false;
        }

        if (baud_rate.equals("110") || baud_rate.equals("115200") || baud_rate.equals("1200") || baud_rate.equals("19200") || baud_rate.equals("2400") || baud_rate.equals("300") || baud_rate.equals("38400") || baud_rate.equals("4800") || baud_rate.equals("57600") || baud_rate.equals("600") || baud_rate.equals("9600"))
        {
          this.baud_rate_exist = true;
        } else
        {
          this.baud_rate_exist = false;
          set_actual_error("baud rate not available -> [" + baud_rate + "]");
          return false;
        }

        int serial_port_data_bits;
        if (data_bits.equals("5") || data_bits.equals("6") || data_bits.equals("7") || data_bits.equals("8"))
        {
          this.data_bits_exist = true;
          serial_port_data_bits = Integer.parseInt(data_bits);
        } else
        {
          this.data_bits_exist = false;
          set_actual_error("databits not available -> [" + data_bits + "]");
          return false;
        }

        int serial_port_stop_bits;
        if (stop_bits.equals("1"))
          serial_port_stop_bits = SerialPort.ONE_STOP_BIT;
        else if (stop_bits.equals("1.5"))
          serial_port_stop_bits = SerialPort.ONE_POINT_FIVE_STOP_BITS;
        else if (stop_bits.equals("2"))
          serial_port_stop_bits = SerialPort.TWO_STOP_BITS;
        else
        {
          this.stop_bits_exist = false;
          set_actual_error("stop_bits not available -> [" + stop_bits + "]");
          return false;
        }

        int serial_port_parity;
        if (parity.toUpperCase().equals("EVEN"))
          serial_port_parity = SerialPort.EVEN_PARITY;
        else if (parity.toUpperCase().equals("MARK"))
          serial_port_parity = SerialPort.MARK_PARITY;
        else if (parity.toUpperCase().equals("NONE"))
          serial_port_parity = SerialPort.NO_PARITY;
        else if (parity.toUpperCase().equals("ODD"))
          serial_port_parity = SerialPort.ODD_PARITY;
        else if (parity.toUpperCase().equals("SPACE"))
          serial_port_parity = SerialPort.SPACE_PARITY;
        else
        {
          this.parity_exist = false;
          set_actual_error("parity not available -> [" + parity + "]");
          return false;
        }
        
        if (!serial_port.isOpen())
        {
          debug.set_debug("The serial port [" + com_port_name + "] is not open");
          return false;
        }
        
        serial_port.setComPortParameters(Integer.parseInt(baud_rate), serial_port_data_bits, serial_port_stop_bits, serial_port_parity);
        serial_port.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
        outStream = serial_port.getOutputStream();
        inStream  = serial_port.getInputStream();

        serial_port.addDataListener(this);

        com_port_opened = true;
      } catch (Exception e)
      {
        set_actual_error("Error opening serial port [" + com_port_name + "] Exception: " + e.getMessage());
        return false;
      }
    }

    return true;
  }

  /**
   * brief Disconnect the serial port
   */
  @Override
  public void close_port()
  {
    parameters = "";
    response = "";
    this.app_token = "";

    if (serial_port != null)
    {
      try
      {
        outStream.close();
        inStream.close();
        debug.set_debug("Port closed " + com_port_name);
      } catch (Exception ex)
      {

        debug.set_debug("Exception can't close port - " + ex.getMessage());
      }
      serial_port.closePort();
      serial_port = null;
      com_port_opened = false;
    }
  }

  @Override
  public int getListeningEvents()
  {
    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
  }

  @Override
  public void serialEvent(SerialPortEvent event)
  {
    parameters = "SerialPortEvent events";
    response = "";
    this.app_token = "";

    switch (event.getEventType()) {
      case SerialPort.LISTENING_EVENT_DATA_AVAILABLE:
        debug.set_debug(this.com_port_name + " read_serial DATA_AVAILABLE ");
        read_serial();
        break;
    }
  }

  /**
   * 
   * @return String saved on var "data" available data in comport
   */
  @Override
  public String get_available_data()
  {
    char STX = '\2'; // START STRING
    char ETX = '\3'; // START STRING
    if (is_serial_printer)
    {
      if (data.contains(STX + "") && data.contains(ETX + ""))
      {
        return data;
      } else
      {
        return "";
      }
    } else
    {
      return data;
    }
  }

  

  public void read_serial()
  {
    /*
     * parameters = ""; response = ""; this.app_token = ""; debug.set_debug(app_token,"start",parameters,response); boolean is_credit_card = false;
     */
    try
    {
      int availableBytes = inStream.available();
      debug.set_debug(this.com_port_name + " read_serial availableBytes " + availableBytes);

      if (availableBytes > 0)
      {
        inStream.read(readBuffer, 0, availableBytes);

        String new_string = new String(readBuffer, 0, availableBytes);
        debug.set_debug(this.com_port_name + " read_serial new_string " + new_string);

        if (is_serial_printer)
        {
          char STX = '\2'; // START STRING
          String weird_start_PA_printer_1 = "�";
          if (new_string.startsWith(STX + "") || new_string.startsWith(weird_start_PA_printer_1))
          {
            data = new_string;
          } else
          {
            if (!data.equals(""))
            {
              data = data + new_string;
            } else
            {
              debug.set_debug("warning, rubbish string: " + new_string);
            }
          }
        } else
        {
        
          
        data = data + new_string;
          
          
        }

        // cuss_server.magnetic_stripe_reader_instance.set_and_format_comport_data(data);

        /*
         * if(configuration_universal.SHOW_MSR_STRING) debug.set_debug(app_token, "String read: "+string_var,parameters,response); else { String var_to_show = string_var.replaceAll(".", "*"); debug.set_debug(app_token ,"String read: "+var_to_show,parameters,response); } if(track_1.equals("")) {
         * boolean quit_garbage = false; if(string_var.contains("%B")) is_credit_card = true; for(int i = 1; i < string_var.length(); i++) { if(string_var.substring(0, i).contains("% ") || string_var.substring(0, i).contains("%B")) { string_var = string_var.substring(i); i = string_var.length();
         * quit_garbage = true; } } if(!quit_garbage) string_var = ""; track_1 = string_var; } else { if(track_1.equals("")) { for( int i = 1; i < string_var.length(); i++) { if(string_var.substring(0,i).contains("?")) { track_1 = string_var.substring(i); string_var = string_var.substring(0, i-1);
         * if(is_credit_card) string_var = string_var.replaceAll("[^0-9]", ""); i = string_var.length(); } } track_1 = track_1 + string_var; track_1 = track_1.replaceAll("[^0-9=]", ""); } } if(configuration_universal.SHOW_MSR_STRING) { debug.set_debug(app_token
         * ,"Final string 1: ["+track_1+"]",parameters,response); debug.set_debug (app_token,"Final string 2: ["+track_1+"]",parameters,response); } else { String var_to_show = track_1.replaceAll(".", "*"); debug.set_debug (app_token,"Final string: "+var_to_show,parameters,response); }
         */
      }
    } catch (Exception e)
    {
      debug.set_debug("Error receiving data  :[" + e.getMessage() + "]");
    }
  }

  @Override
  public boolean isCTS()
  {
    parameters = "";
    response = "";
    this.app_token = "";

    boolean result = false;

    if (serial_port != null)
    {
      result = serial_port.getCTS();
//      debug.set_debug(this.com_port_name + " getCTS: " + result);
    }

    if (!result)
    {
      set_actual_error("CTS false");
      debug.set_debug(this.com_port_name + " CTS false. com_port_opened: " + this.com_port_opened + ". (serial_port != null) " + (serial_port != null));
    }

    return result;
  }

  @Override
  public boolean isDSR()
  {
    parameters     = "";
    response       = "";
    this.app_token = "";
    boolean result = false;

    if (serial_port != null)
    {
      result = serial_port.getDSR();
//      debug.set_debug(this.com_port_name + " getDSR: " + result);
    }
    return result;
  }
  
  @Override
  public boolean set_dtr()
  {
    parameters     = "";
    response       = "boolean: true";
    this.app_token = "";
    boolean result = false;
    
    if(serial_port != null && is_open() == configuration_universal.DEVICE_OK);
    {
      result = serial_port.setDTR();
    }
    return result;
  }
 
  @Override
  public boolean set_rts()
  {
    parameters     = "";
    response       = "boolean: true";
    this.app_token = "";
    boolean result = false;

    if(serial_port != null && is_open() == configuration_universal.DEVICE_OK)
    {
      result = serial_port.setRTS();
    }
    return result;
  }

  private boolean write_data(String data)
  {
    parameters = "";
    response = "";
    this.app_token = "";

    char STX = '\2';
    char ETX = '\3';
    data = STX + data + ETX;
    debug.set_debug(com_port_name + " - pectab[" + data + "]");

    try
    {
      outStream.write(data.getBytes());
      return true;
    } catch (Exception ex)
    {
      System.err.println(ex.getMessage());
      debug.set_debug("Error: " + ex.getMessage());
      set_actual_error(ex.getMessage());
      return false;
    }
  }

  public boolean write_raw_data(String data)
  {
    parameters = "";
    response = "";
    this.app_token = "";
    debug.set_debug("write_raw_data data " + data + ". COMPORT_NAME: " + this.com_port_name);
    try
    {
      debug.set_debug("com_port_opened: " + com_port_opened);
      debug.set_debug("write_raw_data isCTS? " + serial_port.getCTS());
      debug.set_debug("write_raw_data serial_port NULL? " + (serial_port == null) + ". serial_port Open "+serial_port.isOpen());
      debug.set_debug("write_raw_data outStream NULL? " + (outStream == null));
      
      com_port_opened = serial_port.isOpen();
      outStream.write(data.getBytes());
      return true;
    } catch (IOException ex)
    {
      
      debug.set_debug("Error IOException: " + ex.getMessage());

      return false;
    }
  }

  public boolean get_baud_rate_exist()
  {
    parameters = "";
    response = "boolean: " + this.baud_rate_exist;
    this.app_token = "";

    return this.baud_rate_exist;
  }

  public boolean get_parity_exist()
  {
    parameters = "";
    response = "boolean: " + this.parity_exist;
    this.app_token = "";

    return this.parity_exist;
  }

  public boolean get_data_bits_exist()
  {
    parameters = "";
    response = "boolean: " + this.data_bits_exist;
    this.app_token = "";

    return this.data_bits_exist;
  }

  public boolean get_stop_bits_exist()
  {
    parameters = "";
    response = "boolean: " + this.stop_bits_exist;
    this.app_token = "";

    return this.stop_bits_exist;
  }

  public boolean get_com_port_exist()
  {
    parameters = "";
    response = "boolean: " + this.com_port_exist;
    this.app_token = "";

    return this.com_port_exist;
  }


  @Override
  public int is_open()
  {
    if (com_port_opened)
    {
      return configuration_universal.DEVICE_OK;
    } else
    {
      if (!com_port_name.equals("") && !baud_rate.equals("") && !data_bits.equals("") && !stop_bits.equals("") && !parity.equals(""))
      {
        open_port(com_port_name, baud_rate, data_bits, stop_bits, parity, is_serial_printer);
      } else
      {
        debug.set_debug("Parameters incomplete: com_port_name: " + com_port_name + ", baud_rate " + baud_rate + ", data_bits: " + data_bits + ", stop_bits: " + stop_bits + ", parity: " + parity);
      }
    }
    if (com_port_opened)
    {
      debug.set_debug("Ok, comport opened: " + com_port_name);
      return configuration_universal.DEVICE_OK;
    } else
    {
      debug.set_debug("Error, comport not opened: " + com_port_name);
      return configuration_universal.DEVICE_SOFTWARE_ERROR;
    }
  }

  @Override
  public String set_pectab(String pectab)
  {

    String result = "";
    if (this.write_data(pectab))
    {
      boolean data_available = false;
      int i = 0;
      try
      {
        while (!data_available && i++ < configuration_universal.WAIT_FOR_SERIAL_DATA_ANSWER * 10)
        {
          Thread.sleep(100);

          if (!get_available_data().equals(""))
          {
            data_available = true;
            result = get_available_data();
            clean();
            debug.set_debug(com_port_name + " - data avaiable: " + result);
          } else
          {
            debug.set_debug("data not avaiable");
          }

        }
      } catch (InterruptedException e)
      {
        e.printStackTrace();
      }

    } else
    {
      debug.set_debug("serial ATP_ERR6");
      result = "ATP_ERR6";
    }
    debug.set_debug("set_pectab result: " + result); 
    return result;
  }

  public String send_raw_with_answer(String var_raw)
  {
    String result = "";
    if (this.write_raw_data(var_raw))
    {
      boolean data_available = false;
      int i = 0;
      try
      {
        while (!data_available && i++ < configuration_universal.COMPORT_RAW_DATA_TIMEOUT * 10)
        {
          Thread.sleep(100);
          debug.set_debug("send_raw_with_answer get_available_data():" + get_available_data());
          if (!get_available_data().equals(""))
          {
            data_available = true;
            result = get_available_data();
            clean();
            debug.set_debug(com_port_name + " - data avaiable: " + result);
          } else
          {
            // debug.set_debug("data not avaiable");
          }

        }
      } catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    } else
    {
      debug.set_debug("ERROR_WRITE_DATA");
      result = "ERROR_WRITE_DATA";
    }
    return result;
  }

  public void set_paper_low_bt(boolean paper_low)
  {
    paper_low_bt = paper_low;
  }

  public boolean is_paper_low_bt()
  {
    return paper_low_bt;
  }

  public boolean is_paper_low_bp()
  {
    return false;
  }

  public static SerialPort get_comport(String comport_name)
  {
    SerialPort[] ports = SerialPort.getCommPorts();

    for (SerialPort serial_port : ports)
    {   
      if (serial_port.getSystemPortName().equals(comport_name))
      {
        debug.set_debug(comport_name + " Present");
        return serial_port;
      }
    }
    debug.set_debug(comport_name + " NOT Present");

    return null;
  }
  
  
  public static void main(String[] args)
  {
    j_com_port comport_instance = new j_com_port();
    boolean answer = comport_instance.open_port("COM30", "9600", "8", "1", "NONE", true);
    debug.set_debug("answer: " + answer);
    while(true)
    {
      try
      {
        if (comport_instance.is_open() == configuration_universal.DEVICE_OK && comport_instance.isDSR() && comport_instance.isCTS())
        {
          String data = comport_instance.get_available_data();
          if (!data.equals(""))
          {
            debug.set_debug("data: " + data);
            comport_instance.clean();
          }
        }
        else
        {
          comport_instance.close_port();
          comport_instance.open_port("COM30", "9600", "8", "1", "NONE", true);
        }
        Thread.sleep(2000);
      }
      catch (InterruptedException ex)
      {
        debug.set_debug(ex.getMessage());
      }
    }
  }
  
  public static void main1SDF(String[] args)
  {
    try
    {
      SerialPort serial_port = null;
      String com_port = "COM5";
      boolean isOpened = false;

      while (true)
      {
        serial_port = get_comport(com_port);
        debug.set_debug(com_port + " Present");

        if (serial_port != null)
        {
          try
          {
            debug.set_debug(com_port + " isOpened " + isOpened);
            if (!isOpened)
            {
              boolean openedSuccessfully = serial_port.openPort();
              debug.set_debug("\nOpening " + serial_port.getSystemPortName() + ": " + serial_port.getDescriptivePortName() + " - " + serial_port.getPortDescription() + ": " + openedSuccessfully);
              if (openedSuccessfully)
              {
                isOpened = true;
                debug.set_debug("Serial port opened.");
                serial_port.setBaudRate(9600);

                if (serial_port.closePort())
                {
                  isOpened = false;
                }
              }
            } else
            {
              debug.set_debug(com_port + " is Open");
            }

          } catch (Exception e)
          {
            debug.set_debug("\nSerial port NOT opened, Exception: " + e.getMessage());
          }
        } else
        {
          debug.set_debug(com_port + "=NULL NOT Present");
          isOpened = false;
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
    } catch (Exception e)
    {
      debug.set_debug("\nWhile Exception");
      e.printStackTrace();
    }
  }

}
