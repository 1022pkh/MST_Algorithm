import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Node {
	int start;
	int end;
	int weight;

	public Node(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public int getWeight() {
		return weight;
	}
}

public class TestCode {
	static List<Node> graph;
	static int graphSize;
	static int parent[];

	// 2. 퀵정렬
	public static void quickSort(int start, int end) {
		if (start < end) {
			// 1) 분할
			int standIndex = partition(start, end);
			// 2) 왼쪽 부분 배열 정렬
			quickSort(start, standIndex - 1);
			// 3) 오른쪽 부분 배열 정렬
			quickSort(standIndex + 1, end);
		}
	}

	public static int partition(int start, int end) {

		// 기준 원소는 마지막 값으로 정한다.
		Node standard = graph.get(end);
		int i = start - 1;
		Node temp;

		for (int j = start; j < end; j++) {
			if (graph.get(j).getWeight() <= standard.getWeight()) {
				temp = graph.get(++i);
				graph.set(i, graph.get(j));
				graph.set(j, temp);
			}
		}

		temp = graph.get(i+1);
		graph.set(i+ 1, graph.get(end));
		graph.set(end, temp);
		
		return i + 1;

	}

	public static void Kruskal() {
		int result = 0;

		parent = new int[graphSize];

		for (int i = 0; i < graphSize; i++)
			parent[i] = i;

//		for (int i = 0; i < graph.size(); i++) {
//			for (int j = i + 1; j < graph.size(); j++) {
//				if (graph.get(i).getWeight() > graph.get(j).getWeight()) {
//					Node temp = graph.get(i);
//					graph.set(i, graph.get(j));
//					graph.set(j, temp);
//
//				}
//			}
//		}
		
		quickSort(0,graph.size()-1);
	

		int count = 0;
		int index = 0;
		while (!(count + 1 == graphSize)) {
			Node temp = graph.get(index++);

			if (find_set(temp.getStart()) != find_set(temp.getEnd())) {
				union_set(temp.getStart(), temp.getEnd());
				count++;
				result += temp.getWeight();
			}

		}

		System.out.println(result);

	}

	public static int make_set(int x) {
		parent[x] = x;
		return 0;
	}

	public static int find_set(int x) {
		if (x == parent[x])
			return x;
		return find_set(parent[x]);
	}

	public static int union_set(int x, int y) {
		parent[find_set(y)] = find_set(x);
		return 0;
	}

	public static void main(String[] args) {
		graph = new ArrayList<Node>();

		Scanner scan = new Scanner(System.in);
		int n_size = scan.nextInt(); // 정점의 수
		int m_size = scan.nextInt(); // 간선의 수
		scan.nextLine();

		graphSize = n_size;

		for (int i = 0; i < m_size; i++) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			int w = scan.nextInt();

			graph.add(new Node(x - 1, y - 1, w));
		}

		Kruskal();
	}

}
