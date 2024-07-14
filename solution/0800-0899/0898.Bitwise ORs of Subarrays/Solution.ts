function subarrayBitwiseORs(arr: number[]): number {
    const ans: Set<number> = new Set();
    const s: Set<number> = new Set();
    for (const x of arr) {
        const t: Set<number> = new Set([x]);
        for (const y of s) {
            t.add(x | y);
        }
        s.clear();
        for (const y of t) {
            ans.add(y);
            s.add(y);
        }
    }
    return ans.size;
}
