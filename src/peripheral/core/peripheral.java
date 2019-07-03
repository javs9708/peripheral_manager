/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.core;

import peripheral.configuration.configuration_universal;
import peripheral.logs.debug;
import peripheral.serial.com_port;
import peripheral.serial.j_com_port;
import peripheral.serial.old_com_port;

/**
 *
 * @author kiosk
 */
public class peripheral
{

  public String com_port_name, baud_rate, data_bits, stop_bits, parity;
  public boolean is_serial_printer;
  public String var_last_status = "";
  public com_port comport_instance;
  private boolean use_old_comport_library = false;

  public peripheral(String com_port_name, String baud_rate, String data_bits, String stop_bits, String parity, boolean is_serial_printer, boolean use_old_comport_library)
  {
    this.com_port_name = com_port_name;
    this.baud_rate = baud_rate;
    this.data_bits = data_bits;
    this.stop_bits = stop_bits;
    this.parity = parity;
    this.is_serial_printer = is_serial_printer;
    this.use_old_comport_library = use_old_comport_library;
    start_comport();
  }

  public void start_comport()
  {
    if(use_old_comport_library)
    {
      comport_instance = new old_com_port();
      comport_instance.open_port(com_port_name, baud_rate, data_bits, stop_bits, parity, is_serial_printer);
    }
    else
    {
      comport_instance = new j_com_port();
      comport_instance.open_port(com_port_name, baud_rate, data_bits, stop_bits, parity, is_serial_printer);
    }
  }

  protected boolean is_status_ok()
  {
    return false;
  }
  
  boolean last_available_answer = false;
  boolean waiting_for_is_available = false;
  
  public boolean is_available()
  {
    if (!waiting_for_is_available)
    {
      waiting_for_is_available = true;

      if (comport_instance.is_open() == configuration_universal.DEVICE_OK && is_status_ok() && comport_instance.isCTS() && comport_instance.isDSR())
      {
        waiting_for_is_available = false;
        last_available_answer = true;
        return true;
      }
      else
      {
        comport_instance.close_port();
        comport_instance.open_port(com_port_name, baud_rate, data_bits, stop_bits, parity, is_serial_printer);
       
        waiting_for_is_available = false;
        last_available_answer = false;
        return false;
      }
    }
    else
    {
      debug.set_debug("Busy waiting for is available");
      return last_available_answer;
    }
  }

  public String send_pectab(String pectab)
  {
    return comport_instance.set_pectab(pectab);
  }
}
