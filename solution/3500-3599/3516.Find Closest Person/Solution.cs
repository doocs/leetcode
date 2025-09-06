public class Solution {
    public int FindClosest(int x, int y, int z) {
        int a = Math.Abs(x - z);
        int b = Math.Abs(y - z);
        return a == b ? 0 : (a < b ? 1 : 2);
    }
}
