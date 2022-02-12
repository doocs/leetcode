class Solution {
    public int numberOfBeams(String[] bank) {
        int last = 0;
        int ans = 0;
        for (String b : bank) {
            int t = 0;
            for (char c : b.toCharArray()) {
                if (c == '1') {
                    ++t;
                }
            }
            if (t > 0) {
                ans += last * t;
                last = t;
            }
        }
        return ans;
    }
}