using System;
using System.Collections.Generic;

public class Solution {
    private int n;
    private int[][] g;
    private int[,,] ans;
    private int[,,] degree;

    private const int HOLE = 0, MOUSE_START = 1, CAT_START = 2;
    private const int MOUSE_TURN = 0, CAT_TURN = 1;
    private const int MOUSE_WIN = 1, CAT_WIN = 2, TIE = 0;

    public int CatMouseGame(int[][] graph) {
        n = graph.Length;
        g = graph;
        ans = new int[n, n, 2];
        degree = new int[n, n, 2];

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                degree[i, j, MOUSE_TURN] = g[i].Length;
                degree[i, j, CAT_TURN] = g[j].Length;
            }
        }

        for (int i = 0; i < n; i++) {
            foreach (int j in g[HOLE]) {
                degree[i, j, CAT_TURN]--;
            }
        }

        Queue<int[]> q = new Queue<int[]>();

        for (int j = 1; j < n; j++) {
            ans[0, j, MOUSE_TURN] = MOUSE_WIN;
            ans[0, j, CAT_TURN] = MOUSE_WIN;
            q.Enqueue(new int[] { 0, j, MOUSE_TURN });
            q.Enqueue(new int[] { 0, j, CAT_TURN });
        }

        for (int i = 1; i < n; i++) {
            ans[i, i, MOUSE_TURN] = CAT_WIN;
            ans[i, i, CAT_TURN] = CAT_WIN;
            q.Enqueue(new int[] { i, i, MOUSE_TURN });
            q.Enqueue(new int[] { i, i, CAT_TURN });
        }

        while (q.Count > 0) {
            int[] state = q.Dequeue();
            int t = ans[state[0], state[1], state[2]];
            List<int[]> prevStates = GetPrevStates(state);

            foreach (var prevState in prevStates) {
                int pm = prevState[0], pc = prevState[1], pt = prevState[2];
                if (ans[pm, pc, pt] == TIE) {
                    bool win = (t == MOUSE_WIN && pt == MOUSE_TURN) || (t == CAT_WIN && pt == CAT_TURN);
                    if (win) {
                        ans[pm, pc, pt] = t;
                        q.Enqueue(prevState);
                    } else {
                        if (--degree[pm, pc, pt] == 0) {
                            ans[pm, pc, pt] = t;
                            q.Enqueue(prevState);
                        }
                    }
                }
            }
        }

        return ans[MOUSE_START, CAT_START, MOUSE_TURN];
    }

    private List<int[]> GetPrevStates(int[] state) {
        List<int[]> pre = new List<int[]>();
        int m = state[0], c = state[1], t = state[2];
        int pt = t ^ 1;

        if (pt == CAT_TURN) {
            foreach (int pc in g[c]) {
                if (pc != HOLE) {
                    pre.Add(new int[] { m, pc, pt });
                }
            }
        } else {
            foreach (int pm in g[m]) {
                pre.Add(new int[] { pm, c, pt });
            }
        }

        return pre;
    }
}
