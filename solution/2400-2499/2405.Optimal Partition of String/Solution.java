class Solution {
    public int partitionString(String s) {
        Set<Character> ss = new HashSet<>();
        int ans = 1;
        for (char c : s.toCharArray()) {
            if (ss.contains(c)) {
                ++ans;
                ss.clear();
            }
            ss.add(c);
        }
        return ans;
    }
}