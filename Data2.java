package Password;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;
class Data2 
{
	Connection conn;
	Statement stmt;
	String query,name,password,result,password1,username,site_name,query1,dis;
	int option,option1,count=0;
	ResultSet pointer;
	Scanner sc;
	Data2()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/password","root","");
			stmt=conn.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Connection Failed");
		}
	}
	void start()
	{

		sc=new Scanner(System.in);
		System.out.println("------Welcome To Password Manager------");
		System.out.println("Enter 1 for Sign-Up");
		System.out.println("Enter 2 for Login");
		option=sc.nextInt();
		try
		{
			if(option==1)
			{
				System.out.println("Enter Name");
				name=sc.next();
				System.out.println("Enter Password");
				password=sc.next();
				query="insert into signup values('"+name+"','"+password+"')";
				stmt.execute(query);
				System.out.println("----------Sign-Up Successfully----------");
				System.out.println("Enter 3 for Entering New Password Details");
				System.out.println("Enter 4 for Updating Existing Password Details");
				System.out.println("Enter 5 for Displaying password");
				System.out.println("Enter 6 for Logout");
				option1=sc.nextInt();
				boolean b=true;
				while(b==true)
				{

				switch(option1)
				{
					case 3:
						registerPassword();
						break;
					case 4:
						updatePassword();
						break;
					case 5:
						showPassword();
						break;
					default:
						b=false;
						System.out.println("Exiting");
						System.exit(0);
				}
				System.out.println("-----------------------------------");
				System.out.println("Enter 3 for Entering New Password Details");
				System.out.println("Enter 4 for Updating Existing Password Details");
				System.out.println("Enter 5 for Displaying password");
				System.out.println("Enter 6 for Logout");
				option1=sc.nextInt();
				}
			}
			else
			{
					count=0;
					while(count<3)
					{
						System.out.println("Enter name");
						name=sc.next();
						System.out.println("Enter Password");
						password=sc.next();
						query="select Password from signup where Name='"+name+"'";
						ResultSet pointer=stmt.executeQuery(query);
						while(pointer.next())
						{
							result=pointer.getString(1);
						}
						if(result.equals(password))
						{
							break;
						}
						else
						{
							System.out.println("Invalid Name or Password.");
							count++;
							if(count==3)
							{
								System.out.println("Too many attempts try after some time");
								System.exit(0);
							}
						}
					}
					System.out.println("----------Login-in Successfully----------");
					System.out.println("Enter 3 for Entering New Password Details");
					System.out.println("Enter 4 for Updating Existing Password Details");
					System.out.println("Enter 5 for Displaying password");
					System.out.println("Enter 6 for Logout");
					option1=sc.nextInt();
					boolean b=true;
					while(b==true)
					{

					switch(option1)
					{
						case 3:
							registerPassword();
							break;
						case 4:
							updatePassword();
							break;
						case 5:
							showPassword();
							break;
						default:
							b=false;
							System.out.println("Exiting");
							System.exit(0);
					}
					System.out.println("-----------------------------------");
					System.out.println("Enter 3 for Entering New Password Details");
					System.out.println("Enter 4 for Updating Existing Password Details");
					System.out.println("Enter 5 for Displaying password");
					System.out.println("Enter 6 for Logout");
					option1=sc.nextInt();
					}
			}
			
		}
		
		catch(Exception e)
		{
			System.out.println("Error "+e.getMessage());
		}
	}
	void registerPassword()
	{
		try
		{
			System.out.println("Enter SiteName");
			site_name=sc.next();
			System.out.println("Enter Username");
			username=sc.next();
			System.out.println("Enter Password");
			password=sc.next();
			query="insert into password_details values('"+site_name+"','"+username+"','"+password+"')";
			stmt.execute(query);
			System.out.println("------------Password Details saved successfully------------");
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}
	}
	void updatePassword()
	{
		try
		{
			System.out.println("Enter Username");
			username=sc.next();
			System.out.println("Enter Site Name");
			site_name=sc.next();
			System.out.println("Enter new Password");
			password=sc.next();
			query1="select Password from password_details where Username='"+username+"'";
			pointer=stmt.executeQuery(query1);
			while(pointer.next())
			{
				dis=pointer.getString(1);
			}
			if(password.equals(dis))
			{
				System.out.println("It is same as previous password");
				System.out.println("Please enter again");
				password=sc.next();
			}
			query="update password_details set Password='"+password+"' where Username='"+username+"' and Site_Name='"+site_name+"'";
			stmt.execute(query);
			System.out.println("Successfully Updated");
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}
	}
	void showPassword()
	{
		try
		{
			System.out.println("Enter SiteName");
			site_name=sc.next();
			System.out.println("Enter Username");
			username=sc.next();
			query="select * from password_details where Site_Name='"+site_name+"' and username='"+username+"'";
			ResultSet pointer=stmt.executeQuery(query);
			while(pointer.next())
			{
				System.out.println("SiteName : "+pointer.getString(1)+" Username : "+pointer.getString(2)+" Password : "+pointer.getString(3));
			}
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}
	}
}
