class Solution {
    public int residuePrefixes(String s) {
        Set<Character> st = new HashSet<>();
        int ans = 0;
        for (int i = 1; i <= s.length(); i++) {
            char c = s.charAt(i - 1);
            st.add(c);
            if (st.size() == i % 3) {
                ans++;
            }
        }
        return ans;
    }
}
