/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.core;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import peripheral.configuration.configuration_universal;
/**
 *
 * @author kiosk
 */
public class helper
{
  
  public static String read_lines_from_file(String path)
	{
		String record = null;
		String lines = "";
		try
		{
			FileInputStream fis = new FileInputStream(path);
			BufferedInputStream bis = new BufferedInputStream(fis);
			DataInputStream dis = new DataInputStream(bis);

			String last_line = "";

			while ((record = dis.readLine()) != null)
			{
				last_line = record.trim();

				lines += last_line;
			}

			fis.close();
			bis.close();
			dis.close();
			return lines;

		}
		catch (Exception e)
		{
			return null;
		}
	}
  
  public static String get_xmlrpc_server_address()
  {
    if(configuration_universal.SERVER_ADDRESS.equals(""))
    {
      configuration_universal.SERVER_ADDRESS = read_lines_from_file(configuration_universal.SERVER_FILE_PATH);
    }
    return configuration_universal.SERVER_ADDRESS + configuration_universal.SERVER_LISTENER;
  }
}
