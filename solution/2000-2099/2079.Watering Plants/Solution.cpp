class Solution {
public:
    int wateringPlants(vector<int>& plants, int capacity) {
        int ans = 0, cap = capacity;
        for (int i = 0; i < plants.size(); ++i) {
            if (cap >= plants[i]) {
                cap -= plants[i];
                ++ans;
            } else {
                cap = capacity - plants[i];
                ans += i * 2 + 1;
            }
        }
        return ans;
    }
};