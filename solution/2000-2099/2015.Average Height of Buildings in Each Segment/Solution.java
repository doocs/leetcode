class Solution {
    public int[][] averageHeightOfBuildings(int[][] buildings) {
        TreeMap<Integer, Integer> height = new TreeMap<>();
        TreeMap<Integer, Integer> cnt = new TreeMap<>();
        for (var v : buildings) {
            int s = v[0], e = v[1], h = v[2];
            cnt.put(s, cnt.getOrDefault(s, 0) + 1);
            cnt.put(e, cnt.getOrDefault(e, 0) - 1);
            height.put(s, height.getOrDefault(s, 0) + h);
            height.put(e, height.getOrDefault(e, 0) - h);
        }
        int i = 0, h = 0, n = 0;
        List<int[]> res = new ArrayList<>();
        for (int j : cnt.keySet()) {
            if (n > 0) {
                int[] t = new int[] {i, j, h / n};
                int k = res.size() - 1;
                if (k >= 0 && res.get(k)[1] == i && res.get(k)[2] == t[2]) {
                    res.get(k)[1] = j;
                } else {
                    res.add(t);
                }
            }
            h += height.get(j);
            n += cnt.get(j);
            i = j;
        }
        int[][] ans = new int[res.size()][3];
        for (i = 0; i < ans.length; ++i) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}