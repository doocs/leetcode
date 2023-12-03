function movesToStamp(stamp: string, target: string): number[] {
    const m: number = stamp.length;
    const n: number = target.length;
    const indeg: number[] = Array(n - m + 1).fill(m);
    const g: number[][] = Array.from({ length: n }, () => []);
    const q: number[] = [];
    for (let i = 0; i < n - m + 1; ++i) {
        for (let j = 0; j < m; ++j) {
            if (target[i + j] === stamp[j]) {
                if (--indeg[i] === 0) {
                    q.push(i);
                }
            } else {
                g[i + j].push(i);
            }
        }
    }

    const ans: number[] = [];
    const vis: boolean[] = Array(n).fill(false);
    while (q.length) {
        const i: number = q.shift()!;
        ans.push(i);
        for (let j = 0; j < m; ++j) {
            if (!vis[i + j]) {
                vis[i + j] = true;
                for (const k of g[i + j]) {
                    if (--indeg[k] === 0) {
                        q.push(k);
                    }
                }
            }
        }
    }
    if (!vis.every(v => v)) {
        return [];
    }
    ans.reverse();
    return ans;
}
