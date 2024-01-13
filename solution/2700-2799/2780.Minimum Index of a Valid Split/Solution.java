class Solution {
    public int minimumIndex(List<Integer> nums) {
        int x = 0, cnt = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : nums) {
            int t = freq.merge(v, 1, Integer::sum);
            if (cnt < t) {
                cnt = t;
                x = v;
            }
        }
        int cur = 0;
        for (int i = 1; i <= nums.size(); ++i) {
            if (nums.get(i - 1) == x) {
                ++cur;
                if (cur * 2 > i && (cnt - cur) * 2 > nums.size() - i) {
                    return i - 1;
                }
            }
        }
        return -1;
    }
}