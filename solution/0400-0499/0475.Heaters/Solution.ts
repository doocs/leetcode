function findRadius(houses: number[], heaters: number[]): number {
    houses.sort((a, b) => a - b);
    heaters.sort((a, b) => a - b);
    const check = (r: number): boolean => {
        const m = houses.length;
        const n = heaters.length;
        let i = 0;
        let j = 0;
        while (i < m) {
            if (j >= n) {
                return false;
            }
            const mi = heaters[j] - r;
            const mx = heaters[j] + r;
            if (houses[i] < mi) {
                return false;
            }
            if (houses[i] > mx) {
                ++j;
            } else {
                ++i;
            }
        }
        return true;
    };
    let left = 0;
    let right = 1e9;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (check(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
