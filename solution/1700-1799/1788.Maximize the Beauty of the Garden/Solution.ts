function maximumBeauty(flowers: number[]): number {
    const n = flowers.length;
    const s: number[] = Array(n + 1).fill(0);
    const d: Map<number, number> = new Map();
    let ans = -Infinity;
    for (let i = 0; i < n; ++i) {
        const v = flowers[i];
        if (d.has(v)) {
            ans = Math.max(ans, s[i] - s[d.get(v)! + 1] + v * 2);
        } else {
            d.set(v, i);
        }
        s[i + 1] = s[i] + Math.max(v, 0);
    }
    return ans;
}
