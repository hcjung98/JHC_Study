package algorithm.dfs_bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Modeling {

    static int[] visited = new int[100];
    static ArrayList<Integer>[] list = new ArrayList[100];

    public static void dfs(int here) {
        visited[here] = 1;

        for (int i = 0; i < list[here].size(); i++) {
            int there = list[here].get(i);
            if (visited[there] == 0) {
                dfs(there);
            }
        }
    }

    public static void dfsStack(int start) {

        Stack<Integer> stack = new Stack<>();
        stack.add(start);
        visited[start] = 1;

        while (!stack.isEmpty()) {
            int here = stack.pop();

            for (int i = 0; i < list[here].size(); i++) {
                int there = list[here].get(i);
                if (visited[there] == 0) {
                    stack.add(there);
                    visited[there] = 1;
                }
            }
        }
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = 1;

        while (!queue.isEmpty()) {
            int here = queue.poll();

            for (int i = 0; i <= list[here].size(); i++) {
                int there = list[here].get(i);
                if (visited[there] == 0) {
                    queue.add(there);
                    visited[there] = 1;
                }
            }
        }
    }

}
