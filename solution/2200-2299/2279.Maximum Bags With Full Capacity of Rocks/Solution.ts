function maximumBags(capacity: number[], rocks: number[], additionalRocks: number): number {
    const n = rocks.length;
    for (let i = 0; i < n; ++i) {
        capacity[i] -= rocks[i];
    }
    capacity.sort((a, b) => a - b);
    for (let i = 0; i < n; ++i) {
        additionalRocks -= capacity[i];
        if (additionalRocks < 0) {
            return i;
        }
    }
    return n;
}
