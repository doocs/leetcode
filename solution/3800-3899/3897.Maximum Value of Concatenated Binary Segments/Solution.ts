function maxValue(nums1: number[], nums0: number[]): number {
    const MOD = 1_000_000_007;
    const pairs: [number, number][] = [];
    let b = 0;

    for (let i = 0; i < nums1.length; ++i) {
        pairs.push([nums1[i], nums0[i]]);
        b += nums1[i] + nums0[i];
    }

    const group = ([x, y]: [number, number]): number => {
        if (y === 0) {
            return 0;
        }
        if (x > 0) {
            return 1;
        }
        return 2;
    };

    pairs.sort((a, c) => {
        const g1 = group(a);
        const g2 = group(c);
        if (g1 !== g2) {
            return g1 - g2;
        }
        if (g1 === 0) {
            return c[0] - a[0];
        }
        if (g1 === 1) {
            if (a[0] !== c[0]) {
                return c[0] - a[0];
            }
            return a[1] - c[1];
        }
        return a[1] - c[1];
    });

    const p = Array<number>(b).fill(1);
    for (let i = 1; i < b; ++i) {
        p[i] = (p[i - 1] * 2) % MOD;
    }

    let ans = 0;
    --b;
    for (let [cnt1, cnt0] of pairs) {
        while (cnt1 > 0) {
            ans = (ans + p[b]) % MOD;
            --b;
            --cnt1;
        }
        b -= cnt0;
    }
    return ans;
}
