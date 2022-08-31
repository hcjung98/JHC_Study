package algorithm.dfs_bfs;

import java.io.*;
import java.util.*;

public class DFS_BFS {

    static int T, V, E, S;
    static ArrayList<Integer>[] list;
    static int[] visited;
    static ArrayList<Integer> result;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/sample/DFS.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());

            list = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                list[i] = new ArrayList<>();
            }
            visited = new int[V + 1];
            Arrays.fill(visited, 0);

            for (int i = 1; i <= E; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                list[start].add(end);
                list[end].add(start);
            }
            result = new ArrayList<>();

            //  오름차순 정렬
            for (int i = 1; i <= V; i++) {
                Collections.sort(list[i]);
            }

            dfs(S);
            //dfsStack(S);
            //bfs(S);

            bw.write("#" + tc + " ");
            for (int i = 0; i < result.size(); i++) {
                bw.write(result.get(i) + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    static void dfs(int here) {
        visited[here] = 1;
        result.add(here);

        for (int i = 0; i < list[here].size(); i++) {
            int there = list[here].get(i);
            if (visited[there] == 0) {
                dfs(there);
            }
        }
    }

    static void dfsStack(int start) {
        Stack<Integer> stack = new Stack<>();
        stack.add(start);
        visited[start] = 1;

        while (!stack.isEmpty()) {
            int here = stack.pop();
            result.add(here);

            for (int i = 0; i < list[here].size(); i++) {
                int there = list[here].get(i);
                if (visited[there] == 0) {
                    stack.add(there);
                    visited[there] = 1;
                }
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = 1;

        while (!queue.isEmpty()) {
            int here = queue.poll();
            result.add(here);

            for (int i = 0; i < list[here].size(); i++) {
                int there = list[here].get(i);
                if (visited[there] == 0) {
                    queue.add(there);
                    visited[there] = 1;
                }
            }
        }
    }
}
