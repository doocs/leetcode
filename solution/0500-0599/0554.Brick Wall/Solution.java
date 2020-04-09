class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> list : wall) {
            int s = 0;
            for (int i = 0; i < list.size() - 1; ++i) {
                s += list.get(i);
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
        int max = map.values().stream().max(Integer::compare).orElse(0);
        return wall.size() - max;
    }
}
