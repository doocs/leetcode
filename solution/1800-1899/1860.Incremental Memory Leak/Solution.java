class Solution {
    public int[] memLeak(int memory1, int memory2) {
        int i = 1;
        while (true) {
            if (memory1 >= memory2) {
                if (memory1 < i) {
                    break;
                }
                memory1 -= i;
            } else {
                if (memory2 < i) {
                    break;
                }
                memory2 -= i;
            }
            ++i;
        }
        return new int[]{i, memory1, memory2};
    }
}