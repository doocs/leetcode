class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 != n2) {
            return false;
        }
        int[] counter = new int[128];
        for (int i = 0; i < n1; ++i) {
            ++counter[s1.charAt(i)];
            --counter[s2.charAt(i)];
        }
        for (int v : counter) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }
}