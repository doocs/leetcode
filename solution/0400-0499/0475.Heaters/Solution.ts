function findRadius(houses: number[], heaters: number[]): number {
    houses.sort((a, b) => a - b);
    heaters.sort((a, b) => a - b);
    const m = houses.length,
        n = heaters.length;
    let ans = 0;
    for (let i = 0, j = 0; i < m; i++) {
        let cur = Math.abs(houses[i] - heaters[j]);
        while (
            j + 1 < n &&
            Math.abs(houses[i] - heaters[j]) >=
                Math.abs(houses[i] - heaters[j + 1])
        ) {
            cur = Math.min(Math.abs(houses[i] - heaters[++j]), cur);
        }
        ans = Math.max(cur, ans);
    }
    return ans;
}
