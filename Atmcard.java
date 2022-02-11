package atm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Atmcard
 */
@WebServlet("/Atmcard")
public class Atmcard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static long check(long a[]) {
		long result=0;
		for(int i=0;i<16;i++){
			if(i%2==0)
			a[i]=a[i]*2;
		}
		for(int j=0;j<16;j++){
			long count=0;
			if(j%2==0&&a[j]>=10){
				while(a[j]!=0){
				long b=a[j]%10;
				count=count+b;
				a[j]=a[j]/10;
			}
			a[j]=count;
		}
	}
	for(int k=0;k<15;k++){
		result=result+a[k];
	}
	long round=0;
	long last_dig=0;
	long total=0;
	while(round==0){
	 total=result%10;
		last_dig=10-total;
		round++;
	}
	return last_dig;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Atmcard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long a=Long.parseLong(request.getParameter("card_no"));
		PrintWriter pw=response.getWriter();
		long arr[]=new long[16];
		long b;
		for(int i=15;i>=0;i--) {
				 b=a%10;
				arr[i]=b;
				 a=a/10;
		}
		long check_digit=check(arr);
		if(check_digit==arr[15]) {
			pw.println("<h1 style='color:green;'>you card is valid and safe</h1><br><a href='index.html'>Back<a><br><br>*-------------------------------------thanks for using saran's page-----------------------------------------*");
		}
		else {
			pw.println("<p>May be you entered wrong number or if you entered correct number<p><h1 style='color:red;'>Your card is not safe and not valid</h1> <br><a href='index.html'>Back<a><br><br>*-----------------------------------------thank's for using saran's page-----------------------------------*");
		}
	}
}
