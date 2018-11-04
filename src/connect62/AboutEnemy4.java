package connect62;

import java.io.FileWriter;
import java.io.IOException;


public class AboutEnemy4 extends CheckNScore {


	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer; 
	int blank=0;
	int blankRow=0;
	int blankCol=0;





	AboutEnemy4(int[][] map,double[][]scoreMap,int myColor,FileWriter writer) throws IOException{
		super(map, scoreMap, myColor, writer);

	}

	double[][] execute(int[][] map,double[][]scoreMap,int myColor,FileWriter writer) throws IOException {

		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;


		row();
		blank=0; blankRow=0; blankCol=0;
		col();
		blank=0; blankRow=0; blankCol=0;
		dia1();
		blank=0; blankRow=0; blankCol=0;
		dia2();
		blank=0; blankRow=0; blankCol=0;
		return scoreMap;
	}

	void row() throws IOException {
		int[] unit = new int[6];
		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {
				if(map[i][j]==enemyColor) {

					unit=copyToRowUnit(unit,i,j);
					int k=0;
					int count=0;

					boolean isMine=false;				

					for(k=0;k<6;k++) {
						if(unit[k]==myColor)
							isMine = true;
						if(unit[k]==enemyColor)
							count++;
					}

					if(isMine==false&&count==4) {
						for(k=0;k<4;k++) {
							if(unit[k]==0) {
								blank=k;
							}
						}

						blankRow = i+blank;
						blankCol = j;

						if(blank!=0) {
							stone4(blankRow, blankCol, 4.2);
							return;
						}


						if(blank==0) {//연속 4개안에 돌이 없으면
							double case1=0;
							double case2=0;
							double case3=0;

							//case1
							case1=scoreCase(i-1,j)+scoreCase(i+4,j);
							//case2
							case2=scoreCase(i-1,j)+scoreCase(i+5,j);
							//case3
							case3=scoreCase(i-2,j) + scoreCase(i+4,j);


							int maxCase = findMax(case1, case2, case3);

							if(maxCase==1) {
								stone4(i-1,j,4.2);
								stone4(i+4,j,4.2);
							}
							else if(maxCase ==2) {
								stone4(i-1,j,4.2);
								stone4(i+5,j,4.2);
							}
							else if(maxCase ==3) {
								stone4(i-2,j,4.2);
								stone4(i+4,j,4.2);
							}
							return;			
						}
					}


				}
			}
		}

	}



	void col() throws IOException{
		int[] unit = new int[6];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				if(map[i][j]==enemyColor) {

					unit=copyToColUnit(unit,i,j);

					int k=0;
					int count=0;
					boolean isMine=false;



					for(k=0;k<6;k++) {
						if(unit[k]==myColor)
							isMine = true;
						if(unit[k]==enemyColor)
							count++;
					}

					if(isMine==false&&count==4) {
						for(k=0;k<4;k++) {
							if(unit[k]==0) {
								blank=k;
							}
						}

						blankRow = i;
						blankCol = j+blank;

						if(blank!=0) {
							stone4(blankRow, blankCol, 4.1);
							return;
						}

						if(blank==0) {//연속 4개안에 돌이 없으면
							double case1=0;
							double case2=0;
							double case3=0;

							//case1
							case1=scoreCase(i,j-1)+scoreCase(i,j+4);
							//case2
							case2=scoreCase(i,j-1)+scoreCase(i,j+5);
							//case3
							case3=scoreCase(i,j-2) + scoreCase(i,j+4);

							System.out.println("case 1" + case1 + " case2 : " + case2 + " case3 "+ case3);


							int maxCase = findMax(case1, case2, case3);
							if(maxCase==1) {
								stone4(i,j-1,4.1);
								stone4(i,j+4,4.1);
							}
							else if(maxCase ==2) {
								stone4(i,j-1,4.1);
								stone4(i,j+5,4.1);
							}
							else if(maxCase ==3) {
								stone4(i,j-2,4.1);
								stone4(i,j+4,4.1);
							}
							return;			
						}
					}
				}
			}
		}
	}





	double scoreCase(int row, int col) {
		double score = 0;
		if(0<=col&&col<19&&0<=row&&row<19) {//범위검사
			if(map[row][col]==0)
				return scoreMap[row][col];
		}
		return score;
	}

	int findMax(double case1, double case2, double case3) {

		if(case1>=case2) {
			if(case1>=case3)
				return 1;
		}
		if(case2>=case1) {
			if(case2>=case3) {
				return 2;
			}
		}
		if(case3>=case2) {
			if(case3>=case1) {
				return 3;
			}
		}

		return 0;

	}

	void dia1() throws IOException {


		int[] unit = new int[6];
		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				if(map[i][j]==enemyColor) {

					unit=copyToDia1Unit(unit,i,j);

					int k=0;
					int count=0;
					int blank=0;
					int blankRow=0;
					int blankCol=0;

					boolean isMine=false;			


					for(k=0;k<6;k++) {
						if(unit[k]==myColor)
							isMine = true;
						if(unit[k]==enemyColor)
							count++;
					}

					if(isMine==false&&count==4) {
						for(k=0;k<4;k++) {
							if(unit[k]==0) {
								blank=k;
							}
						}

						blankRow = i-blank;
						blankCol = j+blank;


						if(blank!=0) {
							stone4(blankRow, blankCol, 4.3);
							return;	
						}

						if(blank==0) {//연속 4개안에 돌이 없으면
							double case1=0;
							double case2=0;
							double case3=0;

							//case1
							case1=scoreCase(i+1,j-1)+scoreCase(i-4,j+4);
							//case2
							case2=scoreCase(i+1,j-1)+scoreCase(i-5,j+5);
							//case3
							case3=scoreCase(i+2,j-2) + scoreCase(i-4,j+4);



							int maxCase = findMax(case1, case2, case3);
							if(maxCase==1) {
								stone4(i+1,j-1,4.3);
								stone4(i-4,j+4,4.1);
							}
							else if(maxCase ==2) {
								stone4(i+1,j-1,4.3);
								stone4(i-5,j+5,4.1);
							}
							else if(maxCase ==3) {
								stone4(i+2,j-2,4.3);
								stone4(i-4,j+4,4.3);
							}
							return;			




						}
					}
				}
			}
		}

	}

	void dia2() throws IOException {

		int[] unit = new int[6];
		for(int i=5;i<map.length;i++) {
			for(int j=5;j<map.length;j++) {
				if(map[i][j]==enemyColor) {
					unit=copyToDia2Unit(unit,i,j);

					int k=0;
					int count=0;
					

					boolean isMine=false;					


					for(k=0;k<6;k++) {
						if(unit[k]==myColor)
							isMine = true;
						if(unit[k]==enemyColor)
							count++;
					}

					if(isMine==false&&count==4) {
						for(k=0;k<4;k++) {
							if(unit[k]==0) {
								blank=k;
							}
						}

						blankRow = i-blank;
						blankCol = j-blank;

						if(blank!=0) {
							stone4(blankRow, blankCol, 4.4);
							return;
						}

						if(blank==0) {//연속 4개
							double case1=0;
							double case2=0;
							double case3=0;

							case1=scoreCase(i+1,j+1)+scoreCase(i-4,j-4);
							case2=scoreCase(i+1,j+1)+scoreCase(i-5,j-5);
							case3=scoreCase(i+2,j+2) + scoreCase(i-4,j-4);

							int maxCase = findMax(case1, case2, case3);

							if(maxCase==1) {
								stone4(i+1,j+1,4.4);
								stone4(i-4,j-4,4.4);
							}
							else if(maxCase ==2) {
								stone4(i+1,j+1,4.4);
								stone4(i-5,j-5,4.4);
							}
							else if(maxCase ==3) {
								stone4(i+2,j+2,4.4);
								stone4(i-4,j-4,4.4);
							}
							return;			
						}







					}
				}
			}
		}
	}

	void stone4(int row, int col ,double score) throws IOException {
		if(checkMust(row,col,score)) {
			scoreMap[row][col]=scoreMust(scoreMap[row][col],score);
			writer.append("(" +row+ "," + col + ") col findEne4 "+ score+"\n");
			return;
		}
	}


	int[]copyToColUnit(int[]unit, int row, int col){
		if(col<map.length-6+1) {
			int k=0;
			unit[k] = map[row][col];
			unit[k+1]=map[row][col+1];
			unit[k+2]=map[row][col+2];
			unit[k+3]=map[row][col+3];
			unit[k+4]=map[row][col+4];
			unit[k+5]=map[row][col+5];
		}
		return unit;
	}

	int[]copyToRowUnit(int[]unit, int row, int col){
		if(row<map.length-6+1) {
			int k=0;
			unit[k] = map[row][col];
			unit[k+1]=map[row+1][col];
			unit[k+2]=map[row+2][col];
			unit[k+3]=map[row+3][col];
			unit[k+4]=map[row+4][col];
			unit[k+5]=map[row+5][col];
		}

		return unit;
	}

	int[]copyToDia1Unit(int[]unit, int row, int col){
		if(row>=5&& col<=map.length-6) {
			int k=0;
			unit[k] = map[row][col];
			unit[k+1]=map[row-1][col+1];
			unit[k+2]=map[row-2][col+2];
			unit[k+3]=map[row-3][col+3];
			unit[k+4]=map[row-4][col+4];
			unit[k+5]=map[row-5][col+5];
		}
		return unit;
	}

	int[]copyToDia2Unit(int[]unit, int row, int col){
		if(row>5&&col>5) {
			int k=0;
			unit[k] = map[row][col];
			unit[k+1]=map[row-1][col-1];
			unit[k+2]=map[row-2][col-2];
			unit[k+3]=map[row-3][col-3];
			unit[k+4]=map[row-4][col-4];
			unit[k+5]=map[row-5][col-5];
		}
		return unit;
	}

}
