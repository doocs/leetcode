function minCost(s: string, cost: number[]): number {
    let tot = 0;
    const g: Map<string, number> = new Map();
    for (let i = 0; i < s.length; i++) {
        const c = s[i];
        const v = cost[i];
        tot += v;
        g.set(c, (g.get(c) ?? 0) + v);
    }
    let ans = tot;
    for (const x of g.values()) {
        ans = Math.min(ans, tot - x);
    }
    return ans;
}
