class Solution {
    private Map<Integer, Integer> p = new HashMap<>();
    private Map<Integer, Integer> rank = new HashMap<>();

    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        TreeMap<Integer, List<int[]>> d = new TreeMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                d.computeIfAbsent(matrix[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        int[][] ans = new int[m][n];
        for (var e : d.entrySet()) {
            int v = e.getKey();
            var g = e.getValue();
            p.clear();
            rank.clear();
            for (int[] x : g) {
                union(x[0], x[1] + 500);
            }
            for (int[] x : g) {
                int i = x[0], j = x[1];
                rank.put(find(i), Math.max(rank.getOrDefault(find(i), 0), Math.max(rowMax[i], colMax[j])));
            }
            for (int[] x : g) {
                int i = x[0], j = x[1];
                ans[i][j] = 1 + rank.getOrDefault(find(i), 0);
                rowMax[i] = ans[i][j];
                colMax[j] = ans[i][j];
            }
        }
        return ans;
    }
    
    private void union(int a, int b) {
        p.put(find(a), find(b));
    }

    private int find(int x) {
        p.putIfAbsent(x, x);
        if (p.get(x) != x) {
            p.put(x, find(p.get(x)));
        }
        return p.get(x);
    }
}