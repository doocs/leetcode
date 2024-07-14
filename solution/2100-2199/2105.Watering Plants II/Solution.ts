function minimumRefill(plants: number[], capacityA: number, capacityB: number): number {
    let [a, b] = [capacityA, capacityB];
    let ans = 0;
    let [i, j] = [0, plants.length - 1];
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
    ans += i === j && Math.max(a, b) < plants[i] ? 1 : 0;
    return ans;
}
