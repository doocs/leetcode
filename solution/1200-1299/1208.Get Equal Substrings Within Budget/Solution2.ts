function equalSubstring(s: string, t: string, maxCost: number): number {
    const getCost = (i: number) => Math.abs(s[i].charCodeAt(0) - t[i].charCodeAt(0));
    const n = s.length;
    let res = 0;
    let l = 0;
    let r = -1;
    let cost = 0;

    while (++r < n) {
        cost += getCost(r);

        if (cost <= maxCost) res = Math.max(res, r - l + 1);
        else cost -= getCost(l++);
    }

    return res;
}
