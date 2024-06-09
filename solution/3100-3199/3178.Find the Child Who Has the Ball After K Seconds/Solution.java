class Solution {
    public int numberOfChild(int n, int k) {
        int mod = k % (n - 1);
        k /= (n - 1);
        return k % 2 == 1 ? n - mod - 1 : mod;
    }
}