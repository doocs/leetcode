class Solution {
    public int wordCount(String[] startWords, String[] targetWords) {
        Set<Integer> s = new HashSet<>();
        for (var w : startWords) {
            int x = 0;
            for (var c : w.toCharArray()) {
                x |= 1 << (c - 'a');
            }
            s.add(x);
        }
        int ans = 0;
        for (var w : targetWords) {
            int x = 0;
            for (var c : w.toCharArray()) {
                x |= 1 << (c - 'a');
            }
            for (var c : w.toCharArray()) {
                if (s.contains(x ^ (1 << (c - 'a')))) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
}