package algorithm.dfs_bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Modeling {

    static int[] visited = new int[100];
    static ArrayList<Integer>[] list = new ArrayList[100];
    static ArrayList<Integer>[] graph = new ArrayList[100];

    static int[] arr;
    static int[] S;

    public static void dfs(int here) {
        visited[here] = 1;

        for (int i = 0; i < list[here].size(); i++) {
            int there = list[here].get(i);
            if (visited[there] == 0) {
                dfs(there);
            }
        }
    }
    /*트리의 탐색*/
    public static void dfs(int now, int parent){
        for (int i = 0; i < graph[now].size(); i++) {
            int next = graph[now].get(i);
            if(next == parent){
                continue;
            }
            dfs(next, now);
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

    /*public static int binarySearch(int size, int key) {
        int lb = -1, ub = size - 1, m;
        while (lb + 1 < ub) {
            m = lb + (ub - lb) / 2; // overflow 방지
            if (arr[m] >= key) ub = m;
            else lb = m;
        }
        return ub > size ? -1 : arr[ub] == key ? ub : -1;
    }*/


    public static int binarySearch(int N, int X) {
        int start = 0;
        int end = N - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int nowNum = S[mid];

            if (nowNum < X) {
                start = mid + 1;
            } else if (nowNum > X) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int lowerBound(int N, int X) {
        int start = 0;
        int end = N - 1;

        int mid = 0;
        while (start < end) {
            mid = (start + end) / 2;
            int nowNum = S[mid];

            if (nowNum < X) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return mid;
    }
}
