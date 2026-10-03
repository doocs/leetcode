function minimumSubstringsInPartition(s: string): number {
    const n = s.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        const cnt: Map<number, number> = new Map();
        const freq: Map<number, number> = new Map();
        let ans = n - i;
        for (let j = i; j < n; ++j) {
            const k = s.charCodeAt(j) - 97;
            if (freq.has(cnt.get(k)!)) {
                freq.set(cnt.get(k)!, freq.get(cnt.get(k)!)! - 1);
                if (freq.get(cnt.get(k)!) === 0) {
                    freq.delete(cnt.get(k)!);
                }
            }
            cnt.set(k, (cnt.get(k) || 0) + 1);
            freq.set(cnt.get(k)!, (freq.get(cnt.get(k)!) || 0) + 1);
            if (freq.size === 1) {
                ans = Math.min(ans, 1 + f[j + 1]);
            }
        }
        f[i] = ans;
    }
    return f[0];
}
