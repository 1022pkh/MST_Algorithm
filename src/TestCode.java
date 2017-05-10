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
	static int find_set_result[];

	// 1. 병합정렬
	public static void mergeSort(int start, int end) {

		if (start < end) {
			int mid = (start + end) / 2; // 중간지점 계산

			mergeSort(start, mid); // 전반부 정렬
			mergeSort(mid + 1, end); // 후반부 정렬
			merge(start, mid, end); // 병합

		}

	}

	public static void merge(int start, int mid, int end) {
		int i = start;
		int j = mid + 1;
		int t = 0;

		int[] tempArray = new int[10];
		List<Node> temp = new ArrayList<Node>();
		
		
		while (i <= mid && j <= end) {
			if (graph.get(i).weight <= graph.get(j).weight) {
				temp.add(graph.get(i++));
			} else {
				temp.add(graph.get(j++));
			}
		}

		while (i <= mid) {
			temp.add(graph.get(i++));
		}

		while (j <= end) {
			temp.add(graph.get(j++));
		}

		i = start;
		t = 0;

		while (i <= end) {
			graph.set(i++, temp.get(t++));
		}

	}

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
			if (graph.get(j).weight <= standard.weight) {
				temp = graph.get(++i);
				graph.set(i, graph.get(j));
				graph.set(j, temp);
			}
		}

		temp = graph.get(i + 1);
		graph.set(i + 1, graph.get(end));
		graph.set(end, temp);

		return i + 1;

	}

	public static void Kruskal() {
		int result = 0;

		parent = new int[graphSize];

		for (int i = 0; i < graphSize; i++)
			make_set(i);

//		quickSort(0, graph.size() - 1);
		mergeSort(0, graph.size() -1);
		
		
		int count = 0;
		int index = 0;
		while (!(count + 1 == graphSize)) {
			Node temp = graph.get(index++);

			if (find_set(temp.start) != find_set(temp.end)) {
				union_set(temp.start, temp.end);
				count++;
				result += temp.weight;
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
