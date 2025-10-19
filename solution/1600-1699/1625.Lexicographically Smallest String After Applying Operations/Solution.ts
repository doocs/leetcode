function findLexSmallestString(s: string, a: number, b: number): string {
    const q: string[] = [s];
    const vis = new Set<string>([s]);
    let ans = s;
    let i = 0;
    while (i < q.length) {
        s = q[i++];
        if (ans > s) {
            ans = s;
        }
        const t1 = s
            .split('')
            .map((c, j) => (j & 1 ? String((Number(c) + a) % 10) : c))
            .join('');
        const t2 = s.slice(-b) + s.slice(0, -b);
        for (const t of [t1, t2]) {
            if (!vis.has(t)) {
                vis.add(t);
                q.push(t);
            }
        }
    }
    return ans;
}
