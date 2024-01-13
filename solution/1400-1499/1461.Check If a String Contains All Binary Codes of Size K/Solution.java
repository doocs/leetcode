class Solution {
    public boolean hasAllCodes(String s, int k) {
        Set<String> ss = new HashSet<>();
        for (int i = 0; i < s.length() - k + 1; ++i) {
            ss.add(s.substring(i, i + k));
        }
        return ss.size() == 1 << k;
    }
}