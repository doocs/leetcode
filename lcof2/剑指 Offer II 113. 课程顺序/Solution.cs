public class Solution {
    public int[] FindOrder(int numCourses, int[][] prerequisites) {
        List<int>[] g = new List<int>[numCourses];
        for (int i = 0; i < numCourses; i++) {
            g[i] = new List<int>();
        }
        int[] indeg = new int[numCourses];
        foreach (var p in prerequisites) {
            int a = p[0], b = p[1];
            g[b].Add(a);
            ++indeg[a];
        }
        Queue<int> q = new Queue<int>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                q.Enqueue(i);
            }
        }
        int[] ans = new int[numCourses];
        int cnt = 0;
        while (q.Count > 0) {
            int i = q.Dequeue();
            ans[cnt++] = i;
            foreach (int j in g[i]) {
                if (--indeg[j] == 0) {
                    q.Enqueue(j);
                }
            }
        }
        return cnt == numCourses ? ans : new int[0];
    }
}