class Solution {
    public int wateringPlants(int[] plants, int capacity) {
        int ans = 0, cap = capacity;
        for (int i = 0; i < plants.length; ++i) {
            if (cap >= plants[i]) {
                cap -= plants[i];
                ++ans;
            } else {
                ans += (i * 2 + 1);
                cap = capacity - plants[i];
            }
        }
        return ans;
    }
}