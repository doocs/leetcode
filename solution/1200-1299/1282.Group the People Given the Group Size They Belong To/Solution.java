class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < groupSizes.length; ++i) {
            mp.computeIfAbsent(groupSizes[i], k -> new ArrayList<>()).add(i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : mp.entrySet()) {
            int x = entry.getKey();
            List<Integer> indexes = entry.getValue();
            for (int i = 0; i < indexes.size(); i += x) {
                res.add(new ArrayList<>(indexes.subList(i, i + x)));
            }
        }
        return res;
    }
}