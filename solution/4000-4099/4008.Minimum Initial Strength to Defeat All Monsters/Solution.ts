function minInitialStrength(monsters: number[], boosts: number[][]): number {
    const n = monsters.length;
    const d = new Array<number>(n + 1).fill(0);

    for (const [l, r, v] of boosts) {
        d[l] += v;
        d[r + 1] -= v;
    }

    const check = (v: number): boolean => {
        let bonus = 0;
        for (let i = 0; i < n; i++) {
            bonus += d[i];
            if (v + bonus < monsters[i]) {
                return false;
            }
            v -= monsters[i];
            if (v < 0) {
                v = 0;
            }
        }
        return true;
    };

    let left = 0;
    let right = 1e15;
    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        if (check(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
