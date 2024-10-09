class Solution {
    public List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        List<Integer> ans = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            if (v == 1 && !cnt.containsKey(x - 1) && !cnt.containsKey(x + 1)) {
                ans.add(x);
            }
        }
        return ans;
    }
}