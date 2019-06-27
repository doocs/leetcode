class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int res = 0;
        int s = 0;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i] == 1 ? 1 : -1;
            if (map.containsKey(s)) {
                res = Math.max(res, i - map.get(s));
            } else {
                map.put(s, i);
            }
        }
        return res;
    }
}
