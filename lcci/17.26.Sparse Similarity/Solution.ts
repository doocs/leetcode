function computeSimilarities(docs: number[][]): string[] {
    const n = docs.length;
    const d = new Map<number, number[]>();
    for (let i = 0; i < n; ++i) {
        for (const x of docs[i]) {
            if (!d.has(x)) {
                d.set(x, []);
            }
            d.get(x)!.push(i);
        }
    }
    const cnt = new Map<number, number>();
    for (const ids of d.values()) {
        const m = ids.length;
        for (let i = 0; i < m; ++i) {
            for (let j = i + 1; j < m; ++j) {
                const key = ids[i] * n + ids[j];
                cnt.set(key, (cnt.get(key) ?? 0) + 1);
            }
        }
    }
    const ans: string[] = [];
    for (const [key, v] of cnt) {
        const i = Math.floor(key / n);
        const j = key % n;
        const tot = docs[i].length + docs[j].length - v;
        const x = v / tot + 1e-9;
        ans.push(`${i},${j}: ${x.toFixed(4)}`);
    }
    return ans;
}
