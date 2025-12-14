function minMoves(balance: number[]): number {
    const sum = balance.reduce((a, b) => a + b, 0);
    if (sum < 0) {
        return -1;
    }

    const n = balance.length;
    let mn = balance[0],
        idx = 0;
    for (let i = 1; i < n; i++) {
        if (balance[i] < mn) {
            mn = balance[i];
            idx = i;
        }
    }

    if (mn >= 0) {
        return 0;
    }

    let need = -mn;
    let ans = 0;

    for (let j = 1; j < n; j++) {
        const a = balance[(idx - j + n) % n];
        const b = balance[(idx + j) % n];

        const c1 = Math.min(a, need);
        need -= c1;
        ans += c1 * j;

        const c2 = Math.min(b, need);
        need -= c2;
        ans += c2 * j;
    }

    return ans;
}
