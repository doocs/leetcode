function colorGrid(n: number, m: number, sources: number[][]): number[][] {
    const ans: number[][] = Array.from({ length: n }, () => Array(m).fill(0));
    let q: number[][] = [...sources.map(s => [...s])];
    const dirs = [-1, 0, 1, 0, -1];
    for (const [r, c, color] of q) {
        ans[r][c] = color;
    }
    while (q.length > 0) {
        const vis: Map<string, number> = new Map();
        for (const [r, c, color] of q) {
            for (let i = 0; i < 4; i++) {
                const x = r + dirs[i],
                    y = c + dirs[i + 1];
                if (x >= 0 && x < n && y >= 0 && y < m && ans[x][y] === 0) {
                    const key = `${x},${y}`;
                    vis.set(key, Math.max(vis.get(key) || 0, color));
                }
            }
        }
        q = [];
        for (const [key, color] of vis.entries()) {
            const [x, y] = key.split(',').map(Number);
            ans[x][y] = color;
            q.push([x, y, color]);
        }
    }
    return ans;
}
