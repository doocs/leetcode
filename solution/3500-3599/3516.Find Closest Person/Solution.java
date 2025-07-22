class Solution {
    public int findClosest(int x, int y, int z) {
        int a = Math.abs(x - z);
        int b = Math.abs(y - z);
        return a == b ? 0 : (a < b ? 1 : 2);
    }
}