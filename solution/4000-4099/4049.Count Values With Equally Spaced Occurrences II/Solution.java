class Solution {
    public int countSpecialIntegers(int[] nums) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            g.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int ans = 0;
        for (List<Integer> pos : g.values()) {
            if (pos.size() < 3) {
                continue;
            }

            int d = pos.get(1) - pos.get(0);
            boolean ok = true;
            for (int i = 1; i < pos.size(); i++) {
                if (pos.get(i) - pos.get(i - 1) != d) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
                ans++;
            }
        }
        return ans;
    }
}