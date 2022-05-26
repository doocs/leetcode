class Solution {
    public int wordCount(String[] startWords, String[] targetWords) {
        Set<Integer> s = new HashSet<>();
        for (String word : startWords) {
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask |= (1 << (c - 'a'));
            }
            s.add(mask);
        }
        int ans = 0;
        for (String word : targetWords) {
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask |= (1 << (c - 'a'));
            }
            for (char c : word.toCharArray()) {
                int t = mask ^ (1 << (c - 'a'));
                if (s.contains(t)) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
}