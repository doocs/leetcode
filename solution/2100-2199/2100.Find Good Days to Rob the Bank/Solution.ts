function goodDaysToRobBank(security: number[], time: number): number[] {
    const n = security.length;
    if (n <= time * 2) {
        return [];
    }
    const l = new Array(n).fill(0);
    const r = new Array(n).fill(0);
    for (let i = 1; i < n; i++) {
        if (security[i] <= security[i - 1]) {
            l[i] = l[i - 1] + 1;
        }
        if (security[n - i - 1] <= security[n - i]) {
            r[n - i - 1] = r[n - i] + 1;
        }
    }
    const res = [];
    for (let i = time; i < n - time; i++) {
        if (time <= Math.min(l[i], r[i])) {
            res.push(i);
        }
    }
    return res;
}
