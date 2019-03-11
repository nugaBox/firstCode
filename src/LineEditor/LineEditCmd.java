package LineEditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class LineEditCmd {
	String fileName;
	File file;
	FileWriter fw;
	BufferedWriter bw;
	FileReader fr;
	BufferedReader br;
	String str = null;
	ArrayList<String> fileContentsList = new ArrayList<String>();
	Scanner input = new Scanner(System.in);

	// man : 기본 메뉴 표시
	public void manual() {
		System.out.println("=================== LINE EDITOR ===================");
		System.out.println(" OPEN [파일명]\t파일을 읽어 메모리에 저장");
		System.out.println(" SHOW\t\t내용을 라인번호와 함께 표시");
		System.out.println(" ADD\t\t입력받은 내용을 마지막에 추가");
		System.out.println(" INS  [행번호]\t해당 줄번호에 입력 내용을 삽입");
		System.out.println(" EDIT [행번호]\t해당 줄번호의 내용을 표시하고 수정할 내용을 입력");
		System.out.println(" DEL  [행번호]\t해당 줄번호의 내용을 삭제");
		System.out.println(" SAVE\t\t편집된 내용을 파일에 저장");
		System.out.println(" EXIT\t\t종료");
		System.out.println(" MAN\t\t명령어 메뉴 보기");
		System.out.println("============================================== J =\n");
	}

	// open : 파일을 읽어 메모리에 저장한다
	public void open(String fileName) throws IOException {

		// 파일 존재할 때
		try {
			this.fileName = fileName;
			file = new File(fileName);
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			while ((str = br.readLine()) != null) {
				fileContentsList.add(str);
			}

			// 파일 존재하지 않을 때
		} catch (IOException e) {
			this.fileName = fileName;
			file = new File(this.fileName);
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
		}
	}

	// show : 메모리의 내용을 라인번호와 함께 표시한다
	public void show() {
		try {
			int lineNum = 1;
			Iterator<String> iterator = fileContentsList.iterator();
			while (iterator.hasNext()) {
				String element = (String) iterator.next();
				System.out.printf("%04d\t%s\n",lineNum,element);
				lineNum++;
			}
		} catch (Exception e) {
			System.out.println("'open [파일명]'으로 파일을 열어야 합니다 >>");
		}
	}

	// add : 입력받은 내용을 마지막에 추가한다
	public void add() {
		System.out.println("추가할 내용을 입력하세요 >>");
		String addContents = input.nextLine();
		fileContentsList.add(addContents);
	}

	// ins : 입력받은 내용을 라인번호 앞에 추가한다
	public void insert(String lineNumStr) {
		int lineNum = Integer.parseInt(lineNumStr); // 추가할 번호
		if (lineNum <= fileContentsList.size()) {
			try {
				System.out.println("추가할 내용을 입력하세요 >> ");
				String contentsInput = input.nextLine();
				fileContentsList.add(lineNum - 1, contentsInput);
			} catch (Exception e) {
				System.out.println("'open [파일명]'으로 파일을 열어야 합니다 >>");
			}
		} else {
			System.out.println("현재 이 파일에는 " + fileContentsList.size() + "행까지 존재합니다. ins 명령어는 이 범위 안에서만 사용하실 수 있습니다.");
		}
	}

	// edit : 라인번호의 내용을 표시하고 수정할 내용을 입력한다
	public void edit(String lineNumStr) {
		int lineNum = Integer.parseInt(lineNumStr); // 추가할 번호
		if (lineNum <= fileContentsList.size()) {
			try {
				System.out.printf("%04d\t%s\n",lineNum,fileContentsList.get(lineNum - 1));
				System.out.println("수정할 내용을 입력하세요 >> ");
				String contentsInput = input.nextLine();
				fileContentsList.set(lineNum - 1, contentsInput);
			} catch (Exception e) {
				System.out.println("'open [파일명]'으로 파일을 열어야 합니다 >>");
			}
		} else {
			System.out.println("현재 이 파일에는 " + fileContentsList.size() + "행까지 존재합니다. edit 명령어는 이 범위 안에서만 사용하실 수 있습니다.");
		}
	}

	// del : 편집된 내용을 파일에 저장한다
	public void delete(String lineNumber) {
		int lineNum = Integer.parseInt(lineNumber);
		if (lineNum <= fileContentsList.size()) {
			fileContentsList.remove(lineNum - 1);
		} else {
			System.out.println(fileContentsList.size() + "행 이하만 입력하세요");
		}
	}

	// save : 편집된 내용을 파일에 저장한다
	public void save() {
		try {
			bw = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < fileContentsList.size(); i++) {
				bw.write(fileContentsList.get(i));
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (Exception e) {
			System.out.println("열려있는 파일이 없습니다. 파일을 먼저 열어주세요");
		}
	}

	// 입력값 숫자 판별
	public boolean isNumber(String inputValue) { // 입력값이 숫자인지 체크
		char check;
		for (int i = 0; i < inputValue.length(); i++) {
			check = inputValue.charAt(i);
			if (check < 48 || check > 58) {
				return false;
			}
		}
		return true;
	}

	public String yesOrNo() {
		String yesOrNo = null;
		Scanner input = new Scanner(System.in);
		while (true) {
			String quit = input.nextLine();
			if (quit.equals("Y") || quit.equals("y")) {
				yesOrNo = "y";
				break;
			} else if (quit.equals("N") || quit.equals("n")) {
				yesOrNo = "n";
				break;
			} else {
				System.out.println("Y 또는 N 값을 입력해 주세요");
				continue;
			}
		}
		input.close();
		return yesOrNo;
	}
}
