class Solution {
    public boolean isUnique(String astr) {
        int bitmap = 0;
        for (int i = 0, n = astr.length(); i < n; ++i) {
            int pos = astr.charAt(i) - 'a';
            if ((bitmap & (1 << pos)) != 0) {
                return false;
            }
            bitmap |= (1 << pos);
        }
        return true;
    }
}