class Solution {
    public List<String> commonChars(String[] words) {
        int[] freq = new int[26];
        Arrays.fill(freq, 10000);
        for (String word : words) {
            int[] t = new int[26];
            for (char c : word.toCharArray()) {
                ++t[c - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                freq[i] = Math.min(freq[i], t[i]);
            }
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            while (freq[i]-- > 0) {
                res.add(String.valueOf((char) (i + 'a')));
            }
        }
        return res;
    }
}