class Solution {
    public int numSubarraysWithSum(int[] A, int S) {
        int[] map = new int[A.length + 1];
        map[0] = 1;
        int res = 0;
        int s = 0;
        for (int a : A) {
            s += a;
            if (s >= S) {
                res += map[s - S];
            }
            ++map[s];
        }
        return res;
    }
}
