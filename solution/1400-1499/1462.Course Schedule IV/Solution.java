class Solution {
    public List<Boolean> checkIfPrerequisite(
        int numCourses, int[][] prerequisites, int[][] queries) {
        int[][] g = new int[numCourses][numCourses];
        for (int i = 0; i < numCourses; ++i) {
            Arrays.fill(g[i], -1);
        }
        for (int[] e : prerequisites) {
            int a = e[0], b = e[1];
            g[a][b] = 1;
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] e : queries) {
            int a = e[0], b = e[1];
            ans.add(dfs(a, b, g));
        }
        return ans;
    }

    private boolean dfs(int a, int b, int[][] g) {
        if (g[a][b] != -1) {
            return g[a][b] == 1;
        }
        if (a == b) {
            g[a][b] = 1;
            return true;
        }
        for (int i = 0; i < g[a].length; ++i) {
            if (g[a][i] == 1 && dfs(i, b, g)) {
                g[a][b] = 1;
                return true;
            }
        }
        g[a][b] = 0;
        return false;
    }
}