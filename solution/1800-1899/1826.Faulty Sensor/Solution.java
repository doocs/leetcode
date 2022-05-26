class Solution {
    public int badSensor(int[] sensor1, int[] sensor2) {
        int i = 0;
        int n = sensor1.length;
        for (; i < n - 1 && sensor1[i] == sensor2[i]; ++i) {}
        for (; i < n - 1; ++i) {
            if (sensor1[i + 1] != sensor2[i]) {
                return 1;
            }
            if (sensor1[i] != sensor2[i + 1]) {
                return 2;
            }
        }
        return -1;
    }
}