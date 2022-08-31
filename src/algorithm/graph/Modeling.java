package algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Modeling {

    static int N, M;
    static ArrayList<int[]>[] adjList = new ArrayList[N + 1]; //인접 리스트
    static ArrayList<int[]> edgeList = new ArrayList<int[]>(); //간선 리스트

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /*==============================================*/
        /*인접리스트 모델링 : 다익스트라에서 많이 쓰임*/
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<int[]>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[from].add(new int[]{to, cost}); //단방향 그래프 모델링
            adjList[to].add(new int[]{from, cost}); //양방향 그래프 모델링
        }

        /*==============================================*/
        /*간선리스트 모델링 : 벨만포드, 크루스칼에서 많이 쓰임*/

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList.add(new int[]{from, to, cost}); //단방향 그래프 모델링
            edgeList.add(new int[]{to, from, cost}); //양방향 그래프 모델링
        }

        /*==============================================*/
        /*인접행렬 모델링 : 플로이드 워셜에서 많이 쓰임*/
        int[][] graph = new int[N + 1][N + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from][to] = cost; // 단방향 그래프 모델링
            graph[to][from] = cost; // 양방향 그래프 모델링
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) ;
                    //K를 거쳐가는 경우와 바로가는 경우를 비교해서 갱신
                }
            }
        }

        /*PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> Long.compare(o1.cost, o2.cost));
        PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> {
            return Long.compare(o1.cost, o2.cost)
        });

        Arrays.sort(buildings, (o1, o2) -> o1.cost - o2.cost);
        Arrays.sort(buildings, (o1, o2) -> Integer.compare(o1.x, o2.x));
        Arrays.sort(buildings, (o1, o2) -> {
            if(o1.x == o2.x){
                return Integer.compare(o1.y, o2.y);
            }else{
                return Integer.compare(o1.x, o2.x);
            }
        });*/

    }


}
