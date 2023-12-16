function subarrayBitwiseORs(arr: number[]): number {
    const s: Set<number> = new Set();
    const ans: Set<number> = new Set();
    for (const x of arr) {
        const t: Set<number> = new Set();
        for (const y of s) {
            t.add(x | y);
        }
        t.add(x);
        s.clear();
        for (const y of t) {
            s.add(y);
            ans.add(y);
        }
    }
    return ans.size;
}
