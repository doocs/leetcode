function wateringPlants(plants: number[], capacity: number): number {
    let [ans, water] = [0, capacity];
    for (let i = 0; i < plants.length; ++i) {
        if (water >= plants[i]) {
            water -= plants[i];
            ++ans;
        } else {
            water = capacity - plants[i];
            ans += i * 2 + 1;
        }
    }
    return ans;
}
