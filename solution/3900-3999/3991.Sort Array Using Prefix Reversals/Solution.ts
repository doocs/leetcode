function sortArray(nums: number[], pre: number[]): number {
    const n = nums.length;

    let target = 0;
    for (let i = 0; i < n; i++) {
        target = target * 8 + i;
    }

    let start = 0;
    for (const x of nums) {
        start = start * 8 + x;
    }

    if (start === target) {
        return 0;
    }

    const vis = new Set<number>();
    vis.add(start);

    const q: [number[], number][] = [[nums.slice(), 0]];

    while (q.length) {
        const [state, dist] = q.shift()!;
        const nd = dist + 1;

        for (const x of pre) {
            const nxt = state.slice();

            for (let l = 0, r = x - 1; l < r; l++, r--) {
                [nxt[l], nxt[r]] = [nxt[r], nxt[l]];
            }

            let key = 0;
            for (const v of nxt) {
                key = key * 8 + v;
            }

            if (key === target) {
                return nd;
            }

            if (!vis.has(key)) {
                vis.add(key);
                q.push([nxt, nd]);
            }
        }
    }

    return -1;
}
