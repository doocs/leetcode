class Solution {
    public int thirdMax(int[] nums) {
        long m1 = Long.MIN_VALUE;
        long m2 = Long.MIN_VALUE;
        long m3 = Long.MIN_VALUE;
        for (int x : nums) {
            if (x == m1 || x == m2 || x == m3) {
                continue;
            }
            if (x > m1) {
                m3 = m2;
                m2 = m1;
                m1 = x;
            } else if (x > m2) {
                m3 = m2;
                m2 = x;
            } else if (x > m3) {
                m3 = x;
            }
        }
        return (int) (m3 != Long.MIN_VALUE ? m3 : m1);
    }
}
