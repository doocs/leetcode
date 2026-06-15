class Solution {
    public int getLength(int[] nums) {
        int n = nums.length;
        int ans = 1;
        for (int l = 0; l < n; l++) {
            Map<Integer, Integer> cnt = new HashMap<>();
            Map<Integer, Integer> freq = new HashMap<>();
            for (int r = l; r < n; r++) {
                int x = nums[r];
                int c = cnt.getOrDefault(x, 0);
                if (freq.getOrDefault(c, 0) > 0) {
                    freq.put(c, freq.get(c) - 1);
                    if (freq.get(c) == 0) {
                        freq.remove(c);
                    }
                }
                cnt.put(x, c + 1);
                freq.merge(cnt.get(x), 1, Integer::sum);
                int cx = cnt.get(x);
                if (cnt.size() == 1
                    || (freq.size() == 2
                        && (freq.getOrDefault(cx * 2, 0) > 0
                            || (cx % 2 == 0 && freq.getOrDefault(cx / 2, 0) > 0)))) {
                    ans = Math.max(ans, r - l + 1);
                }
            }
        }
        return ans;
    }
}
