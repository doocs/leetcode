function wateringPlants(plants: number[], capacity: number): number {
    const n = plants.length;
    let ans = 0;
    let water = capacity;
    for (let i = 0; i < n; i++) {
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
