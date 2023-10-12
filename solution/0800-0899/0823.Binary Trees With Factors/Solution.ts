function numFactoredBinaryTrees(arr: number[]): number {
    const mod = 10 ** 9 + 7;
    arr.sort((a, b) => a - b);
    const idx: Map<number, number> = new Map();
    const n = arr.length;
    for (let i = 0; i < n; ++i) {
        idx.set(arr[i], i);
    }
    const f: number[] = new Array(n).fill(1);
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
    return f.reduce((a, b) => a + b) % mod;
}
