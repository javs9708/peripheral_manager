package views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import peripheral.launcher.start;
import peripheral.manager.main_controller;

public class peripheral_manager_view extends javax.swing.JFrame
{

  int width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
  int height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

  File fichero = new File(".");

  main_controller m_controller;

  public peripheral_manager_view(main_controller main_controller_instance)
  {
    this.m_controller = main_controller_instance;
    this.setUndecorated(true);
    initComponents();

    main_panel.setBounds(0, 0, 950, 690);

    this.add(main_panel);
    this.setBounds(width / 2 - main_panel.getWidth() / 2, height / 2 - main_panel.getHeight() / 2, main_panel.getWidth(), main_panel.getHeight());

    BT.setContentAreaFilled(false);
    BP.setContentAreaFilled(false);
    OC.setContentAreaFilled(false);
    BG.setContentAreaFilled(false);
    SCALE.setContentAreaFilled(false);

    details.setVisible(false);

    back.setVisible(false);
    port_viewer_scroll.setVisible(false);

  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents()
  {

    main_panel = new javax.swing.JPanel();
    main_title = new javax.swing.JLabel();
    close_label = new javax.swing.JLabel();
    logo_ink = new javax.swing.JLabel();
    line = new javax.swing.JTextField();
    title = new javax.swing.JLabel();
    close_button = new javax.swing.JButton();
    details = new javax.swing.JPanel();
    device_details_panel = new javax.swing.JPanel();
    check_icon = new javax.swing.JLabel();
    device_details_label = new javax.swing.JLabel();
    status_label = new javax.swing.JLabel();
    COM_port_label = new javax.swing.JLabel();
    baud_rate_label = new javax.swing.JLabel();
    data_bits_label = new javax.swing.JLabel();
    stop_bits_label = new javax.swing.JLabel();
    parity_label = new javax.swing.JLabel();
    refresh_panel = new javax.swing.JPanel();
    refresh_label = new javax.swing.JLabel();
    refresh_button = new javax.swing.JButton();
    error_label = new javax.swing.JLabel();
    clean_panel = new javax.swing.JPanel();
    clean_label = new javax.swing.JLabel();
    clean_logo = new javax.swing.JLabel();
    clean_button = new javax.swing.JButton();
    data_received_panel_unique = new javax.swing.JPanel();
    data_received_unique_label = new javax.swing.JLabel();
    data_received_unique_logo = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    text_area_data_received_unique = new javax.swing.JTextArea();
    data_sent_panel = new javax.swing.JPanel();
    data_sent_label = new javax.swing.JLabel();
    send_icon = new javax.swing.JLabel();
    scroll_pane_data_sent = new javax.swing.JScrollPane();
    text_area_data_sent = new javax.swing.JTextArea();
    data_received_panel = new javax.swing.JPanel();
    data_received_label = new javax.swing.JLabel();
    data_received_icon = new javax.swing.JLabel();
    scroll_pane_data_received = new javax.swing.JScrollPane();
    text_area_data_received = new javax.swing.JTextArea();
    print_test_panel = new javax.swing.JPanel();
    print_label = new javax.swing.JLabel();
    print_icon = new javax.swing.JLabel();
    print_button = new javax.swing.JButton();
    buttons_panel = new javax.swing.JPanel();
    atb_panel = new javax.swing.JPanel();
    atb_title = new javax.swing.JLabel();
    check_atb = new javax.swing.JLabel();
    BT = new javax.swing.JButton();
    btp_panel = new javax.swing.JPanel();
    btp_title = new javax.swing.JLabel();
    check_btp = new javax.swing.JLabel();
    BP = new javax.swing.JButton();
    ocr_panel = new javax.swing.JPanel();
    check_ocr = new javax.swing.JLabel();
    ocr_title = new javax.swing.JLabel();
    OC = new javax.swing.JButton();
    bgr_panel = new javax.swing.JPanel();
    bgr_title = new javax.swing.JLabel();
    check_bgr = new javax.swing.JLabel();
    BG = new javax.swing.JButton();
    scale_panel = new javax.swing.JPanel();
    scale_title = new javax.swing.JLabel();
    check_scale = new javax.swing.JLabel();
    SCALE = new javax.swing.JButton();
    back = new javax.swing.JButton();
    port_viewer_scroll = new javax.swing.JScrollPane();
    com_text_area = new javax.swing.JTextArea();
    main_title1 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setBackground(new java.awt.Color(0, 0, 0));
    setLocation(new java.awt.Point(50, 50));
    setResizable(false);

    main_panel.setBackground(new java.awt.Color(245, 245, 245));
    main_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    main_title.setFont(new java.awt.Font("Proxima Nova Alt Lt", 0, 24)); // NOI18N
    main_title.setText("CUTE");
    main_panel.add(main_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 70, 60));

    close_label.setFont(new java.awt.Font("Proxima Nova Alt Th", 0, 18)); // NOI18N
    close_label.setText("Close and load applications");
    main_panel.add(close_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, -1, 45));

    logo_ink.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    logo_ink.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_ink.png"))); // NOI18N
    main_panel.add(logo_ink, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 590, 67, 69));

    line.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    main_panel.add(line, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 28, -1, 64));

    title.setFont(new java.awt.Font("Proxima Nova Alt Lt", 0, 18)); // NOI18N
    title.setText("Pheriperals");
    main_panel.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 151, 60));

    close_button.setBackground(java.awt.Color.white);
    close_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_bt.png"))); // NOI18N
    close_button.setBorderPainted(false);
    close_button.setContentAreaFilled(false);
    close_button.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        close_buttonActionPerformed(evt);
      }
    });
    main_panel.add(close_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 40, 47, 46));

    details.setBackground(new java.awt.Color(245, 245, 245));
    details.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    device_details_panel.setBackground(new java.awt.Color(51, 51, 51));

    check_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    check_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green_ok.png"))); // NOI18N

    device_details_label.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
    device_details_label.setForeground(new java.awt.Color(255, 255, 255));
    device_details_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    device_details_label.setText("Device Details");

    status_label.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
    status_label.setForeground(new java.awt.Color(255, 255, 255));
    status_label.setText("Loading...");

    COM_port_label.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
    COM_port_label.setForeground(new java.awt.Color(255, 255, 255));
    COM_port_label.setText("COM port  :");

    baud_rate_label.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
    baud_rate_label.setForeground(new java.awt.Color(255, 255, 255));
    baud_rate_label.setText("Baud rate :");

    data_bits_label.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
    data_bits_label.setForeground(new java.awt.Color(255, 255, 255));
    data_bits_label.setText("Data bits   :");

    stop_bits_label.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
    stop_bits_label.setForeground(new java.awt.Color(255, 255, 255));
    stop_bits_label.setText("Stop bits   :");

    parity_label.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
    parity_label.setForeground(new java.awt.Color(255, 255, 255));
    parity_label.setText("Parity        :");

    refresh_panel.setBackground(new java.awt.Color(102, 102, 102));
    refresh_panel.setLayout(null);

    refresh_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reload_w.png"))); // NOI18N
    refresh_panel.add(refresh_label);
    refresh_label.setBounds(50, 20, 50, 40);

    refresh_button.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    refresh_button.setForeground(new java.awt.Color(255, 255, 255));
    refresh_button.setText("Refresh");
    refresh_button.setContentAreaFilled(false);
    refresh_button.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        refresh_buttonActionPerformed(evt);
      }
    });
    refresh_panel.add(refresh_button);
    refresh_button.setBounds(0, 0, 290, 80);

    error_label.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
    error_label.setForeground(new java.awt.Color(255, 255, 255));

    javax.swing.GroupLayout device_details_panelLayout = new javax.swing.GroupLayout(device_details_panel);
    device_details_panel.setLayout(device_details_panelLayout);
    device_details_panelLayout.setHorizontalGroup(
      device_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(device_details_panelLayout.createSequentialGroup()
        .addGroup(device_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(device_details_panelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(refresh_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(device_details_panelLayout.createSequentialGroup()
            .addGap(29, 29, 29)
            .addComponent(check_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(device_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(device_details_panelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(device_details_label, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(device_details_panelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(device_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(COM_port_label, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                  .addComponent(baud_rate_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(status_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(error_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, device_details_panelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(device_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(data_bits_label, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(stop_bits_label, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(parity_label, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(32, 32, 32)))
        .addContainerGap())
    );
    device_details_panelLayout.setVerticalGroup(
      device_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(device_details_panelLayout.createSequentialGroup()
        .addGroup(device_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(device_details_panelLayout.createSequentialGroup()
            .addGap(54, 54, 54)
            .addComponent(check_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(device_details_panelLayout.createSequentialGroup()
            .addGap(28, 28, 28)
            .addComponent(device_details_label, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(error_label, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(COM_port_label, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(baud_rate_label)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(data_bits_label)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(stop_bits_label)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(parity_label)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
        .addComponent(refresh_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(28, 28, 28))
    );

    details.add(device_details_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 420));

    clean_panel.setBackground(new java.awt.Color(0, 153, 255));
    clean_panel.setLayout(null);

    clean_label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    clean_label.setForeground(new java.awt.Color(255, 255, 255));
    clean_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    clean_label.setText("Clean");
    clean_panel.add(clean_label);
    clean_label.setBounds(10, 110, 51, 20);

    clean_logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    clean_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/passport.png"))); // NOI18N
    clean_panel.add(clean_logo);
    clean_logo.setBounds(240, 40, 50, 60);

    clean_button.setContentAreaFilled(false);
    clean_button.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        clean_buttonActionPerformed(evt);
      }
    });
    clean_panel.add(clean_button);
    clean_button.setBounds(0, 0, 540, 140);

    details.add(clean_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, 540, 140));

    data_received_panel_unique.setBackground(new java.awt.Color(51, 51, 51));

    data_received_unique_label.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    data_received_unique_label.setForeground(new java.awt.Color(255, 255, 255));
    data_received_unique_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    data_received_unique_label.setText("Data Received");

    data_received_unique_logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    data_received_unique_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/receive.png"))); // NOI18N

    jScrollPane1.setBackground(new java.awt.Color(51, 51, 51));
    jScrollPane1.setBorder(null);
    jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

    text_area_data_received_unique.setEditable(false);
    text_area_data_received_unique.setBackground(new java.awt.Color(51, 51, 51));
    text_area_data_received_unique.setColumns(20);
    text_area_data_received_unique.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
    text_area_data_received_unique.setForeground(new java.awt.Color(255, 255, 255));
    text_area_data_received_unique.setRows(5);
    jScrollPane1.setViewportView(text_area_data_received_unique);

    javax.swing.GroupLayout data_received_panel_uniqueLayout = new javax.swing.GroupLayout(data_received_panel_unique);
    data_received_panel_unique.setLayout(data_received_panel_uniqueLayout);
    data_received_panel_uniqueLayout.setHorizontalGroup(
      data_received_panel_uniqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(data_received_panel_uniqueLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(data_received_unique_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(data_received_panel_uniqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(data_received_panel_uniqueLayout.createSequentialGroup()
            .addComponent(data_received_unique_label, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
        .addContainerGap())
    );
    data_received_panel_uniqueLayout.setVerticalGroup(
      data_received_panel_uniqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(data_received_panel_uniqueLayout.createSequentialGroup()
        .addGroup(data_received_panel_uniqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(data_received_panel_uniqueLayout.createSequentialGroup()
            .addGap(21, 21, 21)
            .addComponent(data_received_unique_label, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
          .addGroup(data_received_panel_uniqueLayout.createSequentialGroup()
            .addGap(47, 47, 47)
            .addComponent(data_received_unique_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );

    details.add(data_received_panel_unique, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 540, -1));

    data_sent_panel.setBackground(new java.awt.Color(51, 51, 51));
    data_sent_panel.setLayout(null);

    data_sent_label.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    data_sent_label.setForeground(new java.awt.Color(255, 255, 255));
    data_sent_label.setText("Data Sent");
    data_sent_panel.add(data_sent_label);
    data_sent_label.setBounds(48, 11, 69, 28);

    send_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send.png"))); // NOI18N
    data_sent_panel.add(send_icon);
    send_icon.setBounds(10, 45, 52, 43);

    scroll_pane_data_sent.setBorder(null);

    text_area_data_sent.setEditable(false);
    text_area_data_sent.setBackground(new java.awt.Color(51, 51, 51));
    text_area_data_sent.setColumns(20);
    text_area_data_sent.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
    text_area_data_sent.setForeground(new java.awt.Color(255, 255, 255));
    text_area_data_sent.setRows(5);
    scroll_pane_data_sent.setViewportView(text_area_data_sent);

    data_sent_panel.add(scroll_pane_data_sent);
    scroll_pane_data_sent.setBounds(60, 50, 180, 190);

    details.add(data_sent_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 260, 260));

    data_received_panel.setBackground(new java.awt.Color(51, 51, 51));
    data_received_panel.setLayout(null);

    data_received_label.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    data_received_label.setForeground(new java.awt.Color(255, 255, 255));
    data_received_label.setText("Data Received");
    data_received_panel.add(data_received_label);
    data_received_label.setBounds(51, 11, 93, 29);

    data_received_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/receive.png"))); // NOI18N
    data_received_panel.add(data_received_icon);
    data_received_icon.setBounds(10, 51, 47, 33);

    scroll_pane_data_received.setBorder(null);

    text_area_data_received.setEditable(false);
    text_area_data_received.setBackground(new java.awt.Color(51, 51, 51));
    text_area_data_received.setColumns(20);
    text_area_data_received.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
    text_area_data_received.setForeground(new java.awt.Color(255, 255, 255));
    text_area_data_received.setRows(5);
    scroll_pane_data_received.setViewportView(text_area_data_received);

    data_received_panel.add(scroll_pane_data_received);
    scroll_pane_data_received.setBounds(60, 50, 180, 190);

    details.add(data_received_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 260, 260));

    print_test_panel.setBackground(new java.awt.Color(0, 153, 255));
    print_test_panel.setLayout(null);

    print_label.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    print_label.setForeground(new java.awt.Color(255, 255, 255));
    print_label.setText("Print on test");
    print_test_panel.add(print_label);
    print_label.setBounds(10, 99, 110, 30);

    print_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print-128.png"))); // NOI18N
    print_test_panel.add(print_icon);
    print_icon.setBounds(250, 40, 50, 50);

    print_button.setContentAreaFilled(false);
    print_button.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        print_buttonActionPerformed(evt);
      }
    });
    print_test_panel.add(print_button);
    print_button.setBounds(0, 0, 540, 140);

    details.add(print_test_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, 540, 140));

    main_panel.add(details, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 870, 420));

    buttons_panel.setBackground(new java.awt.Color(245, 245, 245));
    buttons_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    atb_panel.setBackground(new java.awt.Color(255, 153, 0));
    atb_panel.setLayout(null);

    atb_title.setFont(new java.awt.Font("Proxima Nova Alt Lt", 0, 18)); // NOI18N
    atb_title.setForeground(new java.awt.Color(255, 255, 255));
    atb_title.setText("BT");
    atb_panel.add(atb_title);
    atb_title.setBounds(10, 124, 50, 20);

    check_atb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red_error.png"))); // NOI18N
    check_atb.setToolTipText("");
    atb_panel.add(check_atb);
    check_atb.setBounds(140, 10, 30, 30);

    BT.setBackground(new java.awt.Color(255, 153, 51));
    BT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print-128.png"))); // NOI18N
    BT.setAlignmentX(15.0F);
    BT.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
    BT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    BT.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        BTActionPerformed(evt);
      }
    });
    atb_panel.add(BT);
    BT.setBounds(0, 0, 180, 160);

    buttons_panel.add(atb_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 180, 160));

    btp_panel.setBackground(new java.awt.Color(51, 153, 0));
    btp_panel.setLayout(null);

    btp_title.setFont(new java.awt.Font("Proxima Nova Alt Lt", 0, 18)); // NOI18N
    btp_title.setForeground(new java.awt.Color(255, 255, 255));
    btp_title.setText("BP");
    btp_panel.add(btp_title);
    btp_title.setBounds(10, 127, 50, 20);

    check_btp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red_error.png"))); // NOI18N
    btp_panel.add(check_btp);
    check_btp.setBounds(140, 13, 30, 30);

    BP.setBackground(new java.awt.Color(153, 204, 0));
    BP.setForeground(new java.awt.Color(255, 255, 255));
    BP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print-128.png"))); // NOI18N
    BP.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        BPActionPerformed(evt);
      }
    });
    btp_panel.add(BP);
    BP.setBounds(0, 0, 180, 160);

    buttons_panel.add(btp_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 180, 160));

    ocr_panel.setBackground(new java.awt.Color(153, 153, 255));
    ocr_panel.setLayout(null);

    check_ocr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red_error.png"))); // NOI18N
    ocr_panel.add(check_ocr);
    check_ocr.setBounds(140, 13, 30, 30);

    ocr_title.setFont(new java.awt.Font("Proxima Nova Alt Lt", 0, 18)); // NOI18N
    ocr_title.setForeground(new java.awt.Color(255, 255, 255));
    ocr_title.setText("OC");
    ocr_panel.add(ocr_title);
    ocr_title.setBounds(10, 127, 50, 20);

    OC.setBackground(new java.awt.Color(102, 0, 102));
    OC.setForeground(new java.awt.Color(255, 255, 255));
    OC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/passport.png"))); // NOI18N
    OC.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        OCActionPerformed(evt);
      }
    });
    ocr_panel.add(OC);
    OC.setBounds(0, 0, 180, 160);

    buttons_panel.add(ocr_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 180, 160));

    bgr_panel.setBackground(new java.awt.Color(0, 153, 153));
    bgr_panel.setLayout(null);

    bgr_title.setFont(new java.awt.Font("Proxima Nova Alt Lt", 0, 18)); // NOI18N
    bgr_title.setForeground(new java.awt.Color(255, 255, 255));
    bgr_title.setText("BG");
    bgr_panel.add(bgr_title);
    bgr_title.setBounds(10, 127, 50, 20);

    check_bgr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red_error.png"))); // NOI18N
    bgr_panel.add(check_bgr);
    check_bgr.setBounds(140, 13, 30, 30);

    BG.setBackground(new java.awt.Color(0, 204, 153));
    BG.setForeground(new java.awt.Color(255, 255, 255));
    BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barcode.png"))); // NOI18N
    BG.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        BGActionPerformed(evt);
      }
    });
    bgr_panel.add(BG);
    BG.setBounds(0, 0, 180, 160);

    buttons_panel.add(bgr_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 180, 160));

    scale_panel.setBackground(new java.awt.Color(153, 0, 51));
    scale_panel.setLayout(null);

    scale_title.setFont(new java.awt.Font("Proxima Nova Alt Lt", 0, 18)); // NOI18N
    scale_title.setForeground(new java.awt.Color(255, 255, 255));
    scale_title.setText("SCALE");
    scale_panel.add(scale_title);
    scale_title.setBounds(10, 127, 150, 20);

    check_scale.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red_error.png"))); // NOI18N
    scale_panel.add(check_scale);
    check_scale.setBounds(140, 13, 30, 30);

    SCALE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/small_scale.png"))); // NOI18N
    SCALE.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        SCALEActionPerformed(evt);
      }
    });
    scale_panel.add(SCALE);
    SCALE.setBounds(0, 0, 180, 160);

    buttons_panel.add(scale_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 180, 160));

    main_panel.add(buttons_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 720, 420));

    back.setBackground(new java.awt.Color(255, 255, 255));
    back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back_bt.png"))); // NOI18N
    back.setBorderPainted(false);
    back.setContentAreaFilled(false);
    back.setHideActionText(true);
    back.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    back.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        backActionPerformed(evt);
      }
    });
    main_panel.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 60, 60));

    com_text_area.setColumns(20);
    com_text_area.setRows(5);
    port_viewer_scroll.setViewportView(com_text_area);

    main_panel.add(port_viewer_scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 590, 150, 50));

    main_title1.setFont(new java.awt.Font("Proxima Nova Alt Lt", 0, 24)); // NOI18N
    main_title1.setForeground(new java.awt.Color(243, 54, 54));
    main_title1.setText("Ink");
    main_panel.add(main_title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 40, 60));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 55, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void close_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_buttonActionPerformed
      System.exit(0);
    }//GEN-LAST:event_close_buttonActionPerformed

    private void BTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTActionPerformed
      title.setText("BT");
      buttons_panel.setVisible(false);
      details.setVisible(true);

      data_sent_panel.setVisible(true);
      data_received_panel.setVisible(true);
      print_test_panel.setVisible(true);

      data_received_panel_unique.setVisible(false);
      clean_panel.setVisible(false);
      back.setVisible(true);
      m_controller.load_peripheral();

    }//GEN-LAST:event_BTActionPerformed

    private void OCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OCActionPerformed
      title.setText("OC");
      buttons_panel.setVisible(false);
      details.setVisible(true);

      data_sent_panel.setVisible(false);
      data_received_panel.setVisible(false);
      print_test_panel.setVisible(false);

      data_received_panel_unique.setVisible(true);
      clean_panel.setVisible(true);

      back.setVisible(true);
      m_controller.load_peripheral();
    }//GEN-LAST:event_OCActionPerformed

    private void BGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGActionPerformed
      title.setText("BG");
      buttons_panel.setVisible(false);
      details.setVisible(true);

      data_sent_panel.setVisible(false);
      data_received_panel.setVisible(false);
      print_test_panel.setVisible(false);

      data_received_panel_unique.setVisible(true);
      clean_panel.setVisible(true);

      back.setVisible(true);
      m_controller.load_peripheral();
    }//GEN-LAST:event_BGActionPerformed

    private void BPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPActionPerformed
      title.setText("BP");
      buttons_panel.setVisible(false);
      details.setVisible(true);

      data_sent_panel.setVisible(true);
      data_received_panel.setVisible(true);
      print_test_panel.setVisible(true);

      data_received_panel_unique.setVisible(false);
      clean_panel.setVisible(false);
      back.setVisible(true);
      m_controller.load_peripheral();
    }//GEN-LAST:event_BPActionPerformed

    private void SCALEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SCALEActionPerformed
      title.setText("SCALE");
      buttons_panel.setVisible(false);
      details.setVisible(true);

      data_sent_panel.setVisible(false);
      data_received_panel.setVisible(false);
      print_test_panel.setVisible(false);

      data_received_panel_unique.setVisible(true);
      clean_panel.setVisible(true);

      back.setVisible(true);
      m_controller.load_peripheral();
    }//GEN-LAST:event_SCALEActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
      back.setVisible(false);
      details.setVisible(false);
      buttons_panel.setVisible(true);
      title.setText("Peripherals");
      text_area_data_sent.setText("");
      text_area_data_received.setText("");
      text_area_data_received_unique.setText("");
      new Thread(new Runnable()
      {
        @Override
        public void run()
        {
          m_controller.available_buttons();
        }
      }).start();
    }//GEN-LAST:event_backActionPerformed

    private void clean_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clean_buttonActionPerformed

      text_area_data_received_unique.setText("");
    }//GEN-LAST:event_clean_buttonActionPerformed

    private void refresh_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_buttonActionPerformed
      m_controller.refresh_peripheral();
      text_area_data_sent.setText("");
      text_area_data_received.setText("");

    }//GEN-LAST:event_refresh_buttonActionPerformed

    private void print_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_buttonActionPerformed

      m_controller.test_printer_action();

    }//GEN-LAST:event_print_buttonActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton BG;
  private javax.swing.JButton BP;
  private javax.swing.JButton BT;
  public static javax.swing.JLabel COM_port_label;
  private javax.swing.JButton OC;
  private javax.swing.JButton SCALE;
  private javax.swing.JPanel atb_panel;
  private javax.swing.JLabel atb_title;
  private javax.swing.JButton back;
  public static javax.swing.JLabel baud_rate_label;
  private javax.swing.JPanel bgr_panel;
  private javax.swing.JLabel bgr_title;
  private javax.swing.JPanel btp_panel;
  private javax.swing.JLabel btp_title;
  private javax.swing.JPanel buttons_panel;
  public javax.swing.JLabel check_atb;
  public javax.swing.JLabel check_bgr;
  public javax.swing.JLabel check_btp;
  public static javax.swing.JLabel check_icon;
  public javax.swing.JLabel check_ocr;
  public javax.swing.JLabel check_scale;
  private javax.swing.JButton clean_button;
  private javax.swing.JLabel clean_label;
  private javax.swing.JLabel clean_logo;
  private javax.swing.JPanel clean_panel;
  private javax.swing.JButton close_button;
  private javax.swing.JLabel close_label;
  public javax.swing.JTextArea com_text_area;
  public static javax.swing.JLabel data_bits_label;
  private javax.swing.JLabel data_received_icon;
  private javax.swing.JLabel data_received_label;
  private javax.swing.JPanel data_received_panel;
  private javax.swing.JPanel data_received_panel_unique;
  private javax.swing.JLabel data_received_unique_label;
  private javax.swing.JLabel data_received_unique_logo;
  private javax.swing.JLabel data_sent_label;
  private javax.swing.JPanel data_sent_panel;
  private javax.swing.JPanel details;
  private javax.swing.JLabel device_details_label;
  private javax.swing.JPanel device_details_panel;
  public static javax.swing.JLabel error_label;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextField line;
  private javax.swing.JLabel logo_ink;
  private javax.swing.JPanel main_panel;
  private javax.swing.JLabel main_title;
  private javax.swing.JLabel main_title1;
  private javax.swing.JPanel ocr_panel;
  private javax.swing.JLabel ocr_title;
  public static javax.swing.JLabel parity_label;
  private javax.swing.JScrollPane port_viewer_scroll;
  public javax.swing.JButton print_button;
  private javax.swing.JLabel print_icon;
  private javax.swing.JLabel print_label;
  private javax.swing.JPanel print_test_panel;
  private javax.swing.JButton refresh_button;
  private javax.swing.JLabel refresh_label;
  private javax.swing.JPanel refresh_panel;
  private javax.swing.JPanel scale_panel;
  private javax.swing.JLabel scale_title;
  private javax.swing.JScrollPane scroll_pane_data_received;
  private javax.swing.JScrollPane scroll_pane_data_sent;
  private javax.swing.JLabel send_icon;
  public static javax.swing.JLabel status_label;
  public static javax.swing.JLabel stop_bits_label;
  public static javax.swing.JTextArea text_area_data_received;
  public javax.swing.JTextArea text_area_data_received_unique;
  public javax.swing.JTextArea text_area_data_sent;
  public javax.swing.JLabel title;
  // End of variables declaration//GEN-END:variables
}
