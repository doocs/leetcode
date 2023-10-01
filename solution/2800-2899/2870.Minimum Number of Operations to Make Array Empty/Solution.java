class Solution {
    public int minOperations(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            // count.put(num, count.getOrDefault(num, 0) + 1);
            count.merge(num, 1, Integer::sum);
        }
        int ans = 0;
        for (Integer c : count.values()) {
            if (c < 2) {
                return -1;
            }
            int r = c % 3;
            int d = c / 3;
            switch (r) {
                case (0) -> {
                    ans += d;
                }
                default -> {
                    ans += d + 1;
                }
            }
        }
        return ans;
    }
}