class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int[][] g = new int[numCourses][numCourses];
        for (int[] e : prerequisites) {
            int cur = e[0];
            int pre = e[1];
            if (g[pre][cur] == 0) {
                ++indegree[cur];
                g[pre][cur] = 1;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int cnt = 0;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            ++cnt;
            for (int j = 0; j < numCourses; ++j) {
                if (g[i][j] == 1) {
                    g[i][j] = 0;
                    --indegree[j];
                    if (indegree[j] == 0) {
                        queue.offer(j);
                    }
                }
            }
        }

        return cnt == numCourses;
    }
}