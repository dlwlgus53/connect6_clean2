package connect62;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	static Scanner scan = new Scanner(System.in);
	static int comColor;
	static int mapSize = 19;
	static String temp;

	public static void main(String[] args) throws IOException {
		System.out.println("\nconnect62_clean version\n\n");
		System.out.println("-------------------------------------------\n");
		int[][] map = new int[mapSize][mapSize];
		makeClean(map);	


		System.out.println("type the com number => black : type -1 | white : type 1");
		System.out.println("-------------------------------------------\n");
		temp = scan.nextLine();
		comColor = Integer.parseInt(temp);//==com color

		if(comColor == -1)	{
			System.out.println("com : black");
			System.out.println("user : white");
			System.out.println("-------------------------------------------");
			map[map.length/2][map[0].length/2] = -1;
			System.out.println("com : (9,9)" );
		}
		else if(comColor == 1){
			System.out.println("com : white");
			System.out.println("user : black");
			enemyInput(map,comColor);

			map[8][8] = 1;
			map[8][9] = 1;

			System.out.println("first Row : " + 8 +" frist Col : " + 8);
			System.out.println("second Row : " + 8 +" second Col : " + 9);

		}

		Compute compute = new Compute(map,comColor);
		////here is for debug

		/*map[9][9]=-1;
		  map[9][8]=-1;

		  map[9][7] = -1;
		 


		  map[8][8]=-1;
		  map[8][7]=-1;

		  map[8][6] = -1;*/
		
		 


		while(true) {
			enemyInput(map,comColor);
			compute.execute();
		}
	}




	static void makeClean(int[][]map) {
		int i,j;
		for(i=0;i<map.length;i++) {
			for(j=0;j<map[i].length;j++) {
				map[i][j]=0;
			}
		}
	}


	private static void enemyInput(int[][]map, int comColor) {

		int temp=0;
		String stemp="0";
		String sx1 = "0", sx2="0", sy1="0", sy2="0";
		boolean isRight= false;
		boolean isRight2= false;
		boolean isRight3 = false;


		while(isRight==false) {

			isRight2 = false;
			
			
			while(isRight2==false) {
				System.out.println("enter the x1");
				sx1 = scan.nextLine();

				System.out.println("enter the y1");
				sy1 = scan.nextLine();

				isRight2 = check(sx1, sy1);
			}

			isRight2 = false;

			while(isRight2==false) {
				System.out.println("enter the x2");
				sx2 = scan.nextLine();

				System.out.println("enter the y2");
				sy2 = scan.nextLine();

				isRight2 = check(sx2, sy2);
			}
			
			isRight3 =false;

			while(isRight3==false) {
				System.out.println("did y do well?? yes : 1 ,no: 0");
				stemp = scan.nextLine();
				isRight3 = isStringDouble(stemp);
			}
			temp = Integer.parseInt(stemp);

			if(temp==1)	isRight=true;	
		}


		map[Integer.parseInt(sx1)][Integer.parseInt(sy1)] = comColor*-1;
		map[Integer.parseInt(sx2)][Integer.parseInt(sy2)] = comColor*-1;


	}

	static boolean check(String row, String col) {
		boolean numcheck = true;
		boolean rangecheck = false;
		int r;
		int c;


		numcheck = isStringDouble(row);

		if(numcheck == false) 
			return numcheck;


		numcheck = isStringDouble(col);//숫자로 제대로 입력했는지 판단.

		if(numcheck == false) 
			return numcheck;

		else {
			r = Integer.parseInt(row);
			c = Integer.parseInt(col);

			if(r<19&&r>=0&&c<19&&c>=0)
				rangecheck = true;
		}
		return rangecheck;
	}


	public static boolean isStringDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}




}
