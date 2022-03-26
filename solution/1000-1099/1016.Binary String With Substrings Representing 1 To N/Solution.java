class Solution {
    public boolean queryString(String s, int n) {
        for (int i = n; i > n / 2; i--) {
            if (!s.contains(Integer.toBinaryString(i))) {
                return false;
            }
        }
        return true;
    }
}
