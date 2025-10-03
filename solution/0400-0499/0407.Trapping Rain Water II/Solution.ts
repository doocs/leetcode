function trapRainWater(heightMap: number[][]): number {
    const m = heightMap.length;
    const n = heightMap[0].length;
    const vis: boolean[][] = Array.from({ length: m }, () => new Array(n).fill(false));
    const pq = new MinPriorityQueue<[number, number, number]>({
        compare: (a, b) => a[0] - b[0],
    });
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i === 0 || i === m - 1 || j === 0 || j === n - 1) {
                pq.enqueue([heightMap[i][j], i, j]);
                vis[i][j] = true;
            }
        }
    }

    let ans = 0;
    const dirs = [-1, 0, 1, 0, -1];
    while (!pq.isEmpty()) {
        const [h, i, j] = pq.dequeue();
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y]) {
                ans += Math.max(0, h - heightMap[x][y]);
                vis[x][y] = true;
                pq.enqueue([Math.max(h, heightMap[x][y]), x, y]);
            }
        }
    }
    return ans;
}
