package Exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수 순으로 출력한다.
 */
public class HoresRun {

    public static void main(String[] args) {

        List<Horse> list = new ArrayList<Horse>();

        list.add(new Horse("1번말"));
        list.add(new Horse("2번말"));
        list.add(new Horse("3번말"));
        list.add(new Horse("4번말"));
        list.add(new Horse("5번말"));
        list.add(new Horse("6번말"));
        list.add(new Horse("7번말"));
        list.add(new Horse("8번말"));
        list.add(new Horse("9번말"));
        list.add(new Horse("0번말"));

        for (Horse horse : list) {
            horse.start();
        }

        new PrintHorse(list).start();
    }

}

class PrintHorse extends Thread {
    List<Horse> list;

    public PrintHorse(List<Horse> horse) {
        this.list = horse;
    }

    @Override
    public void run() {
        String[] arr = new String[50];
        int rnk = 1;
        boolean ing = true;
        while (ing) {

            for (Horse horse : list) {
                if(horse.isGoal()==true) {
                    System.out.print(horse.getHName() + " : ");
                    for (int j = 0; j < 50; j++) {
                        arr[j] = "*";
                        System.out.print(arr[j]);
                    }
                    System.out.println();
                    continue;
                }
                
                // 말 달리는거 위치표시
                System.out.print(horse.getHName() + " : ");
                for (int i = 0; i < 50; i++) {
                    arr[i] = "-";
                    if (horse.getLocation() == i) {
                        arr[i] = ">";
                    }
                }
                
                
                // 말 10마리 출력
                for (int j = 0; j < 50; j++) {
                    System.out.print(arr[j]);
                }
                System.out.println();

                // 순위매기기
                if (horse.getLocation() >= 50) {
                    horse.setRank(rnk);
                    rnk++;
                    horse.setGoal(true);
                }

            } // end for

            //일정 시간마다 출력
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=========================================");
            
            //말 다 들어오면 경기 끝
            if (rnk==11) {
                
                System.out.println("=========경기 끝!=======");
                ing=false;
            }
        }// end while
        Collections.sort(list);
        for (Horse horse : list) {
            System.out.printf("%3d 등  :  %3s",horse.getRank(),horse.getHName());
            System.out.println();
        }
	System.exit(0);
    }
}
class Horse extends Thread implements Comparable<Horse> {
    private String name;
    private int rank = 0;
    private int location = 0;
    public boolean goal = false; // 결승지점 통과 여부
    public Horse(String name) {
    	super(name);
    	this.name = name;
	}
	@Override
    public void run() {
		int cnt = 0;
		for (int i = 0; i < 50; i++) {
			this.location += cnt;
			try {
                Thread.sleep((int) (Math.random() * 500) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			cnt++;
		}
    }
    public String getHName() {
        return name;
    }
    public boolean isGoal() {
        return goal;
    }
    public void setGoal(boolean goal) {
        this.goal = goal;
    }
    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public int getLocation() {
        return location;
    }
	@Override
	public int compareTo(Horse horse) {
		/*
			int num = 0;
			if (this.rank > horse.rank) {
				num = 1;
			} else if (this.rank < horse.rank) {
				num = -1;
			} else if (this.rank > horse.rank) {
				num =  0;
			}
			return num;
		*/
		return Integer.compare(this.rank, horse.rank);
	}
}	