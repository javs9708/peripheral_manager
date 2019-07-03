/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.serial;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
//import ink_cuss_source.cuss_server;
//import ink_cuss_source.miscellaneous.configuration_universal;
//import ink_cuss_source.miscellaneous.debug;
//import ink_cuss_source.miscellaneous.error_manager;
//import ink_cuss_source.miscellaneous.helper;
//import ink_cuss_source.other.magnetic_stripe_reader;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import peripheral.configuration.configuration_universal;
import peripheral.logs.debug;

public class old_com_port extends com_port implements SerialPortEventListener
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
  private String         data              = "";

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

  private boolean        paper_low_bt      = false;
  private boolean        paper_low_bp      = false;

  public old_com_port()
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
    actual_error = error;
    
    if(!actual_error.equals("") && !actual_error.equals(last_error))
    {
      last_error = actual_error;
      if(com_port_name.equals("COM3"))
      {
        debug.set_debug("BT ERROR: " + actual_error);
      }
      if(com_port_name.equals("COM5"))
      {
        debug.set_debug("BCR ERROR: " + actual_error);
      }
      if(com_port_name.equals("COM6"))
      {
        debug.set_debug("Door Sensor ERROR: " + actual_error);
      }
      if(com_port_name.equals("COM7"))
      {
        debug.set_debug("SCALE ERROR: " + actual_error);
      }
    }
    
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
    this.com_port_name = com_port_name;
    this.baud_rate = baud_rate;
    this.data_bits = data_bits;
    this.stop_bits = stop_bits;
    this.parity = parity;
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
        
        CommPortIdentifier portId = get_comport_identifier(com_port_name);
        
        debug.set_debug(com_port_name + " Owner: " + portId.getCurrentOwner());
        serial_port = (SerialPort) portId.open("", 5000);

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
        if (data_bits.equals("5"))
          serial_port_data_bits = SerialPort.DATABITS_5;
        else if (data_bits.equals("6"))
          serial_port_data_bits = SerialPort.DATABITS_6;
        else if (data_bits.equals("7"))
          serial_port_data_bits = SerialPort.DATABITS_7;
        else if (data_bits.equals("8"))
          serial_port_data_bits = SerialPort.DATABITS_8;
        else
        {
          this.data_bits_exist = false;
          set_actual_error("databits not available -> [" + data_bits + "]");
          return false;
        }

        int serial_port_stop_bits;
        if (stop_bits.equals("1"))
          serial_port_stop_bits = SerialPort.STOPBITS_1;
        else if (stop_bits.equals("1.5"))
          serial_port_stop_bits = SerialPort.STOPBITS_1_5;
        else if (stop_bits.equals("2"))
          serial_port_stop_bits = SerialPort.STOPBITS_2;
        else
        {
          this.stop_bits_exist = false;
          set_actual_error("stop_bits not available -> [" + stop_bits + "]");
          return false;
        }

        int serial_port_parity;
        if (parity.toUpperCase().equals("EVEN"))
          serial_port_parity = SerialPort.PARITY_EVEN;
        else if (parity.toUpperCase().equals("MARK"))
          serial_port_parity = SerialPort.PARITY_MARK;
        else if (parity.toUpperCase().equals("NONE"))
          serial_port_parity = SerialPort.PARITY_NONE;
        else if (parity.toUpperCase().equals("ODD"))
          serial_port_parity = SerialPort.PARITY_ODD;
        else if (parity.toUpperCase().equals("SPACE"))
          serial_port_parity = SerialPort.PARITY_SPACE;
        else
        {
          this.parity_exist = false;
          set_actual_error("parity not available -> [" + parity + "]");
          return false;
        }

        serial_port.setSerialPortParams(Integer.parseInt(baud_rate), serial_port_data_bits, serial_port_stop_bits, serial_port_parity);
        serial_port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
        outStream = serial_port.getOutputStream();
        inStream = serial_port.getInputStream();

        serial_port.addEventListener(this);
        serial_port.notifyOnDataAvailable(true);

        debug.set_debug("Connected to [" + com_port_name + "]");
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
//    try
//    {
      parameters = "";
      response = "";
      this.app_token = "";


//      CommPortIdentifier portId = get_comport_identifier(com_port_name);
      debug.set_debug("close_port " + com_port_name);
//      if (portId != null && serial_port != null)
      if (serial_port != null)
      {
        try
        {
          outStream.close();
          inStream.close();
        } catch (Exception ex)
        {

          debug.set_debug("Exception can't close port - " + ex.getMessage());
        }
        serial_port.close();
        serial_port = null;
      }
    /*}
    catch(Exception e)
    {
      debug.set_debug("Exception can't close serial port - " + e.getMessage(), app_token, configuration_universal.LOG_TYPE_ERROR, error_manager.COMPORT_ERROR_CONNECTION);
    }*/
  }

  public void serialEvent(SerialPortEvent events)
  {
    parameters = "SerialPortEvent events";
    response = "";
    this.app_token = "";

    switch (events.getEventType()) {
      case SerialPortEvent.DATA_AVAILABLE:
        debug.set_debug(this.com_port_name+" read_serial DATA_AVAILABLE ");
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

  @Override
  public void clean()
  {
    data = "";
  }

  public void read_serial()
  {
    /*
     * parameters = ""; response = ""; this.app_token = "";
     * 
     * debug.set_debug(app_token,"start",parameters,response);
     * 
     * boolean is_credit_card = false;
     */
    try
    {
      int availableBytes = inStream.available();
      debug.set_debug(this.com_port_name+" read_serial availableBytes "+availableBytes);
      
      if (availableBytes > 0)
      {
        inStream.read(readBuffer, 0, availableBytes);

        String new_string = new String(readBuffer, 0, availableBytes);
        new_string = clean_read_string(new_string);
        debug.set_debug(this.com_port_name+" read_serial new_string " + new_string);

        if (is_serial_printer)
        {
          char STX = '\2'; // START STRING
          char ETX = '\3'; // END STRING
          String weird_start_PA_printer_1 = "Œ";
          if (new_string.startsWith(ETX + STX + "") || new_string.startsWith(STX + "") || new_string.startsWith(weird_start_PA_printer_1))
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

        

        
      }
    } catch (Exception e)
    {
      debug.set_debug("Error receiving data  :[" + e.getMessage() + "]");
      this.recover_serial_port();
    }
    debug.set_debug("end");
  }
  
  
  public void recover_serial_port()
  {
    try
    {
      this.close_port();
      
      CommPortIdentifier portId = get_comport_identifier(this.com_port_name);
      
      debug.set_debug(this.com_port_name + " Owner: " + portId.getCurrentOwner());
      serial_port = (SerialPort) portId.open("", 5000);
    }
    catch (PortInUseException ex)
    {
      set_actual_error("Error opening serial port in write_raw_data [" + com_port_name + "] Exception: " + ex.getMessage());
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
      result = serial_port.isCTS();
      debug.set_debug("isCTS: " + result);
    }

    if (!result)
    {
      set_actual_error("CTS false");
      debug.set_debug(this.com_port_name+" CTS false. com_port_opened: "+this.com_port_opened+". (serial_port != null) "+(serial_port != null));
      if (serial_port != null)
      {
        this.recover_serial_port();
      }
    }

    return result;
  }

  @Override
  public boolean isDSR()
  {

    parameters = "";
    response = "";
    this.app_token = "";
    boolean result = false;

    if (serial_port != null)
    {
      result = serial_port.isDSR();
      debug.set_debug("isDSR: " + result);
    }
    return result;
  }
  
  @Override
  public boolean set_dtr()
  {
    parameters = "";
    response = "boolean: true";
    this.app_token = "";
    
    if(serial_port != null && is_open() == configuration_universal.DEVICE_OK)
    {
      serial_port.setDTR(true);
    }
    return true;
  }

  @Override
  public boolean set_rts()
  {
    parameters = "";
    response = "boolean: true";
    this.app_token = "";

    if (serial_port != null && is_open() == configuration_universal.DEVICE_OK)
    {
      serial_port.setRTS(true);
    }
    return true;
  }

  private boolean write_data(String data)
  {
    parameters = "";
    response = "";
    this.app_token = "";
    

    char STX = '\2';
    char ETX = '\3';
    data = STX + data + ETX;
    debug.set_debug(com_port_name + " - data[" + data + "]");

    try
    {
      outStream.write(data.getBytes());
      
      return true;
    } catch (Exception ex)
    {
      set_actual_error(ex.getMessage());
      return false;
    }
  }

  public boolean write_raw_data(String data)
  {
    parameters = "";
    response = "";
    this.app_token = "";
    
    debug.set_debug("write_raw_data data "+data+". COMPORT_NAME: "+this.com_port_name);
    try
    {
      outStream.write(data.getBytes());
      
      return true;
    } catch (Exception ex)
    {
      System.err.println(ex.getMessage());
      debug.set_debug("Error: " + ex.getMessage());
      
      this.recover_serial_port();
      
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


  
  
  /*public void restart_serial_port_properties(int attempt)
  {
    try
    {
      debug.set_debug("attempt: " + attempt);
      if (attempt == 1)
      {
        serial_port.addEventListener(this);
        /*serial_port = null;
        com_port_opened = false;
        open_port(com_port_name, baud_rate, data_bits, stop_bits, parity, is_serial_printer);*/
      /*}
      else if (attempt == 2)
      {
        serial_port.addEventListener(this);
        serial_port.notifyOnDataAvailable(true);
        /*this.close_port();
        com_port_opened = false;
        open_port(com_port_name, baud_rate, data_bits, stop_bits, parity, is_serial_printer);*/
      /*}
      else if (attempt == 3)
      {
        serial_port.removeEventListener();
        serial_port.addEventListener(this);
        serial_port.notifyOnDataAvailable(true);
//        this.recover_serial_port();
      }
    }
    catch (Exception e)
    {
      debug.set_debug("restart_serial_port_properties Exception: "+e.getMessage());
    }
  }*/

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
      }
      else
      {
        debug.set_debug("Parameters incomplete: com_port_name: " + com_port_name + ", baud_rate " + baud_rate + ", data_bits: " + data_bits + ", stop_bits: " + stop_bits + ", parity: " + parity);
      }
    }
    if (com_port_opened)
    {
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
    debug.set_debug("set_pectab: " + pectab);
    String result = "";
    if (this.write_data(pectab))
    {
      debug.set_debug("Writed");
      boolean data_available = false;
      int i = 0;
      try
      {
        debug.set_debug("Before while");
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
            // debug.set_debug("data not avaiable");
          }

        }
        debug.set_debug("After while");
      } catch (InterruptedException e)
      {
        debug.set_debug("Catch set pectab: " + e.getMessage());
        e.printStackTrace();
      }

    } else
    {
      debug.set_debug("serial ATP_ERR6");
      result = "ATP_ERR6";
    }

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
          debug.set_debug("send_raw_with_answer get_available_data():"+get_available_data());
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
  
  public static CommPortIdentifier get_comport_identifier(String comport_name)
  {
    Enumeration portList = CommPortIdentifier.getPortIdentifiers();

    while (portList.hasMoreElements())
    {
      CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
      if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL)
      {
        if(portId.getName().equals(comport_name))
        {
          debug.set_debug(comport_name + " Present");
          return portId;
        }
        
      }
    }
    debug.set_debug(comport_name + " NOT Present");
    return null;
  }
  
  public static void main(String[] args)
  {
    old_com_port comport_instance = new old_com_port();
    
    boolean answer = comport_instance.open_port("COM5", "9600", "8", "1", "NONE", false);
    comport_instance.set_dtr();
    comport_instance.set_rts();
    debug.set_debug("answer: " + answer);
    
    while (true)
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
        Thread.sleep(2000);
      }
      catch (InterruptedException ex)
      {
        debug.set_debug(ex.getMessage());
      }
    }

  }

  public String clean_read_string(String read_string)
  {
    char STX = '\2'; // START STRING
    char ETX = '\3'; // START STRING
    char FS = (char) 0x1C; // START STRING
    char GS = (char) 0x1D; // START STRING
    char DLE = (char) 0x10; // START STRING
    char CR = (char) 0x0D; // START STRING
    char LF = (char) 0x0A; // START STRING
    
    
    read_string = read_string.replace(""+STX, "");
    read_string = read_string.replace(""+ETX, "");
    read_string = read_string.replace(""+FS, "");
    read_string = read_string.replace(""+GS, "");
    read_string = read_string.replace(""+LF, "");
    read_string = read_string.replace(""+DLE, "");
    read_string = read_string.replace(""+CR, "\n");
    
    return read_string;
  }
}
