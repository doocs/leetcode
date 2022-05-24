function movingCount(m: number, n: number, k: number): number {
    const set = new Set();
    const dfs = (i: number, j: number) => {
        const key = `${i},${j}`;
        if (
            i === m ||
            j === n ||
            set.has(key) ||
            `${i}${j}`.split('').reduce((r, v) => r + Number(v), 0) > k
        ) {
            return;
        }
        set.add(key);
        dfs(i + 1, j);
        dfs(i, j + 1);
    };
    dfs(0, 0);
    return set.size;
}
