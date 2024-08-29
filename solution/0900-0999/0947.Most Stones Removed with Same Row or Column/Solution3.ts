function removeStones(stones: number[][]): number {
    const n = stones.length;
    const g: number[][] = Array.from({ length: n }, () => []);

    for (let i = 0; i < n; i++) {
        const [y, x] = stones[i];
        for (let j = i + 1; j < n; j++) {
            if (y === stones[j][0] || x === stones[j][1]) {
                g[i].push(j);
                g[j].push(i);
            }
        }
    }

    const dfs = (i: number) => {
        const seen = new Set<number>();

        let q = [i];
        while (q.length) {
            const qNext: number[] = [];

            for (const i of q) {
                if (seen.has(i)) continue;
                seen.add(i);
                set.delete(i);
                qNext.push(...g[i]);
            }

            q = qNext;
        }
    };

    const set = new Set(Array.from({ length: n }, (_, i) => i));
    let ans = n;
    for (const i of set) {
        dfs(i);
        ans--;
    }

    return ans;
}
