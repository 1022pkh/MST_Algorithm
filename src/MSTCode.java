import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

public class MSTCode {
	static List<Node> graph;
	static int distrance[];
	static int preNode[];
	static boolean visit[];
	static int graphSize = 8;
	static int parent[];

	/**
	 * 최소신장트리 알고리즘 1. 프림 알고리즘 전체적인 코드는 다익스트라의 코드와 비슷함.
	 * 
	 * @param r
	 */
	public static void Prim(int r) {
		// r : 시작으로 삼을 정점 :

		distrance = new int[graphSize];
		preNode = new int[graphSize];
		visit = new boolean[graphSize];
		List<Node> resultGraph = new ArrayList<Node>();

		for (int i = 0; i < graphSize; i++) {
			distrance[i] = -1; // -1 : 무한대로 가정.
			visit[i] = false;
		}

		distrance[r] = 0;
		preNode[r] = 0;
		visit[r] = true;

		int curNode;

		while (!allVisitCheck()) {

			curNode = extractMin();
			visit[curNode] = true;
			// System.out.println("현재 노드 : " + curNode);

			// 연결된 노드 찾아서 거리 갱신
			for (int i = 0; i < graph.size(); i++) {

				if (graph.get(i).getStart() == curNode) {
					int loc = graph.get(i).getEnd();

					if (!visit[loc] && distrance[loc] != 0) {

						if (distrance[loc] == -1) {
							distrance[loc] = graph.get(i).getWeight();
							preNode[loc] = curNode;
						} else if (distrance[loc] > graph.get(i).getWeight()) {
							distrance[loc] = graph.get(i).getWeight();
							preNode[loc] = curNode;
						}

					}

				}

			}

			// for (int j = 0; j < graphSize; j++)
			// System.out.print(distrance[j] + " ");
			// System.out.println();
			// System.out.println();

		}

		System.out.println("------");
		for (int j = 0; j < graphSize; j++)
			System.out.print(preNode[j] + "  ");
		System.out.println();

	}

	/**
	 * 2. 크루스칼 알고리즘 싸이클을 만들지 않는 범위에서 최소 비용 간선을 하나씩 더해가면서 최소신장트리를 만든다.
	 */
	public static void Kruskal() {

		List<Node> sortGraph = new ArrayList<Node>();
		List<Node> resultGraph = new ArrayList<Node>();

		parent = new int[graphSize];

		for (int i = 0; i < graphSize; i++)
			parent[i] = i;

		// 1. 모든 간선을 가중치의 크기순으로 정렬한다.
		while (!(graph.size() == 0)) {
			int min = 99999;
			int minIndex = 0;
			Node temp = null;

			for (int i = 0; i < graph.size(); i++) {
				if (min > graph.get(i).weight) {
					min = graph.get(i).weight;
					minIndex = i;
					temp = graph.get(i);
				}
			}

			graph.remove(minIndex);
			sortGraph.add(temp);

		}

		// System.out.println("------");
		// for (int j = 0; j < sortGraph.size(); j++)
		// System.out.print(sortGraph.get(j).getWeight() + " ");
		// System.out.println();

		// 2.적은 비용부터 간선을 그린다.
		// 간선의 수 + 1 = 정점의 수
		int count = 0;
		int index = 0;
		while (!(count + 1 == graphSize)) {
			Node temp = sortGraph.get(index++);

			// http://swlock.blogspot.kr/2016/05/disjoint-set-data-structure-algorithm.html
			if (find_set(temp.getStart()) != find_set(temp.getEnd())) {

				union_set(temp.getStart(), temp.getEnd());
				resultGraph.add(temp);
				count++;

			}

		}

		System.out.println("------");
		for (int j = 0; j < resultGraph.size(); j++)
			System.out.println(resultGraph.get(j).getStart() + "  " + resultGraph.get(j).getEnd());
		System.out.println();

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

	public static int extractMin() {
		// 그래프에서 d값이 가장 작은 정점을 리턴한다.
		int min = 99999;
		int result = 0;

		for (int i = 0; i < graphSize; i++) {

			if (visit[i])
				continue;

			if (distrance[i] != -1 && distrance[i] != 0) {
				if (min > distrance[i]) {
					min = distrance[i];
					result = i;
				}
			}
		}

		return result;
	}

	public static boolean allVisitCheck() {

		for (int i = 0; i < visit.length; i++)
			if (visit[i] == false)
				return false;

		return true;
	}

	public static void main(String[] args) {
		graph = new ArrayList<Node>();

		graph.add(new Node(0, 1, 4));
		graph.add(new Node(0, 3, 18));
		graph.add(new Node(0, 2, 1));
		graph.add(new Node(0, 4, 5));
		graph.add(new Node(1, 4, 12));
		graph.add(new Node(2, 3, 7));
		graph.add(new Node(2, 5, 6));
		graph.add(new Node(3, 4, 17));
		graph.add(new Node(3, 6, 8));
		graph.add(new Node(4, 6, 10));
		graph.add(new Node(4, 7, 11));
		graph.add(new Node(5, 6, 3));
		graph.add(new Node(5, 7, 15));
		graph.add(new Node(6, 7, 14));

		// System.out.println(graph.size());

		System.out.println("Prim");
		Prim(0);
		System.out.println();
		System.out.println("Kruskal");
		Kruskal();
	}

}
