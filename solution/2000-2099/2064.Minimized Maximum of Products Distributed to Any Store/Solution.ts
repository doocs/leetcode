function minimizedMaximum(n: number, quantities: number[]): number {
    let left = 1,
        right = 1e5;
    while (left < right) {
        const mid = (left + right) >> 1;
        let s = 0;
        for (let q of quantities) {
            s += Math.floor((q - 1) / mid) + 1;
        }
        if (s <= n) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
