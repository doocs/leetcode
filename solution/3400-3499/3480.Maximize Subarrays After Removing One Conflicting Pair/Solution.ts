function maxSubarrays(n: number, conflictingPairs: number[][]): number {
    const g: number[][] = Array.from({ length: n + 1 }, () => []);
    for (let [a, b] of conflictingPairs) {
        if (a > b) {
            [a, b] = [b, a];
        }
        g[a].push(b);
    }

    const cnt: number[] = Array(n + 2).fill(0);
    let ans = 0,
        add = 0;
    let b1 = n + 1,
        b2 = n + 1;

    for (let a = n; a > 0; a--) {
        for (const b of g[a]) {
            if (b < b1) {
                b2 = b1;
                b1 = b;
            } else if (b < b2) {
                b2 = b;
            }
        }
        ans += b1 - a;
        cnt[b1] += b2 - b1;
        add = Math.max(add, cnt[b1]);
    }

    ans += add;
    return ans;
}
