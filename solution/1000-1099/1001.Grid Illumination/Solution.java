class Solution {
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        Set<Long> points = new HashSet<>();
        Map<Integer, Integer> rcnt = new HashMap<>();
        Map<Integer, Integer> ccnt = new HashMap<>();
        Map<Integer, Integer> dgcnt = new HashMap<>();
        Map<Integer, Integer> udgcnt = new HashMap<>();
        for (int[] l : lamps) {
            int r = l[0], c = l[1];
            long v = r * n + c;
            if (!points.contains(v)) {
                points.add(v);
                rcnt.put(r, rcnt.getOrDefault(r, 0) + 1);
                ccnt.put(c, ccnt.getOrDefault(c, 0) + 1);
                dgcnt.put(r - c, dgcnt.getOrDefault(r - c, 0) + 1);
                udgcnt.put(r + c, udgcnt.getOrDefault(r + c, 0) + 1);
            }   
        }
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {0, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int r = queries[i][0], c = queries[i][1];
            if (rcnt.getOrDefault(r, 0) > 0 || ccnt.getOrDefault(c, 0) > 0 || dgcnt.getOrDefault(r - c, 0) > 0 || udgcnt.getOrDefault(r + c, 0) > 0) {
                ans[i] = 1;
                for (int[] d : dirs) {
                    int x = r + d[0], y = c + d[1];
                    long v = x * n + y;
                    if (x < 0 || x >= n || y < 0 || y >= n || !points.contains(v)) {
                        continue;
                    }
                    points.remove(v);
                    rcnt.put(x, rcnt.get(x) - 1);
                    if (rcnt.get(x) == 0) {
                        rcnt.remove(x);
                    }
                    ccnt.put(y, ccnt.get(y) - 1);
                    if (ccnt.get(y) == 0) {
                        ccnt.remove(y);
                    }
                    dgcnt.put(x - y, dgcnt.get(x - y) - 1);
                    if (dgcnt.get(x - y) == 0) {
                        dgcnt.remove(x - y);
                    }
                    udgcnt.put(x + y, udgcnt.get(x + y) - 1);
                    if (udgcnt.get(x + y) == 0) {
                        udgcnt.remove(x + y);
                    }
                }
            }
        }
        return ans;
    }
}