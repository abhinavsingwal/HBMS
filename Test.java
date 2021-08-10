
import java.util.*; //IMPORTING PACKAGES
import java.sql.*;

/*
* HOSPITAL CLASS IS THE SUPER CLASS OF HOSPITAL BED MANAGEMENT SYSTEM 

* HOSPITAL CLASS CONTAINS NAME AND ADDRESS VARIABLES.
*/
class Hospital
{
	
	 String name;
	 String address;
	 Hospital()
	 {
	 }
	 Hospital(String a,String j)
	 {
		 name=a;
		 address=j;
	 }
}

/*
* WARD CLASS IS SUB CLASS OF HBMS.
* THIS CLASS CONTAINS MANY  VARIABLES LIKE: WARD NAME, USED BEDS, TOTAL BEDS ETC.
* THIS CLASS IS USED TO  CREATE WARDS 
*/
class Ward extends Hospital
{
	String name_of_ward;
	int total_beds;
	int  used_beds;
	int available_beds;
	Ward()
	{
	}
	Ward(String now,int  tb,int  ub)
	{
		this.name_of_ward=now;
		this.total_beds=tb;
		this.used_beds=ub;
		available_beds=total_beds-used_beds;
		System.out.println(this.name_of_ward+" created...");	
	}
	
	public void need_bed(int value)
{
	
	if(value>this.available_beds)
	{
		int y;
		Scanner rr=new Scanner(System.in);
		System.out.println("beds not availabe");
	    
	}
	else
	{
     this.used_beds=this.used_beds+value;
	 this.available_beds=this.total_beds-this.used_beds;
	System.out.println("ok.. now total available beds "+this.available_beds);
	}
}

public void release_bed(int value)
{
	if(value>this.used_beds)
	{
		System.out.println("wrong value");
	}
	else
	{
		this.used_beds=this.used_beds-value;
		this.available_beds=this.total_beds=this.used_beds;
		System.out.println("release operation sucessful");
	
	}
}
}
/*
TEST CLASS IS THE DRIVER CLASS OF OUR PROGRAM 

* IN THIS CLASS. WE ESTABLASHED DATABASE CONNECTIVITY WITH MYSQL DATABASE
* TEST CLASS CARRY MANY FUNCTIONS LIKE NEED BEDS, RELEASE BEDS ETC 
* TEST CLASS CONTAINS MENU FUNCTION 
* 
*/
class Test 
{
	 static Ward[] ward_arr=new Ward[10];
	 static int ward_counter=0;

	 void create_ward()
{

 	String name;
	int beds;
	int used_beds;
	System.out.print("Enter name of ward: ");
	Scanner ss=new Scanner(System.in);
	name=ss.nextLine();
	System.out.print("Enter total beds in "+name+" ward: ");
	 beds=ss.nextInt();
	 System.out.print("Enter used beds in "+name+ " ward: ");
	 used_beds=ss.nextInt();
	Ward ward_object=new Ward(name,beds,used_beds);
	Ward w=ward_object;
	ward_arr[ward_counter]=w;
	ward_counter=ward_counter+1;
	try
	{
	   try 
	   {
                   Class.forName("com.mysql.jdbc.Driver");
	   }
        catch(ClassNotFoundException ce)

		{
			System.out.println("Loading driver failed");
		}			
	
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
	String sql="insert into ward(wname,total_beds,used_beds,available_beds) values(?,?,?,?)";
	PreparedStatement st = conn.prepareStatement(sql);
	
	st.setString(1,name);
	st.setString(2,Integer.toString(beds));
	st.setString(3,Integer.toString(used_beds));
	st.setString(4,Integer.toString(beds-used_beds));
	st.executeUpdate();
	}
	catch(Exception ae)
	{
		System.out.println("Something might be wrong");
	}
	
}
 void create_hospital()
{
	String name;
	String address;

	System.out.print("Enter name of your Hospital: ");
	Scanner sc=new Scanner(System.in);
	name=sc.nextLine();
	System.out.print("Enter address of Hospital: ");
	address=sc.nextLine();
	Hospital obj=new Hospital(name,address);
	try
	{
	   try 
	   {
                   Class.forName("com.mysql.jdbc.Driver");
	   }
        catch(ClassNotFoundException ce)

		{
			System.out.println("Loading driver failed");
		}			
	
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
	String sql="insert into hospital(hname,hadd) values(?,?)";
	PreparedStatement st = conn.prepareStatement(sql);
	st.setString(1,name);
	st.setString(2,address);
	st.executeUpdate();
	}
	catch(Exception ae)
	{
		System.out.println("Something might be wrong");
	}
}
	void every_ward_status()
	{
		try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection(  
"jdbc:mysql://localhost/project","root","root");  
//here sonoo is database name, root is username and password  
Statement stmt=con.createStatement();  
ResultSet rs=stmt.executeQuery("select * from ward");  
while(rs.next())  
System.out.println("SERIAL_NO : "+rs.getInt(1)+"|  WARD NAME : "+rs.getString(2)+"|  TOTAL BEDS : "+rs.getInt(3)+"| USED BEDS : "+rs.getInt(4)+"| AVAILABLE BEDS : "+rs.getInt(5));  
con.close();  
}catch(Exception e){ System.out.println(e);}  
	}
void need_bed_main()
{
	int k,j;
	every_ward_status();
		Scanner ss=new Scanner(System.in);
	System.out.print("pls choose... ward's SERIAL_NO : ");

	k=ss.nextInt();
	System.out.println("ENTER THE AMOUNT OF BEDS : ");
	j=ss.nextInt();
	System.out.println();
	System.out.println();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
    String jdbcUrl = "jdbc:mysql://localhost/project";
    String username = "root";
    String password = "root";
	

    String sql = "update ward set used_beds =used_beds + (?),available_beds =available_beds-(?) where id = (?) AND available_beds> (?)";

    try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password); 
        PreparedStatement pr = conn.prepareStatement(sql);) {
      

		 pr.setInt(1,j);
		 pr.setInt(2,j);
        pr.setInt(3,k);
		pr.setInt(4,j);
      int s = pr.executeUpdate();
    
    } catch (SQLException e) {
      e.printStackTrace();
    }
	
	
}
void release_beds()
{
	int k,j;
	every_ward_status();
	Scanner ss=new Scanner(System.in);
	System.out.print("pls choose ward : ");
	j=ss.nextInt();
	System.out.println("ENTER AMOUNT OF BEDS: ");
	k=ss.nextInt();
	System.out.println();
	String jdbcUr = "jdbc:mysql://localhost/project";
    String usernam = "root";
    String passwor = "root";
	

    String sqll = "update ward set used_beds=used_beds - (?),available_beds=available_beds+(?) where id = (?) AND used_beds>=(?)";
	

    try (Connection conn = DriverManager.getConnection(jdbcUr, usernam, passwor); 
        PreparedStatement pr = conn.prepareStatement(sqll);) {
      

		 pr.setInt(1,k);
		 pr.setInt(2,k);
		 pr.setInt(3,j);
        pr.setInt(4,k);
      int s = pr.executeUpdate();
    
    } catch (SQLException e) {
      e.printStackTrace();
    }
	
}
int  menu()
{
	int flag;
	System.out.print("CREATE WARD... press 0...               " );
	System.out.println("NEED BEDS... press 8.... ");
	System.out.print("EXIT... press 5....                        ");
	System.out.println("All wards status...press 4..");
	System.out.print("SHIFT BEDS... PRESS 7.....               ");
	System.out.println("CLEAR SCREEN ... PRESS 9...");
	System.out.print("RELEASE BEDS... PRESS 2.....             ");
	System.out.println(" UPDATE WARD VALUES... press 1.... ");
	System.out.println();
	System.out.print("PLEASE ENTER VALUE : ");
	Scanner sc=new Scanner(System.in);
	flag=sc.nextInt();
	return flag;
	
}
void shift_beds()
{
	int j,k,chk;
	Scanner ss=new Scanner(System.in);
	every_ward_status();
	System.out.println();
	System.out.print("PLEASE CHOOSE FIRST WARD  ENTER SR NO : ");
	j=ss.nextInt();
	System.out.println();
	System.out.print("PLEASE CHOOSE SECOND WARD ENTER SR NO : ");
	k=ss.nextInt();
	System.out.println();
	System.out.print("Shift beds UPPER WARD TO LOWER WARD ...  PRESS 2..  ");
	System.out.println();
	System.out.println("Shift beds LOWER WARD TO UPPER WARD ... PRESS 1..  ");
System.out.print("ENTER VALUE : ");
	chk=ss.nextInt();
	System.out.println("AMOUNT OF BEDS.. YOU WANNA SHIFT.. : ");
	int amount=ss.nextInt();
	if(chk==1)
	{
		
	
    String jdbcUrl = "jdbc:mysql://localhost/project";
    String username = "root";
    String password = "root";
	

    String sql = "update ward set total_beds=total_beds + (?),available_beds=available_beds+(?) where id = (?) AND available_beds>=(?)";
	

    try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password); 
        PreparedStatement pr = conn.prepareStatement(sql);) {
      

		 pr.setInt(1,amount);
		 pr.setInt(2,amount);
		 pr.setInt(3,j);
		 pr.setInt(4,amount);
        
      int s = pr.executeUpdate();
    
    } catch (SQLException e) {
      e.printStackTrace();
    }
	
	
	String jdbcUr = "jdbc:mysql://localhost/project";
    String usernam = "root";
    String passwor = "root";
	

    String sqll = "update ward set total_beds=total_beds - (?),available_beds=available_beds-(?) where id = (?) AND available_beds>=(?)";
	

    try (Connection conn = DriverManager.getConnection(jdbcUr, usernam, passwor); 
        PreparedStatement pr = conn.prepareStatement(sqll);) {
      

		 pr.setInt(1,amount);
		 pr.setInt(2,amount);
		 pr.setInt(3,k);
        pr.setInt(4,amount);
      int s = pr.executeUpdate();
    
    } catch (SQLException e) {
      e.printStackTrace();
    }
	}
	else if(chk==2)
	{
		String jdbcUrl = "jdbc:mysql://localhost/project";
    String username = "root";
    String password = "root";
	

    String sql = "update ward set total_beds=total_beds + (?),available_beds=available_beds+(?) where id = (?) AND available_beds>=(?)";
	

    try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password); 
        PreparedStatement pr = conn.prepareStatement(sql);) {
      

		 pr.setInt(1,amount);
		 pr.setInt(2,amount);
		 pr.setInt(3,k);
        pr.setInt(4,amount);
      int s = pr.executeUpdate();
    
    } catch (SQLException e) {
      e.printStackTrace();
    }
	
	
	String jdbcUr = "jdbc:mysql://localhost/project";
    String usernam = "root";
    String passwor = "root";
	

    String sqll = "update ward set total_beds=total_beds - (?),available_beds=available_beds-(?) where id = (?) AND available_beds>=(?)";
	

    try (Connection conn = DriverManager.getConnection(jdbcUr, usernam, passwor); 
        PreparedStatement pr = conn.prepareStatement(sqll);) {
      

		 pr.setInt(1,amount);
		 pr.setInt(2,amount);
		 pr.setInt(3,j);
        pr.setInt(4,amount);
      int s = pr.executeUpdate();
    
    } catch (SQLException e) {
      e.printStackTrace();
    }	
	}
	else{
		System.out.println("enter a valid value : ");
		}
}
public void update_ward()
{
 String rname;	
	Scanner sc=new Scanner(System.in);
	Scanner ss=new Scanner(System.in);
   every_ward_status();
   System.out.println();
   System.out.println();
   System.out.print("please enter SERIAL NO. you wanna update : ");
   int j=sc.nextInt();
   System.out.print("ok...");
   System.out.println();
   System.out.println("ward name : ");
   rname=ss.nextLine();
   System.out.println("enter total beds : ");
   int tot=sc.nextInt();
   System.out.println("enter used beds :");
   int use=sc.nextInt();
   int av=tot-use;
   String jdbcUr = "jdbc:mysql://localhost/project";
    String usernam = "root";
    String passwor = "root";
	

    String sqll = "update ward set wname=(?),total_beds=(?),used_beds=(?),available_beds=(?) where id = (?)";
	

    try (Connection conn = DriverManager.getConnection(jdbcUr, usernam, passwor); 
        PreparedStatement pr = conn.prepareStatement(sqll);) {
      

		 pr.setString(1,rname);
		 pr.setInt(2,tot);
		 pr.setInt(3,use);
        pr.setInt(4,av);
		pr.setInt(5,j);
      int s = pr.executeUpdate();
    
    } catch (SQLException e) {
      e.printStackTrace();
    }
}
public static void main(String args[])
{
	
Test obj=new Test();

int check=1;
while(true)
{
	check=obj.menu();
	switch(check)
	{
		case 0:
		obj.create_ward();
		
		
		break;
		case 1:
		obj.update_ward();
		break;
		case 8:
		obj.need_bed_main();
		
		break;
		case 2:
		obj.release_beds();
		break;
		case 5:
		
		break;
		case 4:
		obj.every_ward_status();
		break;
		case 9:
		for (int i = 0; i < 50; ++i) System.out.println();
    
	break;
	case 7:
	obj.shift_beds();
	break;
		default:
		System.out.println("wrong value:");
		
	}
	if(check==5)
	{
		
		
		break;
	}
	
}
	
}
}