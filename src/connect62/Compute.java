package connect62;

import java.io.FileWriter;
import java.io.IOException;

public class Compute {

	int[][]map;
	double[][]scoreMap;
	int[]result=new int[2];
	int myColor;
	Column column;
	Diagonal1 diagonal1;
	Row row;
	Diagonal2 diagonal2;
	Overlap4by4 overlap4by4;
	Make3by3 make3by3;
	Make4by4 make4by4;
	AboutEnemy3 aboutEnemy3;
	FileWriter writer;
	FileWriter mapWriter;
	FindTwoEnemy3 findTwoEnemy3;
	AboutEnemy4 aboutEnemy4;

	Compute(int[][]map, int myColor) throws IOException{
		this.map = map;
		this.myColor =myColor;
		scoreMap = new double[map.length][map.length];

		column = new Column(map, scoreMap, myColor, writer);
		diagonal1 = new Diagonal1(map, scoreMap, myColor, writer);
		row = new Row(map, scoreMap, myColor, writer);
		diagonal2 = new Diagonal2(map, scoreMap, myColor, writer);
		
		overlap4by4 = new Overlap4by4(map, scoreMap, myColor, writer);
		make3by3 = new Make3by3(map, scoreMap, myColor, writer);
		make4by4 = new Make4by4(map,scoreMap, myColor, writer);
		
		aboutEnemy3 = new AboutEnemy3(map, scoreMap, myColor, writer);
		findTwoEnemy3 = new FindTwoEnemy3(map, scoreMap, myColor, writer);
		aboutEnemy4 = new AboutEnemy4(map, scoreMap, myColor, writer);
		

	}



	public void execute() throws IOException{
		
		makeClean(scoreMap);
		FileWriter writer = new FileWriter("log.txt");
		checkAlreadyDone();
		
		scoreMap=column.execute(map, scoreMap, myColor, writer);
		scoreMap=diagonal1.execute(map, scoreMap, myColor, writer);
		scoreMap = row.execute(map, scoreMap, myColor, writer);
		scoreMap = diagonal2.execute(map, scoreMap, myColor, writer);
		scoreMap = overlap4by4.execute(map, scoreMap, myColor, writer);
		scoreMap = make3by3.execute(map, scoreMap, myColor, writer);
		scoreMap = make4by4.execute(map, scoreMap, myColor, writer);
		//scoreMap = aboutEnemy3.execute(map, scoreMap, myColor, writer);(overlap enemy 3)
		scoreMap = findTwoEnemy3.execute(map, scoreMap, myColor, writer);
		scoreMap = aboutEnemy4.execute(map, scoreMap, myColor, writer);

		printMap(scoreMap);
		result=findResult();
		map[result[0]][result[1]]= myColor;
		checkAlreadyDone();
		System.out.println("first Row : " + result[0] +" frist Col : " + result[1]);
		result=findResult();
		map[result[0]][result[1]]= myColor;
		checkAlreadyDone();
		System.out.println("second Row : " + result[0] +" second Col : " + result[1]);
		writer.close();
		printMap2(map);//gui like things
		
		return;
	}





	void printMap(double[][] scoreMap2) throws IOException {
		mapWriter = new FileWriter("map.txt");
		for(int i=0;i<scoreMap2.length;i++) {
			for(int j=0;j<scoreMap2.length;j++) {
				mapWriter.append(i +" : " + j +" : " + scoreMap2[i][j]+"\n");
			}
		}
		mapWriter.close();
	}

	void printMap(int[][] map) throws IOException{
		FileWriter mapWriter2 = new FileWriter("guimap.txt");
		mapWriter2.write("map\n");
		mapWriter2.write("|  ");
		for(int i=0;i< map.length; i++) {
			if(i<10)
				mapWriter2.append(i+" |");
			else
				mapWriter2.append(i-10+" |");
		}
		mapWriter2.append("\n");
		for(int i=0;i<map.length;i++){
			if(i<10)
				mapWriter2.append("|"+i);
			else
				mapWriter2.append("|"+(i-10));
			for(int j=0; j<map[0].length;j++) {
				if(map[i][j]==0) {
					if(j==5||j==15)
						mapWriter2.append("|5 ");
					else if(j==10)
						mapWriter2.append("|0 ");
					else
						mapWriter2.append("|  ");
				}
				else if(map[i][j]==-1)	
					mapWriter2.append("|● ");
				else if(map[i][j]==1)
					mapWriter2.append("|◆ ");
			

			}
			mapWriter2.append("\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n");
		}
		mapWriter2.close();
		return;

	}
	
	
	void printMap2(int[][] map) throws IOException{
		System.out.print("map\n");
		System.out.print("|  ");
		for(int i=0;i< map.length; i++) {
			if(i<10)
				System.out.print(i+" |");
			else
				System.out.print(i-10+" |");
		}
		System.out.print("\n");
		for(int i=0;i<map.length;i++){
			if(i<10)
				System.out.print("|"+i);
			else
				System.out.print("|"+(i-10));
			for(int j=0; j<map[0].length;j++) {
				if(map[i][j]==0) {
					if(j==5||j==15)
						System.out.print("|5 ");
					else if(j==10)
						System.out.print("|0 ");
					else
						System.out.print("|  ");
				}
				else if(map[i][j]==-1)	
					System.out.print("|● ");
				else if(map[i][j]==1)
					System.out.print("|◆ ");
			

			}
			System.out.print("\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n");
		}
	
		return;

	}

	void checkAlreadyDone() {
		for(int i=0; i<map.length;i++) {
			for(int j=0;j<map.length;j++) {
				if(map[i][j]!=0)	scoreMap[i][j]=-10000;
			}
		}
	}

	void makeClean(double[][] scoreMap2) {
		int i,j;
		for(i=0;i<scoreMap2.length;i++) {
			for(j=0;j<scoreMap2[i].length;j++) {
				scoreMap2[i][j]=0;
			}
		}
	}

	int[] findResult(){
		int result[] = new int[2];

		int i;
		int j;

		double check=9;
		boolean isMust=false;

		for(i=0;i<map.length;i++) {
			for(j=0;j<map.length;j++) {
				if(scoreMap[i][j]>0&&(int)scoreMap[i][j]%10<9&&(int)scoreMap[i][j]%10>0) {
					//한자리 수가 0이 아니면 isMust = true
					isMust=true;
					//그리구 must가 있을때
					if(scoreMap[i][j]%10<check%10) {
						check = scoreMap[i][j]; result[0]=i; result[1]=j;
					}//숫자가 작을수록 중요한 거니까!
					else if(scoreMap[i][j]%10==check%10) {//만약 필수 우선순위가 같다
						if(scoreMap[i][j]>check) {//modify
							check = scoreMap[i][j]; result[0]=i; result[1]=j;
						}
					}
				}
			}
		}

		if(isMust)	return result;

		check=0;

		for(i=0;i<map.length;i++) {
			for(j=0;j<map.length;j++) {
				if(scoreMap[i][j]>check) {
					check = scoreMap[i][j]; result[0]=i; result[1]=j;

				}
			}
		}
		return result;
	}





}
