int wateringPlants(int* plants, int plantsSize, int capacity) {
    int ans = 0;
    int water = capacity;
    for (int i = 0; i < plantsSize; i++) {
        if (water < plants[i]) {
            ans += i * 2 + 1;
            water = capacity - plants[i];
        } else {
            ans++;
            water -= plants[i];
        }
    }
    return ans;
}
