class Solution {
    public int countStaircase(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            var orders = new HashMap<Integer, Integer>();
            var cnt = new HashMap<Integer, Integer>();
            int ok = 0;
            for (int j = i; j < n; ++j) {
                int x = nums[j];
                orders.putIfAbsent(x, orders.size() + 1);
                int count = cnt.merge(x, 1, Integer::sum);
                int order = orders.get(x);
                if (count > order) {
                    break;
                }
                if (count == order && ++ok == cnt.size()) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}