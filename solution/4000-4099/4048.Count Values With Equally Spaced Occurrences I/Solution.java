class Solution {
    public int countSpecialIntegers(int[] nums) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            g.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int ans = 0;
        for (List<Integer> pos : g.values()) {
            if (pos.size() == 3 && pos.get(0) + pos.get(2) == pos.get(1) * 2) {
                ans++;
            }
        }
        return ans;
    }
}