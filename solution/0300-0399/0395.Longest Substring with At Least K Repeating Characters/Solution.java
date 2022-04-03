class Solution {
    public int longestSubstring(String s, int k) {
        int maxLength = 0;
        int n = s.length();

        for (int i = 1; i < 27; i++) {
            Map<Character, Integer> counter = new HashMap<>();
            int left = 0;
            int right = 0;
            int unique = 0;
            int kCount = 0;

            while (right < n) {
                if (unique <= i) {
                    char r = s.charAt(right);
                    counter.put(r, counter.getOrDefault(r, 0) + 1);
                    if (counter.get(r) == 1) {
                        unique += 1;
                    }
                    if (counter.get(r) == k) {
                        kCount += 1;
                    }
                    right += 1;
                } else {
                    char l = s.charAt(left);
                    counter.put(l, counter.getOrDefault(l, 2) - 1);
                    if (counter.get(l) == 0) {
                        unique -= 1;
                    }
                    if (counter.get(l) == k - 1) {
                        kCount -= 1;
                    }
                    left += 1;
                }
                if (unique == i && kCount == i) {
                    maxLength = Math.max(maxLength, right - left);
                }
            }
        }
        return maxLength;
    }
}
