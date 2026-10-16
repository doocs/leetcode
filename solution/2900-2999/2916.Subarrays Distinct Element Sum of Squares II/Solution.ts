function sumCounts(nums: number[]): number {
    const MOD = 1000000007n;
    const n = nums.length;
    const size = 4 * n;
    const sum = new Array<bigint>(size).fill(0n);     
    const sumSq = new Array<bigint>(size).fill(0n);    
    const lazy = new Array<bigint>(size).fill(0n);     
    const build = (node: number, l: number, r: number): void => {
        if (l === r) {
            sum[node] = 0n;
            sumSq[node] = 0n;
            return;
        }
        const mid = (l + r) >> 1;
        build(node << 1, l, mid);
        build((node << 1) | 1, mid + 1, r);
    };
    const apply = (node: number, l: number, r: number, v: bigint): void => {
        const len = BigInt(r - l + 1);
        sumSq[node] = (sumSq[node] + 2n * v * sum[node] + v * v * len) % MOD;
        sum[node] = (sum[node] + v * len) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    };
    const pushDown = (node: number, l: number, r: number): void => {
        if (lazy[node] !== 0n && l !== r) {
            const mid = (l + r) >> 1;
            const v = lazy[node];
            apply(node << 1, l, mid, v);
            apply((node << 1) | 1, mid + 1, r, v);
            lazy[node] = 0n;
        }
    };
    const update = (node: number, l: number, r: number, ql: number, qr: number, v: bigint): void => {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, v);
            return;
        }
        pushDown(node, l, r);
        const mid = (l + r) >> 1;
        update(node << 1, l, mid, ql, qr, v);
        update((node << 1) | 1, mid + 1, r, ql, qr, v);
        sum[node] = (sum[node << 1] + sum[(node << 1) | 1]) % MOD;
        sumSq[node] = (sumSq[node << 1] + sumSq[(node << 1) | 1]) % MOD;
    };
    build(1, 0, n - 1);
    const last = new Map<number, number>();
    let ans = 0n;
    for (let r = 0; r < n; r++) {
        const x = nums[r];
        const p = last.has(x) ? last.get(x)! : -1;
        update(1, 0, n - 1, p + 1, r, 1n);
        last.set(x, r);
        ans = (ans + sumSq[1]) % MOD;
    }
    return Number(ans);
}