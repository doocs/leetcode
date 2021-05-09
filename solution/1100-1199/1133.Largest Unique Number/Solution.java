class Solution {
    public int largestUniqueNumber(int[] A) {
        int[] counter = new int[1001];
        for (int a : A) {
            ++counter[a];
        }
        for (int i = 1000; i >= 0; --i) {
            if (counter[i] == 1) {
                return i;
            }
        }
        return -1;
    }
}