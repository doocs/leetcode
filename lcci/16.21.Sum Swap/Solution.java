class Solution {
    public int[] findSwapValues(int[] array1, int[] array2) {
        long s1 = 0, s2 = 0;
        Set<Integer> s = new HashSet<>();
        for (int x : array1) {
            s1 += x;
        }
        for (int x : array2) {
            s2 += x;
            s.add(x);
        }
        long diff = s1 - s2;
        if (diff % 2 != 0) {
            return new int[0];
        }
        diff /= 2;
        for (int a : array1) {
            int b = (int) (a - diff);
            if (s.contains(b)) {
                return new int[] {a, b};
            }
        }
        return new int[0];
    }
}