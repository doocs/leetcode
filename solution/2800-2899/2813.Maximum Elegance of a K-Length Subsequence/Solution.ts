function findMaximumElegance(items: number[][], k: number): number {
    items.sort((a, b) => b[0] - a[0]);
    let tot = 0;
    const vis: Set<number> = new Set();
    const dup: number[] = [];
    for (const [p, c] of items.slice(0, k)) {
        tot += p;
        if (vis.has(c)) {
            dup.push(p);
        } else {
            vis.add(c);
        }
    }
    let ans = tot + vis.size ** 2;
    for (const [p, c] of items.slice(k)) {
        if (vis.has(c) || dup.length === 0) {
            continue;
        }
        tot += p - dup.pop()!;
        vis.add(c);
        ans = Math.max(ans, tot + vis.size ** 2);
    }
    return ans;
}
