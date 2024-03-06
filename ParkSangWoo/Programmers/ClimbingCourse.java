package oldexam;

import java.util.*;

class Programmers_ClimbingCourse {

	static class Vertex implements Comparable<Vertex> {
		int index, weight;

		public Vertex(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex v) {
			return Integer.compare(this.weight, v.weight);
		}
	}

	static List<Vertex>[] adjList;
	static int a, b, w, intensity, ansIntensity, ansIdx;
	static boolean[] isVisited;
	static PriorityQueue<Vertex> pqueue = new PriorityQueue<>();
	static HashSet<Integer> summitSet = new HashSet<>();

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

		adjList = new List[n + 1];
		for (int i = 1; i <= n; i++) {
			adjList[i] = new ArrayList<>();
		}

		int pathNum = paths.length;
		for (int i = 0; i < pathNum; i++) {
			a = paths[i][0];
			b = paths[i][1];
			w = paths[i][2];

			adjList[a].add(new Vertex(b, w));
			adjList[b].add(new Vertex(a, w));
		}

		isVisited = new boolean[n + 1];
		for (int start : gates) {
			pqueue.add(new Vertex(start, 0));
			isVisited[start] = true;
		}

		for (int i = 0; i < summits.length; i++) {
			summitSet.add(summits[i]);
		}

		ansIdx = n + 1;
		ansIntensity = Integer.MAX_VALUE;
		while (!pqueue.isEmpty()) {
			Vertex curVertex = pqueue.poll();

			if (curVertex.weight > intensity) {
				intensity = curVertex.weight;
			}

			if (ansIntensity < intensity) {
				break;
			}

			isVisited[curVertex.index] = true;

			if (summitSet.contains(curVertex.index)) {
				if (ansIntensity > intensity) {
					ansIntensity = intensity;
					ansIdx = curVertex.index;
					continue;
				}
				if (ansIdx <= curVertex.index) {
					continue;
				}
				ansIdx = curVertex.index;
			}

			for (Vertex nextVertex : adjList[curVertex.index]) {
				if (isVisited[nextVertex.index]) {
					continue;
				}
				if (nextVertex.weight > ansIntensity) {
					continue;
				}
				pqueue.add(nextVertex);
			}
		}
		int[] answer = { ansIdx, ansIntensity };
		return answer;
	}
}
