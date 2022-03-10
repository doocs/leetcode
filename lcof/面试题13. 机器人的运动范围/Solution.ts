function movingCount(m: number, n: number, k: number): number {
    const set = new Set();
    const dfs = (y: number, x: number) => {
        if (y === m || x === n || set.has(`${y},${x}`)) {
            return;
        }
        let count = 0;
        const str = `${y}${x}`;
        for (const c of str) {
            count += Number(c);
        }
        if (count <= k) {
            set.add(`${y},${x}`);
            dfs(y + 1, x);
            dfs(y, x + 1);
        }
    };
    dfs(0, 0);
    return set.size;
}
