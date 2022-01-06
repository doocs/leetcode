public class Solution {
    public int[] FindOrder(int numCourses, int[][] prerequisites) {
        var edges = new List<int>[numCourses];
        for (int i = 0; i < numCourses; ++i)
        {
            edges[i] = new List<int>();
        }
        var indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.Length; ++i)
        {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            edges[b].Add(a);
            ++indegree[a];
        }
        var q = new Queue<int>();
        for (int i = 0; i < numCourses; ++i)
        {
            if (indegree[i] == 0) q.Enqueue(i);
        }
        var ans = new int[numCourses];
        var n = 0;
        while (q.Count > 0)
        {
            int b = q.Dequeue();
            ans[n++] = b;
            foreach (int a in edges[b])
            {
                if (--indegree[a] == 0) q.Enqueue(a);
            }
        }
        return n == numCourses ? ans : new int[0];
    }
}