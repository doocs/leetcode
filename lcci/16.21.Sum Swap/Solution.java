class Solution {
    public int[] findSwapValues(int[] array1, int[] array2) {
        int s1 = 0, s2 = 0;
        Set<Integer> s = new HashSet<>();
        for (int a : array1) {
            s1 += a;
        }
        for (int b : array2) {
            s.add(b);
            s2 += b;
        }
        int diff = s1 - s2;
        if ((diff & 1) == 1) {
            return new int[]{};
        }
        diff >>= 1;
        for (int a : array1) {
            int b = a - diff;
            if (s.contains(b)) {
                return new int[]{a, b};
            }
        }
        return new int[]{};
    }
}