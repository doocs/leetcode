class Solution {
    public boolean hasAllCodes(String s, int k) {
        int counter = 1 << k;
        Set<String> exists = new HashSet<>();
        for (int i = k; i <= s.length(); ++i) {
            String t = s.substring(i - k, i);
            if (!exists.contains(t)) {
                exists.add(t);
                --counter;
            }
            if (counter == 0) {
                return true;
            }
        }
        return false;
    }
}