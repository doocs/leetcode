function mostPopularCreator(creators: string[], ids: string[], views: number[]): string[][] {
    const cnt: Map<string, number> = new Map();
    const d: Map<string, number> = new Map();
    const n = ids.length;
    for (let k = 0; k < n; ++k) {
        const [c, i, v] = [creators[k], ids[k], views[k]];
        cnt.set(c, (cnt.get(c) ?? 0) + v);
        if (!d.has(c) || views[d.get(c)!] < v || (views[d.get(c)!] === v && ids[d.get(c)!] > i)) {
            d.set(c, k);
        }
    }
    const mx = Math.max(...cnt.values());
    const ans: string[][] = [];
    for (const [c, x] of cnt) {
        if (x === mx) {
            ans.push([c, ids[d.get(c)!]]);
        }
    }
    return ans;
}
