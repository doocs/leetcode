class Solution {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    List<Integer>[] graph = new List[numCourses];
    int[] inDegrees = new int[numCourses];

    for (int i = 0; i < numCourses; ++i)
      graph[i] = new ArrayList<>();

    for (int[] prerequisite : prerequisites) {
      graph[prerequisite[1]].add(prerequisite[0]);
      ++inDegrees[prerequisite[0]];
    }

    int[] ans = new int[numCourses];
    int index = 0;

    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < numCourses; ++i)
      if (inDegrees[i] == 0)
        q.offer(i);

    while (!q.isEmpty()) {
      int u = q.poll();
      ans[index++] = u;
      for (int v : graph[u])
        if (--inDegrees[v] == 0)
          q.offer(v);
    }

    return index == numCourses ? ans : new int[] {};
  }
}
