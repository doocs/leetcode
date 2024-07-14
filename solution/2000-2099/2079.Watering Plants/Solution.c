int wateringPlants(int* plants, int plantsSize, int capacity) {
    int ans = 0, water = capacity;
    for (int i = 0; i < plantsSize; ++i) {
        if (water >= plants[i]) {
            water -= plants[i];
            ans += 1;
        } else {
            water = capacity - plants[i];
            ans += i * 2 + 1;
        }
    }
    return ans;
}