package peripheral.configuration;

public class configuration_universal
{
  
  public static int          DEVICE_SOFTWARE_ERROR        = 4;
  public static int          DEVICE_OK                    = 0;

  // ========== SERIAL CUSTOM PRINTERS

  public static int          WAIT_FOR_SERIAL_DATA_ANSWER  = 15;                                                          // Seconds, Average its 7 seconds for a BT, so 15 its a good time to avoid empty answers
  

  // ========== SERIAL COMPORT RAWDATA TIMEOUT

  public static int          COMPORT_RAW_DATA_TIMEOUT     = 1;                                                           // Seconds
  public static int          SLEEP_THREAD_OCR             = 500; //Mili Seconds     
  public static int          SLEEP_THREAD_BG              = 500; //Mili Seconds   
//
  // ========== KIOSK MANAGER STATUSES
  // PRINTER STATUSES
//
  public static String       PRINTER_OK                   = "PRINTER_OK";
  public static String       PAPER_OUT                    = "PAPER_OUT";
  public static String       PAPER_JAM                    = "PAPER_JAM";
  public static String       COVER_OPEN                   = "COVER_OPEN";
  public static String       CONNECTION_ERROR             = "CONNECTION_ERROR";

  // / KIOSK MANAGER TEXTS
  public static final String AVAILABLE_TEXT               = "AVAILABLE";
  public static final String UNAVAILABLE_TEXT             = "UNAVAILABLE";
  public static final String UNASSIGNED_TEXT              = "UNASSIGNED";
  
  
  // BGR COMMANDS
  public static final String INITIAL_COMMAND_1            = "CR";
  public static final String INITIAL_COMMAND_2            = "CR";
  public static final String COMMAND_AFTER_ANSWER         = "CR";
}
