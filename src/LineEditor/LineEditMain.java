package LineEditor;

import java.util.Scanner;

public class LineEditMain {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		LineEditCmd command = new LineEditCmd();
		command.manual();

		// 명령어 입력 시작
		while (true) {
			System.out.print("CMD : ");
			String inputCmd = scanner.nextLine();
			String cmdArray[] = inputCmd.split(" ");
			String cmd = cmdArray[0].trim();

			if (cmd.equals("MAN")) {
				command.manual();
			} else if (cmd.equals("OPEN")) {
				try {
					String fileName = cmdArray[1].trim();
					command.open(fileName);
					System.out.println("파일을 읽어왔습니다");
				} catch (Exception e) {
					System.out.println("파일을 읽을 수 없습니다. 다시 시도해주세요 >> ");
				}
			} else if (cmd.equals("SHOW")) {
				command.show();
			} else if (cmd.equals("ADD")) {
				command.add();
			} else if (cmd.equals("INS")) {
				try {
					String lineNum = cmdArray[1].trim();
					if (command.isNumber(lineNum)) {
						command.insert(lineNum);
					} else {
						System.out.println("'ins [행번호]' 형식으로만 입력하세요 1 >> ");
					}
				} catch (Exception e) {
					System.out.println("'ins [행번호]' 형식으로만 입력하세요 2 >> ");
				}
			} else if (cmd.equals("EDIT")) {
				try {
					String lineNum = cmdArray[1].trim();
					if (command.isNumber(lineNum)) {
						command.edit(lineNum);
					} else {
						System.out.println("'edit [행번호]' 형식으로만 입력하세요 1 >> ");
					}
				} catch (Exception e) {
					System.out.println("'edit [행번호]' 형식으로만 입력하세요 2 >> ");
				}
			} else if (cmd.equals("DEL")) {
				try {
					String lineNum = cmdArray[1].trim();
					if (command.isNumber(lineNum)) {
						command.delete(lineNum);
					} else {
						System.out.println("'del [행번호]' 형식으로만 입력하세요 1 >> ");
					}
				} catch (Exception e) {
					System.out.println("'del [행번호]' 형식으로만 입력하세요 2 >> ");
				}
			} else if (cmd.equals("SAVE")) {
				command.save();
			}

			else if (cmd.equals("EXIT")) {
				System.out.println("종료 하시겠습니까?(Y or N)");
				String yesOrNo = command.yesOrNo();
				if (yesOrNo.equals("y") || yesOrNo.equals("Y")) {
					break;
				} else if (yesOrNo.equals("n") || yesOrNo.equals("N")) {
					continue;
				}
			} else
				System.out.println("다시 입력하세요");
		}
		scanner.close();
	}
}
