package peripheral.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class server
{

  public static XmlRpcClient client        = null;
  static String              XMLRPC_SERVER = "http://staging.inkcloud.io//test_listener.php ";

  String peripheral_type;
  String com_port;
  String baud_rate;
  String data_bit;
  String stop_bit;
  String parity;
  String is_serial_printer;



  public String[][] json_com_ports;
  
  public  server() throws FileNotFoundException, UnsupportedEncodingException
  {
    get_com_ports();
  }

  public boolean connect()
  {
    try
    {
      if (client == null)
      {
        client = new XmlRpcClient();
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(XMLRPC_SERVER));
        client.setConfig(config);
        System.out.println("XMLRPC Client created, Conecting to [" + XMLRPC_SERVER + "]");
      }
    } catch (Exception e)
    {
      System.out.println("Error on connect method [" + e.toString() + "]");
      return false;
    }
    return true;
  }

  public Object execute_xmlrpc(String method_name, Object[] params)
  {
    Object[] result = null;
    try
    {
      result = (Object[]) client.execute(method_name, params);
    } catch (Exception e)
    {
      System.out.println("Error execute_xmlrpc method: " + method_name + " - Error message: [" + e.getMessage() + "]");
    }
    return result;
  }

  public String execute_xmlrpc_string_result(String method_name, Object[] params)
  {
    String result = null;
    try
    {
      result = (String) client.execute(method_name, params);
    } catch (Exception e)
    {
      System.out.println("Error execute_xmlrpc method: " + method_name + " - Error message: [" + e.getMessage() + "]");
    }
    return result;
  }

  public void get_com_ports() throws FileNotFoundException, UnsupportedEncodingException  
  {


    System.out.println("->Get COM_PORTS");
    String R;
    if (this.connect() == true)
    {


        Object[] params = new Object[1];
        params[0]       = "";

        R =  this.execute_xmlrpc_string_result("get_com_ports", params);
        if (R == null)
        {
            System.out.println("Error receiving application from inkdcs - function get_com_ports");
        } else
        {
            JsonParser parser = new JsonParser();
            JsonArray gsonArr = parser.parse(R).getAsJsonArray();
            int c=0;
            for (JsonElement obj : gsonArr) 
            {
                c++;
            }
 
            
            json_com_ports = new String[c][7];

            
            int i        = 0;
            for (JsonElement obj : gsonArr) 
            {                      
                // Object of array
                JsonObject gsonObj = obj.getAsJsonObject();     
                    
                // Primitives elements of object      
                
                peripheral_type       = gsonObj.get("peripheral_type").getAsString();
                
                com_port              = gsonObj.get("com_port").getAsString();
                baud_rate             = gsonObj.get("baud_rate").getAsString();
                data_bit              = gsonObj.get("data_bit").getAsString();
                stop_bit              = gsonObj.get("stop_bit").getAsString();
                parity                = gsonObj.get("parity").getAsString();
                is_serial_printer     = gsonObj.get("is_serial_printer").getAsString();
                
                json_com_ports[i][0]     = peripheral_type;
                json_com_ports[i][1]     = com_port;
                json_com_ports[i][2]     = baud_rate;
                json_com_ports[i][3]     = data_bit;
                json_com_ports[i][4]     = stop_bit;
                json_com_ports[i][5]     = parity;
                json_com_ports[i][6]     = is_serial_printer;
                               
                
                i++;
            }  
        }
    }         
  }
}

