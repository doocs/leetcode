function supplyWagon(supplies: number[]): number[] {
    for (let h = (supplies.length + 1) >> 1; h > 0; --h) {
        const n = supplies.length;
        let mi = 1 << 30;
        let k = 0;
        for (let i = 0; i < n - 1; ++i) {
            const x = supplies[i] + supplies[i + 1];
            if (mi > x) {
                mi = x;
                k = i;
            }
        }
        const t: number[] = new Array(n - 1);
        for (let i = 0, j = 0; i < n; ++i, ++j) {
            if (i === k) {
                t[j] = mi;
                ++i;
            } else {
                t[j] = supplies[i];
            }
        }
        supplies = t;
    }
    return supplies;
}
