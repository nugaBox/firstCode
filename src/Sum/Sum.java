package Sum;

import java.util.Scanner;

public class Sum {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long sum=0;		
			
		for(;;) {
	     System.out.print("숫자를 입력하세요 : "); 
	     //사용자의 입력 (ENTER키를 구분하기 위해 String 형으로 받음)
	     String num = scan.nextLine();
	     
	     //숫자 입력 없이 [ENTER]를 눌렀을 때
	     if(num.isEmpty()) { 
	    	 System.out.print("종료하려면 y를 입력하세요 : ");
				 String check = scan.nextLine();
				 scan.close();
	    	 if(check.equals("y") || check.equals("Y")) {
	    		 System.out.println("종료합니다");
	                break;
	    	 }
	     }
	     else {
	    	//숫자를 입력받았을 때
	    	 if(isNumber(num)) { 
		    	 long l_num = Long.valueOf(num); //String to Long 형변환
		    	 sum+=l_num; 
		    	 System.out.println("합계 : "+sum);
	    	 }
	    	//문자열을 입력받았을 때
	    	 else { 
	    		 System.out.println("다시 입력하세요");
	    	 	 num="";
	    	 }	 
	     }
		}
	   }
	
	 //입력값의 숫자 여부 판별 메소드
	  public static boolean isNumber(String str) { 
		  char tempCh;
		  boolean result = true;

		  for (int i=0; i<str.length(); i++){
		      tempCh = str.charAt(i);	//입력값을 하나씩 검사 
		      if ((int)tempCh < 48 || (int)tempCh > 57){ //아스키 코드 값의 범위로 문자임을 판별
		          result = false;
		          break;
		      }
		    }
		    return result;
		  }
}