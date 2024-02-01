package oldexam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_14503 {
	static int N;
	static int M;
	static int back;
	static int ans = 1;
	static int end = 0;
	static int[][] room;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer stn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stn.nextToken());
		M = Integer.parseInt(stn.nextToken());
		room = new int[N][M];

		stn = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(stn.nextToken());
		int startY = Integer.parseInt(stn.nextToken());
		int startD = Integer.parseInt(stn.nextToken());

		for (int row = 0; row < N; row++) {
			stn = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				room[row][col] = Integer.parseInt(stn.nextToken());
			}
		}

		clean(startX, startY, startD);
		
		bw.write(Integer.toString(ans));
		bw.flush();
		bw.close();
	}

	public static void clean(int startRow, int startCol, int direction) {
		room[startRow][startCol] = 2; // 자기 자리 청소
		if (back == 4) {
			back = 0;
			turnMove(startRow, startCol, direction);
		}
		if (end != 0) {
			return;
		}

		switch (direction) {
		case 0: // up
			if (room[startRow - 1][startCol] == 0) {
				clean(startRow - 1, startCol, direction);
				ans++;
			} else
				back++;
			clean(startRow, startCol, 3);

			break;

		case 1: // right
			if (room[startRow][startCol + 1] == 0) {
				clean(startRow, startCol + 1, direction);
				ans++;
			} else
				back++;
			clean(startRow, startCol, 0);
			break;

		case 2: // down
			if (room[startRow + 1][startCol] == 0) {
				clean(startRow + 1, startCol, direction);
				ans++;
			} else
				back++;
			clean(startRow, startCol, 1);
			break;

		case 3: // left
			if (room[startRow][startCol - 1] == 0) {
				clean(startRow, startCol, direction);
				ans++;
			} else
				back++;
			clean(startRow, startCol, 2);
			break;

		}

	} // clean end

	public static void turnMove(int row, int col, int direction) {
		switch (direction) {
		case 0: // down
			if (room[row + 1][col] == 2) {
				clean(row + 1, col, direction);
			} else if (room[row + 1][col] == 1) {
				end++;
				return;
			} else
				clean(row + 1, col, direction);
			break;
		case 1: // left
			if (room[row][col - 1] == 2) {
				clean(row, col - 1, direction);
			} else if (room[row][col - 1] == 1) {
				end++;
				return;
			} else
				clean(row, col - 1, direction);
			break;
		case 2: // up
			if (room[row - 1][col] == 2) {
				clean(row - 1, col, direction);
			} else if (room[row - 1][col] == 1) {
				end++;
				return;
			} else
				clean(row - 1, col, direction);
			break;
		case 3: // right
			if (room[row][col + 1] == 2) {
				clean(row, col + 1, direction);
			} else if (room[row][col + 1] == 1) {
				end++;
				return;
			} else
				clean(row, col + 1, direction);
			break;
		}

	}

}
