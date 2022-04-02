public class Solution {
    public static int longestSubstring(String s, int k) {
        int maxLength = 0;
        int n = s.length();

        for (int i=1; i<27; i++) {
            HashMap<Character, Integer> counter = new HashMap<>();
            int left = 0;
            int right = 0;
            int unique = 0;
            int kCount = 0;

            while (right < n) {
                if (unique <= i) {
                    char r = s.charAt(right);
                    if (counter.containsKey(r))
                        counter.put(r, counter.get(r) + 1);
                    else
                        counter.put(r, 1);
                    
                    if (counter.get(r) == 1)
                        unique += 1;
                    if (counter.get(r) == k)
                        kCount += 1;
                    
                    right += 1;
                } else {
                    char l = s.charAt(left);
                    if (counter.containsKey(l))
                        counter.put(l, counter.get(l) - 1);
                    else
                        counter.put(l, 1);

                    if (counter.get(l) == 0)
                        unique -= 1;
                    if (counter.get(l) == k-1)
                        kCount -= 1;

                    left += 1;
                }

                if (unique == i && kCount == i)
                    maxLength = Math.max(maxLength, right - left);
            }
        }
        return maxLength;
    }
}
