function equalSubstring(s: string, t: string, maxCost: number): number {
    const getCost = (i: number) => Math.abs(s[i].charCodeAt(0) - t[i].charCodeAt(0));
    const n = s.length;
    let ans = 0,
        cost = 0;
    for (let l = 0, r = 0; r < n; ++r) {
        cost += getCost(r);
        while (cost > maxCost) {
            cost -= getCost(l++);
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
