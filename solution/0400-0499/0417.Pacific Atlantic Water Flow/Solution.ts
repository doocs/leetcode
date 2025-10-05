function pacificAtlantic(heights: number[][]): number[][] {
    const m = heights.length,
        n = heights[0].length;
    const vis1: boolean[][] = Array.from({ length: m }, () => Array(n).fill(false));
    const vis2: boolean[][] = Array.from({ length: m }, () => Array(n).fill(false));
    const q1: [number, number][] = [];
    const q2: [number, number][] = [];
    const dirs = [-1, 0, 1, 0, -1];

    for (let i = 0; i < m; ++i) {
        q1.push([i, 0]);
        vis1[i][0] = true;
        q2.push([i, n - 1]);
        vis2[i][n - 1] = true;
    }
    for (let j = 0; j < n; ++j) {
        q1.push([0, j]);
        vis1[0][j] = true;
        q2.push([m - 1, j]);
        vis2[m - 1][j] = true;
    }

    const bfs = (q: [number, number][], vis: boolean[][]) => {
        while (q.length) {
            const [x, y] = q.shift()!;
            for (let k = 0; k < 4; ++k) {
                const nx = x + dirs[k],
                    ny = y + dirs[k + 1];
                if (
                    nx >= 0 &&
                    nx < m &&
                    ny >= 0 &&
                    ny < n &&
                    !vis[nx][ny] &&
                    heights[nx][ny] >= heights[x][y]
                ) {
                    vis[nx][ny] = true;
                    q.push([nx, ny]);
                }
            }
        }
    };

    bfs(q1, vis1);
    bfs(q2, vis2);

    const ans: number[][] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (vis1[i][j] && vis2[i][j]) {
                ans.push([i, j]);
            }
        }
    }
    return ans;
}
