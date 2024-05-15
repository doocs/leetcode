class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int a = capacityA, b = capacityB;
        int ans = 0;
        int i = 0, j = plants.length - 1;
        for (; i < j; ++i, --j) {
            if (a < plants[i]) {
                ++ans;
                a = capacityA;
            }
            a -= plants[i];
            if (b < plants[j]) {
                ++ans;
                b = capacityB;
            }
            b -= plants[j];
        }
        ans += i == j && Math.max(a, b) < plants[i] ? 1 : 0;
        return ans;
    }
}