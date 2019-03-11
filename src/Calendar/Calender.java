package Calendar;
import java.util.Scanner;

public class Calender {
	static long year = 0;
	static int month = 0;
	static Scanner scan;
	static int[] monthArr = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	public static void main(String[] args) {
		//입력
		inputYear(); //연도 입력 메소드
		inputMonth();//월 입력 메소드
		scan.close();
		
		//출력
		System.out.println("<달력 표시>");
		System.out.println(year+"년 "+month+"월");
		System.out.println("-----------------------------------------------------");
		System.out.println("Sun\tMon\tTue\tWed\tThu\tFri\tSat");
		System.out.println("-----------------------------------------------------");
		Calender.checkFouryear(year); //윤년 판별 메소드
		Calender.getCalender(year,month); //달력 표시 메소드
		System.out.println("\n-----------------------------------------------------");
	}
	
	//윤년 판별 메소드
	public static void checkFouryear(long year) {
		
		if(year%4 == 0 && year%100 != 0 || year%400 == 0)
			monthArr[1] = 29;
		else
			monthArr[1] = 28;
	}
	
	//달력 표시 메소드
	public static void getCalender(long year, int month) {
		int day = 1;

		for(int i = 1 ; day <= monthArr[month-1] ; i++) {
			if(i <= getWeekday(year,month,1)) //i가 첫째날의 요일숫자보다 작을 때는 공백
				System.out.printf("\t");
			else {
				System.out.printf("%02d\t",day);
				day++;
			}
			if(i%7 == 0) //요일숫자가 끝나면 줄바꿈
				System.out.println();
		}
	}

	//요일숫자 메소드 (Zeller 알고리즘)
	public static int getWeekday(long year, int month, int day) {
		int adjustment = 0, mm = 0, yy = 0, weekday = 0;

		adjustment = (14 - month) / 12;
		mm = month + 12 * adjustment;
		yy = (int)year - adjustment;
		weekday = (day + ((13 * mm + 8) / 5) + yy + (yy / 4) - (yy / 100) + (yy / 400)) % 7;
				
		return weekday;
	}
	
	//연도 입력 메소드 (숫자만 가능)
	public static long inputYear() {
		String inputYear;
		scan = new Scanner(System.in);
			
		for(;;) {
			System.out.print("연도를 입력하세요...");
			inputYear = scan.nextLine();
			if(isNumber(inputYear)) {
				year = Long.valueOf(inputYear);
				return year;
			}
			else
				System.out.print("[숫자만 입력] ");
		}
	}

	 //월 입력 메소드 (숫자 1~12만 가능)
	public static int inputMonth() {
		String inputMonth;
		
		for(;;) {
			System.out.print("월을 입력하세요...");
			inputMonth = scan.nextLine();
			if(isNumber(inputMonth)) {
				month = Integer.parseInt(inputMonth);
				if(month <= 12)	
					return month;
				else
					System.out.print("[1~12월만 입력] ");
			}
			else
				System.out.print("[숫자만 입력] ");
			}
	}
	  
	//입력값의 숫자 여부 판별 메소드
	public static boolean isNumber(String str) { 
		char tempCh;
		boolean result = true;

		for (int i = 0; i < str.length(); i++){
		    tempCh = str.charAt(i);	//입력값을 하나씩 검사 
		    if ((int)tempCh < 48 || (int)tempCh > 57){ //아스키 코드 값의 범위로 문자임을 판별
		        result = false;
		        break;
		    }
		}
		return result;
	}
}