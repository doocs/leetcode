class Solution {
    public int countOfSubstrings(String word, int k) {
        return f(word, k) - f(word, k + 1);
    }

    private int f(String word, int k) {
        int ans = 0;
        int l = 0, x = 0;
        Map<Character, Integer> cnt = new HashMap<>(5);
        for (char c : word.toCharArray()) {
            if (vowel(c)) {
                cnt.merge(c, 1, Integer::sum);
            } else {
                ++x;
            }
            while (x >= k && cnt.size() == 5) {
                char d = word.charAt(l++);
                if (vowel(d)) {
                    if (cnt.merge(d, -1, Integer::sum) == 0) {
                        cnt.remove(d);
                    }
                } else {
                    --x;
                }
            }
            ans += l;
        }
        return ans;
    }

    private boolean vowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
