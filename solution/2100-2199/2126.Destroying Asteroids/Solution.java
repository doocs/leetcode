class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long m = mass;
        for (int v : asteroids) {
            if (m < v) {
                return false;
            }
            m += v;
        }
        return true;
    }
}