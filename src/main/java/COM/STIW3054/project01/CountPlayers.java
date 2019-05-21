package COM.STIW3054.project01;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.UnknownHostException;
import java.util.logging.FileHandler;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Properties;
import org.jsoup.Jsoup;
public class CountPlayers {
 private String url;
 Document doc; 
 String data;
 static int total = 0;
 static int z = 0;
 static boolean data1 = true;
 static String[] category2 = {"U8","U10","U16","U18","","UxG","U8G","U10G","U16G","U18G","U20"};

 public CountPlayers(String URL,String state)  {
  this.url = URL;
 }
 
 public String getTop() throws UnknownHostException {
   
  try {
    this.doc = Jsoup.connect(url).get();
    Elements des = doc.select(".CRs1");
       Element titl = doc.selectFirst(".defaultDialog");
       String title = doc.title();
          int scrape = title.indexOf("9");
             String category = title.substring(scrape + 1).replace("(", "").replace(")", "");
       Elements rows = des.select("tr");
       
        
       
       if (titl != null) {
       
        
       for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
         
           Element row = rows.get(i);
           Elements cols = row.select("td");
                 final String rk = row.select("td:nth-of-type(1)").text();
                 final String sno = row.select("td:nth-of-type(2)").text();
                 final String name = row.select("td:nth-of-type(4)").text();
                 final String rtg = row.select("td:nth-of-type(6)").text();
                 final String state = row.select("td:nth-of-type(7)").text();
                 final String pointer = row.select("td:nth-of-type(8)").text();
                 

                 if (!rk.equals(null)) {
                  
                     String format = "| %-5s | %-5s | %-35s| %-8s| %-20s| %-8s| %-8s|\n";
                     System.out.format(format, rk, sno, name, rtg, state, pointer,category);
                     total++;
                 }
                 
       }
       z -= 2;
       System.out.format(total + "\n");
       
       

       }else {
        data = "URL DOESN'T HAVE CONTENT";
        
        
       }
       } catch (IOException uk) {
    System.out.print("");
    z = 5;
    
   }
   
   return data;
 }
 
}