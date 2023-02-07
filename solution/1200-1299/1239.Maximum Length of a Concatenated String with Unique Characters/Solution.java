class Solution {
    public int maxLength(List<String> arr) {
        int ans = 0;
        List<Integer> masks = new ArrayList<>();
        masks.add(0);
        for (var s : arr) {
            int mask = 0;
            for (int i = 0; i < s.length(); ++i) {
                int j = s.charAt(i) - 'a';
                if (((mask >> j) & 1) == 1) {
                    mask = 0;
                    break;
                }
                mask |= 1 << j;
            }
            if (mask == 0) {
                continue;
            }
            int n = masks.size();
            for (int i = 0; i < n; ++i) {
                int m = masks.get(i);
                if ((m & mask) == 0) {
                    masks.add(m | mask);
                    ans = Math.max(ans, Integer.bitCount(m | mask));
                }
            }
        }
        return ans;
    }
}