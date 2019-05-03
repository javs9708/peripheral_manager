package peripheral.configuration;

import java.awt.Color;
import java.util.HashMap;
import java.util.Vector;

public class configuration_universal
{
  
  public static String       SOFT_VERSION                 = "3.005";                                                     //VERSION WITH UPDATE FUNCTIONS
  public static String       SOFT_VERSION_PATH            = "c:\\cuss\\platform\\";
  public static int          IS_DEBUG                     = 1;
  public static String       ie_path                      = "c:\\Program Files\\Internet Explorer\\iexplore.exe ";
  public static String       ie_path_2                    = "c:\\Archivos de programa\\Internet Explorer\\iexplore.exe ";
  public static String       PLATFORM_PATH                = "/cuss_kiosk_launch.php?atempt=0&mk=";

  // CONFIG FILES
  public static String       SERVER_INI_PATH              = "c:\\cuss\\platform\\inkserver.ini";
  public static String       MACHINE_KEY_PATH             = "c:\\cuss\\platform\\machine_key.ini";
  public static String       MACHINE_KEY                  = "";

  public static String       INK_SERVER                   = "http://webdcs";
  public static String       XMLRPC_SCRIPT                = "/cuss_kiosk_listener.php";
  public static String       XMLRPC_SERVER                = INK_SERVER + XMLRPC_SCRIPT;
  public static String       DEFAULT_DESKTOP_NAME         = "Default";
  public static boolean      KIOSK_MODE                   = true;
  
  public static int          ACTIVE_APP_MAX_MINUTES       = 10;                                                          // default 10
  public static int          SINGLE_MODE_NO_NOTIFY_MAX_MINUTES = 10;                                                          // default 10 Single mode

  public static int          RC_OK                        = 0;
  public static int          RC_WRONG_APP_STATE           = -2;
  public static int          RC_NOT_SUPPORTED             = -10;

  public static int          RC_WRONG_DEVICE_STATE        = 2;
  public static int          RC_DEVICE_TIMEOUT            = 1;
  public static int          RC_DEVICE_NOT_REACHABLE      = 304;
  public static int          RC_DEVICE_NOT_RESPONDING     = 305;

  public static int          EVENT_RELEASE_READY          = 7;
  public static int          EVENT_RELEASE_UNAVAILABLE    = 8;
  public static int          EVENT_OK                     = 1;
  public static int          EVENT_READY                  = 203;
  public static int          EVENT_HANDLING_UNAVAILABLE   = 3;

  public static int          DEVICE_SOFTWARE_ERROR        = 4;
  public static int          DEVICE_OK                    = 0;
  public static int          DEVICE_PAPER_LOW             = 107;

  public static Vector       AVAILABLE_APPS_VECTOR        = new Vector();
  public static Vector       DISABLE_APPS_VECTOR          = new Vector();

  public static String       INK_ADMINISTRATOR_TOKEN      = "INK_ADMINISTRATOR";

  public static int          TIME_TO_CHECK_APP_PROCESS    = 5;                                                           // seconds

  public static int          TIME_SEND_EVENT              = 100;

  public static boolean      SEND_OCR_STATUS              = true;

  public static boolean      SHOW_MSR_STRING              = true;
  
  public static boolean      SEND_BLACK_MARK              = true;
  public static String       BLACK_MARK_TEXT              = "EP#DETECTION=BLACKMK";

  public static int          APP_RESTART_MAX_TIMES        = 10;

  // ========== TIME TO CHECK PERIPHERALS STATE
  public static int          TIME_BEFORE_CALL_ANY_STATUS  = 0;                                                           // 0 secs
  public static int          TIME_CALLBACK_IP_PRINTER     = 20000;                                                       // 20 secs
  public static int          TIME_CHECK_ATB_IP            = 90000;                                                       // 90 secs
  public static int          TIME_CHECK_ATB_ZEBRA         = 30000;                                                       // 30 secs
  public static int          TIME_CHECK_OTHER_PERIPHERALS = 30000;                                                       // 30 secs
  public static int          TIME_CHECK_SERIAL_PRINTER    = 30000;                                                       // 30 secs

  // ========== SELECTED PERIPHERALS
  public static int          OCR_SUPPLIER                 = 0;                                                           // (0 = OCR 3M) - (1 = OCR ACCESS)
  public static boolean      IS_USB_ATB                   = false;                                                       // (false = ATB_IP) - (true = ATB_USB)
  public static boolean      IS_USB_BTP                   = false;                                                       // (false = BTP_IP) - (true = BTP_USB)
  public static boolean      IS_SERIAL_ATB                = false;                                                       // (false = ATB_IP|ATB_USB) - (true = ATB_SERIAL)
  public static boolean      IS_SERIAL_BTP                = false;                                                       // (false = BTP_IP|BTP_USB) - (true = BTP_SERIAL)

  // ========== RESOLUTION VARIABLES
  public static String       CURRENT_RESOLUTION;
  public static String       DEFAULT_RESOLUTION           = null;

  // ========== LOG VARIABLES
  public static String       LOG_FILES_FOLDER_PATH        = "c:\\cuss\\platform logs\\";
  
  public static String       LOG_FOLDER_PATH_NETWORK      = "c:\\cuss\\platform logs\\network\\";
  
  public static HashMap      LOG_OBJECTS                  = new HashMap();
  public static boolean      ACTIVE_LOG                   = true;
  public static int          MAX_LOGS_ONLIINE             = 7; // 7 days
  public static int          MAX_NETWORK_LOGS_ONLIINE     = 7; // 7 days
  
  public static String       KEY_WORD                     = "ink_cuss_platform";
  public static String       LOG_ONLY_THIS_CLASS          = "";
  public static String       LOG_TYPE_WARNING             = " * WARNING > ";
  public static String       LOG_TYPE_ERROR               = " * ERROR   > ";
  public static String       LOG_TYPE_DEBUG               = " - DEBUG   > ";
  public static String       LOG_TYPE_DEVICE_OK           = " - DEVICE  > ";

  // ========== ZEBRA PRINTER DATA
  public static String       ZEBRA_PRINTER_VID            = "Vid_088c";
  public static final int    ZEBRA_BP_TIME_AFTER_PRINT    = 5000;                                                        // miliseconds, time to check status of zebra BP printer after send pectab to print

  // ========== BCR KEYBOARD WEDGE SOCKET INFO

  public static int          BCR_SOCKET_PORT              = 8456;                                                        // do not change this port value 8456

  // ========== SERIAL CUSTOM PRINTERS

  public static int          WAIT_FOR_SERIAL_DATA_ANSWER  = 15;                                                          // Seconds, Average its 7 seconds for a BT, so 15 its a good time to avoid empty answers

  // ========== PECTAB START WITH STRING

  public static String       BT_HEADER                    = "BTT";
  public static String       BT_BODY                      = "BTP";

  public static String       BP_HEADER                    = "PT";
  public static String       BP_BODY                      = "CP";

  // ========== SCALE PARAMETERS

  // public static String SCALE_COMPORT = "";
  // public static String SCALE_BAUDRATE = "";

  // ========== SERIAL COMPORT RAWDATA TIMEOUT

  public static int          COMPORT_RAW_DATA_TIMEOUT     = 1;                                                           // Seconds

  // ========== KIOSK MANAGER ERRORS
  // PRINTER ERRORS

  public static String       PAPER_OUT                    = "PAPER_OUT";
  public static String       PAPER_JAM                    = "PAPER_JAM";
  public static String       COVER_OPEN                   = "COVER_OPEN";
  public static String       CONNECTION_ERROR             = "CONNECTION_ERROR";

  // HEARTBEAT

  public static int          HEARTBEAT_SLEEP              = 30;                                                          // default 30 mins
  public static final String TEMP_APP_TRANSITION_PATH     = "c:\\cuss\\platform\\temp\\transitions.ini";

  public static final int    MIN_FREE_RAM_KIOSK           = 512;                                                         // (Mb) If RAM is lower than this value a warning will be send.
  public static final int    MAX_CPU_USAGE                = 98;                                                          // MAX PERCENTAGE
  public static final double MIN_HARD_DISK_SPACE          = 5;                                                           // (Gb)

  public static final int    BP_SELECTED                  = 1;
  public static final int    BT_SELECTED                  = 2;
  public static final int    OCR_SELECTED                 = 3;
  public static final int    BCR_SELECTED                 = 4;
  public static final int    SCALE_SELECTED               = 5;

  public static final String COMPORT_CHANGES_FILE_PATH    = "c:\\cuss\\platform\\temp\\comport_changes.ini";

  public static final String RAM_ALERT_FILE_PATH          = "c:\\cuss\\platform\\temp\\ram_alert.ini";
  public static final String CPU_ALERT_FILE_PATH          = "c:\\cuss\\platform\\temp\\cpu_alert.ini";
  public static final String HD_ALERT_FILE_PATH           = "c:\\cuss\\platform\\temp\\hd_alert.ini";

  // UPDATER

  public static String       UPDATE_INI_PATH              = "c:\\cuss\\platform\\updates\\update.ini";

  // / COLORS KIOSK MANAGER

  public static Color        COLOR_BG_GREY                = new Color(247, 247, 247);
  public static Color        COLOR_BT_BLUE                = new Color(33, 150, 243);
  public static Color        COLOR_BT_GREEN               = new Color(76, 175, 80);
  public static Color        COLOR_BT_AQUA_BLUE           = new Color(0, 150, 136);
  public static Color        COLOR_BT_RED                 = new Color(244, 67, 54);
  public static Color        COLOR_BT_ORANGE              = new Color(255, 152, 0);
  public static Color        COLOR_BT_LIGHT_GREEN         = new Color(139, 195, 74);
  public static Color        COLOR_BT_PURPLE              = new Color(103, 58, 183);
  public static Color        COLOR_BT_GREY                = new Color(96, 125, 139);
  public static Color        COLOR_BT_BLACK               = new Color(33, 33, 33);
  public static Color        COLOR_BT_DARK_GREY           = new Color(76, 76, 76);

  // / KIOSK MANAGER TEXTS
  public static final String AVAILABLE_TEXT               = "AVAILABLE";
  public static final String UNAVAILABLE_TEXT             = "UNAVAILABLE";
  public static final String UNASSIGNED_TEXT              = "UNASSIGNED";

  public static final int    MAX_STACKTRACE               = 8;
 
  
  // COMPORT BCR CONFIG
  public static final boolean USE_NEW_BCR_COMMANDS        = false; // NEW ('E':Enable - 'D':Disable)   -   OLD ('\2':Enable - '\3':Disable')
  
 
  // ATB zebra KR403 USB pectab impl
  public static final String NO_HEADER                    = "NO_HEADER";                
  
  // ACCESS USB BCR
  public static final boolean USE_ACCESS_BCR_IF_AVAILABLE = false;
  
  public static final String SOUND_STRINGS                = "C:/cuss/platform/sounds/strings.wav";
  public static final String SOUND_ALARM                  = "C:/cuss/platform/sounds/alarm.wav";
  public static final String IMAGE_NETWORK_DOWN           = "C:/cuss/platform/images/spinner1.gif";
  
  public static int RESTART_BCR_ON_SECONDS                = 4;
  
  
  public static final long WAIT_DATA_SLEEP_DOOR_SENSOR    = 10000;// MILISECONDS
  public static final long CONNECT_RETRY_DOOR_SENSOR      = 30000;// MILISECONDS 
  
  /// PERIPHERAL SIMULATION - MUST BE FALSE TO COMPILE PRODUCTION
  public static final boolean SIMULATE_BP_PRINTER         = false;
  public static final boolean TEST_DOOR_SENSOR_BCR        = false;
  
  public static final String NETWORK_DOWN_TEXT            = "Looking for network...";
  public static final long SLEEP_BEFORE_PLATFORM_START    = 10000;
  
  public static String       LOG_ST_KIOSK_K               = "&log_st_kiosk=";
  public static final Object LOG_ST_KIOSK                 = "30B3E621E517D9BA17507FAB160EF5F22B5EE435FA172D56CF572835F3508FE4FF07D89326323BF3972A9FCA77098513FF17B4217BDCF002F26300395145355F";

  public static final String INACTIVE_SCREEN_PATH         = "C:/cuss/platform/inactive_screen.ini";
  
}