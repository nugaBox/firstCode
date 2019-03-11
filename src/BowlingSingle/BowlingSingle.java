package BowlingSingle;

import java.util.Scanner;

public class BowlingSingle {

	static Scanner scan = new Scanner(System.in);
	static int frame[] = new int[10];
	static int allScore[][] = new int[2][12];
	static int pins, pins2;
	static String inputScore;

	public static void main(String args[]) {

		System.out.println("볼링 스코어 게임 (싱글 모드)");

		for (int l1 = 0; l1 < 10; l1++)
			frame[l1] = 0;
		for (int l2 = 0; l2 < 12; l2++)
			allScore[0][l2] = allScore[1][l2] = 0;

		startFrame(); // 1~10프레임 운영
		lastFrame(); // 보너스프레임 운영
		scan.close();
		print(); // 스코어보드 출력
	}

	// 1~10프레임 운영
	static void startFrame() {
		nextframe:

		for (int i = 0; i < 10; i++) {
			boolean chk = false;

			while (!chk) {
				System.out.println();
				System.out.printf("======= %2d번 프레임 =======\n", i + 1);
				System.out.printf("1번째 볼을 입력하세요 : ");
				inputScore = scan.nextLine();
				if (inputScore.equals("")) {
					System.out.println("프로그램을 종료합니다");
					System.exit(0);
				} else
					pins = Integer.valueOf(inputScore);

				if (pins <= 10 && pins >= 0) {
					allScore[0][i] = pins;
					chk = true;
				}
			}

			if (pins == 10) {
				score();
				if (i == 0)
					System.out.printf("스트라이크! 현재점수 : 10+\n");
				else
					System.out.printf("스트라이크! 현재점수 : %d+\n", frame[i - 1] + 10);

				continue;

			}
			chk = false;

			do {
				int pins2 = 0;

				if (chk)
					continue nextframe;
				System.out.print("2번째 볼을 입력하세요 : ");
				inputScore = scan.nextLine();
				if (inputScore.equals("")) {
					System.out.println("프로그램을 종료합니다");
					System.exit(0);
				} else
					pins2 = Integer.valueOf(inputScore);

				if (pins2 <= 10 && pins2 >= 0 && pins2 + allScore[0][i] < 11) {
					allScore[1][i] = pins2;
					score();
					if (pins2 + allScore[0][i] == 10) { // 스페어 처리
						if (i == 0)
							System.out.printf("스페어! 현재점수 : 10+\n");
						else
							System.out.printf("스페어! 현재점수 : %d+\n", frame[i - 1] + 10);
					} else // 스페어 처리 실패
					if (i == 0)
						System.out.printf("현재점수 : %d\n", allScore[0][i] + allScore[1][i]);
					else
						System.out.printf("현재점수 : %d\n", frame[i - 1] + allScore[0][i] + allScore[1][i]);

					chk = true;
				}
			} while (true);
		}
	}

	// 보너스프레임 운영
	static void lastFrame() {
		if (allScore[0][9] == 10) { // 10프레임이 스트라이크일때
			boolean chk1 = false;
			int pins3 = 0;

			while (!chk1) {
				System.out.println("\n======= [보너스 프레임] =======");
				System.out.print("1번째 볼을 입력하세요 : ");
				inputScore = scan.nextLine();
				if (inputScore.equals("")) {
					System.out.println("프로그램을 종료합니다");
					System.exit(0);
				} else
					pins3 = Integer.valueOf(inputScore);

				if (pins3 < 11 && pins3 >= 0) {
					allScore[0][10] = pins3;
					chk1 = true;
				}
			}

			if (allScore[0][10] == 10) { // 보너스프레임 초구 스트라이크 성공 (2연속)
				boolean chk2 = false;
				int pins4 = 0;

				// System.out.println("스트라이크! ");

				if (allScore[0][11] == 10) // 보너스프레임 2번째볼 스트라이크 성공 (3연속)
					System.out.println("스트라이크! 게임을 종료합니다");

				while (!chk2) {
					System.out.print("2번째 볼을 입력하세요 : ");
					inputScore = scan.nextLine();
					if (inputScore.equals("")) {
						System.out.println("프로그램을 종료합니다");
						System.exit(0);
					} else
						pins4 = Integer.valueOf(inputScore);

					if (pins4 < 11 && pins4 > 0) {
						System.out.println("\n게임을 종료합니다");
						allScore[0][11] = pins4;
						chk2 = true;
					}
				}

			} else { // 보너스프레임 초구 스트라이크 실패
				boolean chk3 = false;
				int pins5 = 0;

				while (!chk3) {
					System.out.print("2번째 볼을 입력하세요 : ");
					inputScore = scan.nextLine();
					if (inputScore.equals(""))
						System.exit(0);
					else
						pins5 = Integer.valueOf(inputScore);

					if (pins5 < 11 && pins5 >= 0 && pins5 + allScore[0][10] < 11) {
						allScore[1][10] = pins5;
						chk3 = true;
					}

				}
			}

		} else if (allScore[0][9] + allScore[1][9] == 10) { // 10프레임 스페어 처리였을때
			boolean chk4 = false;

			int pins6 = 0;

			while (!chk4) {
				System.out.print("2번째 볼을 입력하세요 : ");
				inputScore = scan.nextLine();
				if (inputScore.equals("")) {
					System.out.println("프로그램을 종료합니다");
					System.exit(0);
				} else
					pins6 = Integer.valueOf(inputScore);

				if (pins6 <= 10 && pins6 >= 0) {
					allScore[0][10] = pins6;
					chk4 = true;
				}
			}
		}
		score();
	}

	// 점수 계산 메소드
	static void score() {

		// 1프레임
		if (allScore[0][0] + allScore[1][0] == 10) // 1프레임 스페어 처리
			frame[0] = 10 + allScore[0][1];
		else
			frame[0] = allScore[0][0] + allScore[1][0]; // 1프레임 스페어 실패

		if (allScore[0][0] == 10) { // 1프레임 스트라이크
			if (allScore[0][1] == 10)
				frame[0] = 20 + allScore[0][2];// 연속 스트라이크 성공 : 20 + 다다음프레임 초
			else
				frame[0] = 10 + allScore[0][1] + allScore[1][1];// 연속 스트라이크 실패시 : 10 + 다음프레임 점수1 + 다음프레임 점수2
		}

		// 2~10프레임
		for (int j = 1; j < 10; j++) {
			if (allScore[0][j] == 10) { // 스트라이크
				if (allScore[0][j + 1] == 10)
					frame[j] = frame[j - 1] + 20 + allScore[0][j + 2]; // 연속 스트라이크 성공 : 앞점수 + 20 + 다다음프레임 초구
				else
					frame[j] = frame[j - 1] + 10 + allScore[0][j + 1] + allScore[1][j + 1]; // 연속 스트라이크 실패시 : 앞점수 + 10 +
																							// 다음프레임점수
				//continue;
			}

			if (allScore[0][j] + allScore[1][j] == 10)
				frame[j] = frame[j - 1] + 10 + allScore[0][j + 1]; // 스페어 처리 : 앞점수 + 10 + 다음프레임 초구
			else
				frame[j] = frame[j - 1] + allScore[0][j] + allScore[1][j]; // 스페어 실패 : 앞점수 + 현재프레임점수
		}
	}

	// 스코어보드 출력 메소드
	static void print() {
		System.out.print("\n\t프레임");
		for (int k = 1; k < 11; k++)
			System.out.printf("\t%d", k);

		System.out.print(
				"\n\t======================================================================================================");

		// 1번째 볼 점수
		System.out.print("\n\n\t (1) ");
		for (int l = 0; l < 10; l++)
			if (allScore[0][l] == 10)
				System.out.printf("\tX");
			else
				System.out.printf("\t%d", allScore[0][l]);

		if (allScore[0][9] == 10) {
			if (allScore[0][10] == 10)
				System.out.printf("\tX\tX"); // allScore[0][10], allScore[0][11]
			else
				System.out.printf("\t%d", allScore[0][10]);
		} else if (allScore[0][9] + allScore[1][9] == 10)
			System.out.printf("\t%d", allScore[0][10]);

		// 2번째 볼 점수
		System.out.print("\n\n\t (2) ");
		for (int i1 = 0; i1 < 10; i1++)
			if (allScore[0][i1] == 10)
				System.out.printf("\t");
			else if (allScore[0][i1] != 10 && allScore[0][i1] + allScore[1][i1] == 10)
				System.out.printf("\t/");
			else
				System.out.printf("\t%d", allScore[1][i1]);

		if (allScore[0][9] == 10 && allScore[0][10] != 10)
			System.out.printf("\t%d", allScore[1][10]);

		System.out.print(
				"\n\t======================================================================================================");
		System.out.print("\n\n\t  점수");
		for (int j1 = 0; j1 < 10; j1++)
			System.out.printf("\t%d", frame[j1]);

	}
}
