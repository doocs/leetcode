public class Solution {
    public int[] FindOrder(int numCourses, int[][] prerequisites) {
        var g = new List<int>[numCourses];
        for (int i = 0; i < numCourses; ++i)
        {
            g[i] = new List<int>();
        }
        var indeg = new int[numCourses];
        foreach (var p in prerequisites)
        {
            int a = p[0], b = p[1];
            g[b].Add(a);
            ++indeg[a];
        }
        var q = new Queue<int>();
        for (int i = 0; i < numCourses; ++i)
        {
            if (indeg[i] == 0) q.Enqueue(i);
        }
        var ans = new int[numCourses];
        var cnt = 0;
        while (q.Count > 0)
        {
            int i = q.Dequeue();
            ans[cnt++] = i;
            foreach (int j in g[i])
            {
                if (--indeg[j] == 0) q.Enqueue(j);
            }
        }
        return cnt == numCourses ? ans : new int[0];
    }
}