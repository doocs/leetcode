class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int[] m : matches) {
            int a = m[0], b = m[1];
            cnt.putIfAbsent(a, 0);
            cnt.put(b, cnt.getOrDefault(b, 0) + 1);
        }
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            int u = entry.getKey();
            int v = entry.getValue();
            if (v < 2) {
                ans.get(v).add(u);
            }
        }
        Collections.sort(ans.get(0));
        Collections.sort(ans.get(1));
        return ans;
    }
}