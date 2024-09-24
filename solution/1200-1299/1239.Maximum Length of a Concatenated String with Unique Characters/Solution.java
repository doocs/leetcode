class Solution {
    public int maxLength(List<String> arr) {
        List<Integer> s = new ArrayList<>();
        s.add(0);
        int ans = 0;
        for (var t : arr) {
            int x = 0;
            for (char c : t.toCharArray()) {
                int b = c - 'a';
                if ((x >> b & 1) == 1) {
                    x = 0;
                    break;
                }
                x |= 1 << b;
            }
            if (x > 0) {
                for (int i = s.size() - 1; i >= 0; --i) {
                    int y = s.get(i);
                    if ((x & y) == 0) {
                        s.add(x | y);
                        ans = Math.max(ans, Integer.bitCount(x | y));
                    }
                }
            }
        }
        return ans;
    }
}
