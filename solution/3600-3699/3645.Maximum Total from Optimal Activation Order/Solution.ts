function maxTotal(value: number[], limit: number[]): number {
    const g = new Map<number, number[]>();
    for (let i = 0; i < value.length; i++) {
        if (!g.has(limit[i])) {
            g.set(limit[i], []);
        }
        g.get(limit[i])!.push(value[i]);
    }
    let ans = 0;
    for (const [lim, vs] of g) {
        vs.sort((a, b) => b - a);
        ans += vs.slice(0, lim).reduce((acc, v) => acc + v, 0);
    }
    return ans;
}
