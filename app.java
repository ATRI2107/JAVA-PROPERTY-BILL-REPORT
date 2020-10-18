import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

class clients
{
    int client_id;
    String name;
    String address;
    String suburb;
    String state;
    int postcode;
    clients(int client_id,String name,String address,String suburb,String state,int postcode)
    {
        this.client_id=client_id;
        this.name=name;
        this.address=address;
        this.suburb=suburb;
        this.state=state;
        this.postcode=postcode;
    }
}

class properties
{
    int property_id;
    String address;
    String suburb;
    String state;
    int postcode;
    int rent;
    double fee;
    int client_id;
    properties(int property_id,String address,String suburb,String state,int postcode,int rent,double fee,int client_id)
    {
        this.property_id=property_id;
        this.address=address;
        this.suburb=suburb;
        this.state=state;
        this.postcode=postcode;
        this.rent=rent;
        this.fee=fee;
        this.client_id=client_id;
    }
}

class expenses
{
    int property_id;
    String expense;
    double cost;
    String date;
    expenses(int property_id,String expense,double cost,String date)
    {
        this.property_id=property_id;
        this.expense=expense;
        this.cost=cost;
        this.date=date;
    }
}

class rents
{
    int property_id;
    int rent_amt;
    String date;
    rents(int property_id,int rent_amt,String date)
    {
        this.property_id=property_id;
        this.rent_amt=rent_amt;
        this.date=date;
    }
}
class PropertyReport
{
    HashMap<Integer,properties> prop2=new HashMap<>();
    ArrayList<rents> rent_result=new ArrayList<>();
    ArrayList<expenses> expense_result=new ArrayList<>();
    void property_search(HashMap<Integer,ArrayList<properties>> prop)
    {
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Enter the address you want to find property details for: ");
        String address_search[]=sc.nextLine().split(" ");
        System.out.println("-------------------------------------");
        System.out.println("Property ID     ||   Property Address");
        System.out.println("-------------------------------------");
        for(Map.Entry<Integer,ArrayList<properties>> m:prop.entrySet())
        {
            
            ArrayList<properties> temp=m.getValue();
            for(properties p:temp)
            {
                String check_address[]=p.address.split(" ");
                HashMap<String,Integer> index=new HashMap<>();
                for(int i=0;i<check_address.length;i++)
                {
                    index.put(check_address[i],i);
                }
                boolean check=false;
                ArrayList<Integer> index_check=new ArrayList<>();
                for(String s:address_search)
                {
                    index_check.add(index.getOrDefault(s, -1));
                }
                if(index_check.size()==1 && index_check.get(0)!=-1) check=true;
                else
                {
                    for(int j=1;j<index_check.size();j++)
                    {
                        
                        if(index_check.get(j)-index_check.get(j-1)==1) check=true;
                        else
                        {
                            check=false;
                            break;
                        }
                    }
                } 
                if(check)
                {
                    prop2.put(p.property_id,p);
                    System.out.println(p.property_id+"                  "+p.address);
                }
                
                
                
            }
        }
    }
    void record_rent_collection(HashMap<Integer,clients> cl,HashMap<Integer,ArrayList<expenses>> exp,HashMap<Integer,ArrayList<properties>> prop,HashMap<Integer,ArrayList<rents>> re)
    {
        Scanner sc=new Scanner(System.in);
        property_search(prop);   
        System.out.println();
        System.out.print("Enter the Property Id of your choice from the given options above : ");
        int pid=sc.nextInt();
        System.out.println();
        properties p=prop2.get(pid);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Property Address   Property Weekly Rent   Property Owner Name");
        System.out.println("-------------------------------------------------------------");
        System.out.println(p.address+"          "+p.rent+"                  "+cl.get(p.client_id).name);
        System.out.println();
        System.out.print("Enter the number of weeks you want to collect rent for : ");
        int weeks=sc.nextInt();
        System.out.println();
        Date d=new Date();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");
        String curr_date=formatter.format(d);
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                    SUMMMARY                                                      ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Property Address   Property Weekly Rent   Number Of Weeks     Property Owner Name        Rent Collected            Date");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(p.address+"          "+p.rent+"                  "+weeks+"                "+cl.get(p.client_id).name+"             "+(weeks*p.rent)+"                 "+curr_date);
        System.out.println();
        if(re.containsKey(p.property_id))
        {
            re.get(p.property_id).add(new rents(p.property_id, weeks*p.rent, curr_date));
        }
        else
        {
            ArrayList<rents> al=new ArrayList<>();
            al.add(new rents(p.property_id, weeks*p.rent, curr_date));
            re.put(p.property_id,al);
        }
        rent_result.add(new rents(p.property_id, weeks*p.rent, curr_date));
        menu(cl, exp, prop, re);
    }
    void record_expenses(HashMap<Integer,clients> cl,HashMap<Integer,ArrayList<expenses>> exp,HashMap<Integer,ArrayList<properties>> prop,HashMap<Integer,ArrayList<rents>> re)
    {
        Scanner sc=new Scanner(System.in);
        property_search(prop);
        System.out.print("Enter the Property Id of your choice from the given options above : ");
        int pid=sc.nextInt();
        System.out.println();
        properties p=prop2.get(pid);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Property Address   Property Weekly Rent   Property Owner Name");
        System.out.println("-------------------------------------------------------------");
        System.out.println(p.address+"          "+p.rent+"                  "+cl.get(p.client_id).name);
        System.out.println();
        Date d=new Date();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");
        String curr_date=formatter.format(d);
        System.out.println();
        System.out.println("Enter Expense Description");
        sc.nextLine();
        String expense=sc.nextLine();
        System.out.println("Enter Expense Amount");
        double cost=sc.nextDouble();
        if(exp.containsKey(pid))
        {
            exp.get(pid).add(new expenses(pid, expense, cost, curr_date));
        }
        else 
        {
            ArrayList<expenses> al=new ArrayList<>();
            al.add(new expenses(pid, expense, cost, curr_date));
            exp.put(pid,al);
        }
        expense_result.add(new expenses(pid, expense, cost, curr_date));
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                    SUMMMARY                                                      ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Property Address   Monetary Amount   Description of Expense     Property Owner Name                   Date");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(p.address+"          "+cost+"                  "+expense+"             "+cl.get(p.client_id).name+"               "+curr_date);
        System.out.println();
        menu(cl, exp, prop, re);
    }
    void print_report(HashMap<Integer,clients> cl,HashMap<Integer,ArrayList<expenses>> exp,HashMap<Integer,ArrayList<properties>> prop,HashMap<Integer,ArrayList<rents>> re,clients c)
    {
                System.out.println("Generating report for "+c.name+" ....");
                System.out.println();
                System.out.println("PORTFOLIO REPORT");
                System.out.println("Client: "+c.name+" , "+c.address+" "+c.suburb+" "+c.state+" "+c.postcode);
                long millis=System.currentTimeMillis();  
                Date date=new Date(millis);    
                System.out.println("Report Generated : "+date);
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("|                         Property |   Rent |     Expenses |     Fee Rate |      Fees |      Net |");
                System.out.println("--------------------------------------------------------------------------------------------------");
                double total_rent_client=0.0,total_expenses_client=0.0,total_fees_client=0.0,total_net_client=0.0;
                for(properties p: prop.get(c.client_id))
                {
                    double total_rent=0.0;
                    for(rents r:re.get(p.property_id))
                    {
                        total_rent+=r.rent_amt*1.0;
                    }
                    total_rent_client+=total_rent;
                    double total_expenses=0.0;
                    for(expenses e:exp.get(p.property_id))
                    {
                        total_expenses+=e.cost*1.0;
                    }
                    total_expenses_client+=total_expenses;
                    double fee_rate=p.fee;
                    double fees=fee_rate*total_rent;
                    total_fees_client+=fees;
                    double net=total_rent-total_expenses-fees;
                    total_net_client+=net;
                    System.out.println("|  "+p.address+"  |  "+total_rent+"|    "+total_expenses+"  |    "+fee_rate+"      |  "+fees+"     |  "+net+" |");
                }
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("|                             TOTAL| "+total_rent_client+" |    "+total_expenses_client+" |               |      "+total_fees_client+" |   "+total_net_client+" |");
                System.out.println("--------------------------------------------------------------------------------------------------");
    }
    void specific_client(HashMap<Integer,clients> cl,HashMap<Integer,ArrayList<expenses>> exp,HashMap<Integer,ArrayList<properties>> prop,HashMap<Integer,ArrayList<rents>> re)
    {
        System.out.println();
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the name to search : ");
        String name=sc.nextLine();
        System.out.println();
        for(Map.Entry<Integer,clients> m:cl.entrySet())
        {
            HashSet<String> hs=new HashSet<>();
            String client_name[]=m.getValue().name.split(" ");
            for(String s:client_name) hs.add(s);
            if(hs.contains(name))
            {
                print_report(cl, exp, prop, re, m.getValue());
            }
        }
    }
    void portfolio_report(HashMap<Integer,clients> cl,HashMap<Integer,ArrayList<expenses>> exp,HashMap<Integer,ArrayList<properties>> prop,HashMap<Integer,ArrayList<rents>> re)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Generate Report : ");
        System.out.println("1. Specific Client");
        System.out.println("2. All Clients");
        System.out.println("3. For a Specific Post Code");
        System.out.println();
        int choice=sc.nextInt();
        switch(choice)
        {
            case 1:
                specific_client(cl, exp, prop, re);
                break;

        }
    }
    void menu(HashMap<Integer,clients> cl,HashMap<Integer,ArrayList<expenses>> exp,HashMap<Integer,ArrayList<properties>> prop,HashMap<Integer,ArrayList<rents>> re)
    {
        Scanner sc=new Scanner(System.in);
        int choice;

        System.out.println("Enter your choice:");
        System.out.println();
        System.out.println("1. Record Rent Collection ");
        System.out.println("2. Record Expenses ");
        System.out.println("3. Portfolio Report ");
        System.out.println("4. Save ");
        System.out.println("5. Exit");
        System.out.println();
        choice=sc.nextInt();
        System.out.println();
        switch(choice)
        {
            case 1:
                record_rent_collection(cl, exp, prop, re);
            break;
            case 2:
                record_expenses(cl, exp, prop, re);
            break;
            case 3:
                portfolio_report(cl, exp, prop, re);
            break;

        }
    }
}
class app
{
    public static void main(String[] args) {

        File f;
        f=new File("clients.txt");
        PropertyReport pr=new PropertyReport();
        HashMap<Integer,clients> cl=new HashMap<>();
        HashMap<Integer,ArrayList<expenses>> exp=new HashMap<>();
        HashMap<Integer,ArrayList<properties>> prop=new HashMap<>();
        HashMap<Integer,ArrayList<rents>> re=new HashMap<>();

        try(Scanner sc=new Scanner(f,StandardCharsets.UTF_8.name()))
        {
            while(sc.hasNextLine())
            {
                String temp[]=sc.nextLine().split(",");
                int client_id=Integer.parseInt(temp[0]);
                String name=temp[1];
                String address=temp[2];
                String suburb=temp[3];
                String state=temp[4];
                int postcode=Integer.parseInt(temp[5]);
                cl.put(client_id,new clients(client_id, name, address, suburb, state, postcode));
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        f=new File("expenses.txt");
        try(Scanner sc=new Scanner(f,StandardCharsets.UTF_8.name()))
        {
            while(sc.hasNextLine())
            {
                String temp[]=sc.nextLine().split(",");
                int property_id=Integer.parseInt(temp[0]);
                String expense=temp[1];
                double cost=Double.parseDouble(temp[2]);
                String date=temp[3];
                if(exp.containsKey(property_id))
                {
                    exp.get(property_id).add(new expenses(property_id, expense, cost, date));
                }
                else
                {
                    ArrayList<expenses> al=new ArrayList<>();
                    al.add(new expenses(property_id, expense, cost, date));
                    exp.put(property_id,al);
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        f=new File("properties.txt");
        try(Scanner sc=new Scanner(f,StandardCharsets.UTF_8.name()))
        {
            while(sc.hasNextLine())
            {
                String temp[]=sc.nextLine().split(",");
                int property_id=Integer.parseInt(temp[0]);
                String address=temp[1];
                String suburb=temp[2];
                String state=temp[3];
                int postcode=Integer.parseInt(temp[4]);
                int rent=Integer.parseInt(temp[5]);
                double fee=Double.parseDouble(temp[6]);
                int client_id=Integer.parseInt(temp[7]);
                if(prop.containsKey(client_id))
                {
                    prop.get(client_id).add(new properties(property_id, address, suburb, state, postcode, rent, fee, client_id));
                }
                else 
                {
                    ArrayList<properties> al=new ArrayList<>();
                    al.add(new properties(property_id, address, suburb, state, postcode, rent, fee, client_id));
                    prop.put(client_id,al);
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        f=new File("rents.txt");
        try(Scanner sc=new Scanner(f,StandardCharsets.UTF_8.name()))
        {
            while(sc.hasNextLine())
            {
                String temp[]=sc.nextLine().split(",");
                int property_id=Integer.parseInt(temp[0]);
                int rent_amt=Integer.parseInt(temp[1]);
                String date=temp[2];
                if(re.containsKey(property_id))
                {
                    re.get(property_id).add(new rents(property_id, rent_amt, date));
                }
                else
                {
                    ArrayList<rents> al=new ArrayList<>();
                    al.add(new rents(property_id, rent_amt, date));
                    re.put(property_id,al);
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
       pr.menu(cl, exp, prop, re);
    }
}