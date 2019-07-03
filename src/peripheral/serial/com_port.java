/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.serial;

import peripheral.configuration.configuration_universal;
import peripheral.logs.debug;

/**
 *
 * @author kiosk
 */
public class com_port
{
  public boolean open_port(String com_port_name, String baud_rate, String data_bits, String stop_bits, String parity, boolean is_serial_printer)
  {
    debug.set_debug("Method not implemented");
    return false;
  }
  
  public String get_available_data()
  {
    debug.set_debug("Method not implemented");
    return "";
  }
  
  public int is_open()
  {
    debug.set_debug("Method not implemented");
    return configuration_universal.DEVICE_SOFTWARE_ERROR;
  }
  
  public void close_port()
  {
   debug.set_debug("Method not implemented");
  }
  
  public String set_pectab(String pectab)
  {
    debug.set_debug("Method not implemented");
    return "";
  }
  
  public boolean isCTS()
  {
    debug.set_debug("Method not implemented");
    return false;
  }
  
  public boolean isDSR()
  {
    debug.set_debug("Method not implemented");
    return false;
  }
  
  public String get_com_port_name()
  {
    debug.set_debug("Method not implemented");
    return "";
  }
  
  public String get_baud_rate()
  {
    debug.set_debug("Method not implemented");
    return "";
  }
  
  public String get_data_bits()
  {
    debug.set_debug("Method not implemented");
    return "";
  }
  
  public String get_stop_bits()
  {
    debug.set_debug("Method not implemented");
    return "";
  }
  
  public String get_parity()
  {
    debug.set_debug("Method not implemented");
    return "";
  }
  
  public void clean()
  {
    debug.set_debug("Method not implemented");
  }
  
  public boolean set_dtr()
  {
    debug.set_debug("Method not implemented");
    return false;
  }
  
  public boolean set_rts()
  {
    debug.set_debug("Method not implemented");
    return false;
  }
}
