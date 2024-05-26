function queryResults(limit: number, queries: number[][]): number[] {
    const g = new Map<number, number>();
    const cnt = new Map<number, number>();
    const ans: number[] = [];
    for (const [x, y] of queries) {
        cnt.set(y, (cnt.get(y) ?? 0) + 1);
        if (g.has(x)) {
            const v = g.get(x)!;
            cnt.set(v, cnt.get(v)! - 1);
            if (cnt.get(v) === 0) {
                cnt.delete(v);
            }
        }
        g.set(x, y);
        ans.push(cnt.size);
    }
    return ans;
}
