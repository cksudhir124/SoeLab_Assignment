import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.BorderLayout;
import java.util.Vector;


public class School_Details
{
    public static void main(String[] args) throws Exception
    {
        JFrame f=new JFrame("Details of Student");
        JButton b,b1;
        b=new JButton("ADD");
        b1=new JButton("SHOW");
        b.setBounds(100,50,100,30);
        b1.setBounds(100,100,100,30);
    // b button for enter a new frame to fill the all required details of student.    
        b.addActionListener(new ActionListener()
        {  
               public void actionPerformed(ActionEvent e)
               {  
                   f.setVisible(false);
                   JFrame jf=new JFrame("Insert Student Details");
                   JLabel l1,l2,l3,l4,l5;
                   JTextField t1,t2,t3,t4,t5;
                   JButton jb;
                   
                   l1=new JLabel("Name");
                   l2=new JLabel("Father's Name");
                   l3=new JLabel("Date of Birth");
                   l4=new JLabel("Address");
                   l5=new JLabel("Course");
                   jb=new JButton("OK");
                   
                   t1=new JTextField("");
                   t2=new JTextField("");
                   t3=new JTextField("");
                   t4=new JTextField("");
                   t5=new JTextField("");
                   
                   l1.setBounds(100,50,100,30);
                   t1.setBounds(100,80,200,30);
                   l2.setBounds(100,110,200,30);
                   t2.setBounds(100,140,200,30);
                   l3.setBounds(100,170,200,30);
                   t3.setBounds(100,200,200,30);
                   l4.setBounds(100,230,100,30);
                   t4.setBounds(100,280,200,30);
                   l5.setBounds(100,310,100,30);
                   t5.setBounds(100,340,200,30);
                   jb.setBounds(200,390,100,30);
                   
          //jb button for insert the filled student of details into database.         
                   jb.addActionListener(new ActionListener()
                   {  
                        public void actionPerformed(ActionEvent e)
                        { 
                                    f.setVisible(true);
                                    jf.setVisible(false);
                                    String n=t1.getText();
                                    String fn=t2.getText();
                                    String d=t3.getText();
                                    String ad=t4.getText();
                                    String cou=t5.getText();
                                    
                                  
                                    String dbURL1 = "jdbc:mysql://localhost:3306/School";  //school database name
                                    String username1 ="root";
                                    String password1 = "xyz";  //xyz=database root password.
                                    Connection dbCon1 = null;
                                    String query1 = " insert into student (name,fname,dob,address,course)"
                                                 + " values (?, ?, ?, ?, ?)";
                                    try {
                                        dbCon1 = DriverManager.getConnection(dbURL1,username1,password1);
                                        PreparedStatement prt1 = dbCon1.prepareStatement(query1);
                                        prt1.setString (1,n);
                                        prt1.setString (2,fn);
                                        prt1.setString (3,d);
                                        prt1.setString (4,ad);
                                        prt1.setString (5,cou);
                                        prt1.execute();
                                         
                                    } catch (SQLException ex) {
                                        Logger.getLogger(School_Details.class.getName()).log(Level.SEVERE, null, ex);
                                    }       
                        }
                    });
                     
                   jf.add(l1);
                   jf.add(t1);
                   jf.add(l2);
                   jf.add(t2);
                   jf.add(l3);
                   jf.add(t3);
                   jf.add(l4);
                   jf.add(t4);
                   jf.add(l5);
                   jf.add(t5);
                   jf.add(jb);
                   
                   jf.setSize(500,800);
                   jf.setLayout(null);
                   jf.setVisible(true);
                   
               }
        });
     // b1 button for showing the requied JTable of student details,which will be fetch from database table.   
        b1.addActionListener(new ActionListener()
        {  
               public void actionPerformed(ActionEvent e)
               {  
                   f.setVisible(false);
                   String dbURL2 = "jdbc:mysql://localhost:3306/School";
                   String username2 ="root";
                   String password2 = "xyz";  // xyz is database root password.
                   Connection con = null;
                   Statement st = null; 
                   ResultSet rs = null; 
                   String s;
                   
                   try
                   { 
                       con = DriverManager.getConnection(dbURL2,username2,password2);
                       st = con.createStatement();
                       s = "select * from student order by name asc"; 
                       rs = st.executeQuery(s);
                       ResultSetMetaData rsmt = rs.getMetaData(); 
                       int c = rsmt.getColumnCount();
                       Vector column = new Vector(c);
                       for(int i = 1; i <= c; i++) 
                       {
                            column.add(rsmt.getColumnName(i)); 
                       }
                       Vector data = new Vector(); 
                       Vector row = new Vector(); 
                       while(rs.next()) 
                       { 
                           row = new Vector(c); 
                           for(int i = 1; i <= c; i++)
                           {
                                row.add(rs.getString(i)); 
                           } 
                           data.add(row); 
                       }
                       JFrame frame = new JFrame("Show Student Details"); 
                       frame.setSize(500,800); 
                       frame.setLocationRelativeTo(null); 
                       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                       JPanel panel = new JPanel(); 
                       JTable table = new JTable(data,column);
                       JScrollPane jsp = new JScrollPane(table); 
                       panel.setLayout(new BorderLayout()); 
                       panel.add(jsp,BorderLayout.CENTER); 
                       frame.setContentPane(panel); 
                       frame.setVisible(true); 
                   }
                   catch(Exception ex)
                   { 
                       JOptionPane.showMessageDialog(null, "ERROR"); 
                   }
                   finally
                   {
                      try
                      { 
                          st.close(); 
                          rs.close(); 
                          con.close(); 
                      }
                      catch(Exception ex)
                      { 
                          JOptionPane.showMessageDialog(null, "ERROR CLOSE"); 
                      }
                   } 
                  
               }
               
        });
     
        f.add(b);
        f.add(b1);
        f.setSize(500,800);
        f.setLayout(null);
        f.setVisible(true);
    }
    
}
