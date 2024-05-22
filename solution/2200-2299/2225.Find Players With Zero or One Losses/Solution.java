class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (var e : matches) {
            cnt.putIfAbsent(e[0], 0);
            cnt.merge(e[1], 1, Integer::sum);
        }
        List<List<Integer>> ans = List.of(new ArrayList<>(), new ArrayList<>());
        for (var e : cnt.entrySet()) {
            if (e.getValue() < 2) {
                ans.get(e.getValue()).add(e.getKey());
            }
        }
        Collections.sort(ans.get(0));
        Collections.sort(ans.get(1));
        return ans;
    }
}