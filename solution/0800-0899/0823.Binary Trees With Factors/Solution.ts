function numFactoredBinaryTrees(arr: number[]): number {
    const mod = 1000000007n;
    arr.sort((a, b) => a - b);
    const idx: Map<number, number> = new Map();
    const n = arr.length;
    for (let i = 0; i < n; ++i) {
        idx.set(arr[i], i);
    }
    const f: bigint[] = new Array(n).fill(1n);
    for (let i = 0; i < n; ++i) {
        const a = arr[i];
        for (let j = 0; j < i; ++j) {
            const b = arr[j];
            if (a % b === 0) {
                const c = a / b;
                if (idx.has(c)) {
                    const k = idx.get(c)!;
                    f[i] = (f[i] + f[j] * f[k]) % mod;
                }
            }
        }
    }
    return Number(f.reduce((a, b) => a + b, 0n) % mod);
}
