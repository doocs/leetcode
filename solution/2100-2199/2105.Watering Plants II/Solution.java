class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int i = 0, j = plants.length - 1;
        int ans = 0, a = capacityA, b = capacityB;
        while (i <= j) {
            if (i == j) {
                if (Math.max(capacityA, capacityB) < plants[i]) {
                    ++ans;
                }
                break;
            }
            if (capacityA < plants[i]) {
                capacityA = a - plants[i];
                ++ans;
            } else {
                capacityA -= plants[i];
            }
            if (capacityB < plants[j]) {
                capacityB = b - plants[j];
                ++ans;
            } else {
                capacityB -= plants[j];
            }
            ++i;
            --j;
        }
        return ans;
    }
}