class Solution {
    public int countSegments(String s) {
        int ans = 0;
        for (String t : s.split(" ")) {
            if (!"".equals(t)) {
                ++ans;
            }
        }
        return ans;
    }
}