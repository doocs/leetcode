function equalSubstring(s: string, t: string, maxCost: number): number {
    const getCost = (i: number) => Math.abs(s[i].charCodeAt(0) - t[i].charCodeAt(0));
    const n = s.length;
    let cost = 0;
    let l = 0;
    for (let r = 0; r < n; ++r) {
        cost += getCost(r);
        if (cost > maxCost) {
            cost -= getCost(l++);
        }
    }
    return n - l;
}
