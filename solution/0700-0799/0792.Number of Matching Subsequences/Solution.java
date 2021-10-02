class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        List<String>[] buckets = new List[26];
        for (int i = 0; i < buckets.length; ++i) {
            buckets[i] = new ArrayList<>();
        }
        for (String word : words) {
            buckets[word.charAt(0) - 'a'].add(word);
        }
        int res = 0;
        for (char c : s.toCharArray()) {
            List<String> old = new ArrayList<>(buckets[c - 'a']);
            buckets[c - 'a'].clear();
            for (String t : old) {
                if (t.length() == 1) {
                    ++res;
                } else {
                    buckets[t.charAt(1) - 'a'].add(t.substring(1));
                }
            }
        }
        return res;
    }
}