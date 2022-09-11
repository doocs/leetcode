function partitionString(s: string): number {
    const set = new Set();
    let res = 1;
    for (const c of s) {
        if (set.has(c)) {
            res++;
            set.clear();
        }
        set.add(c);
    }
    return res;
}
