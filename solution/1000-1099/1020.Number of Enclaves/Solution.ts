function numEnclaves(grid: number[][]): number {
    let res = 0;
    const m = grid.length;
    const n = grid[0].length;
    const dfs = (y: number, x: number) => {
        if (x < 0 || x >= n || y < 0 || y >= m || grid[y][x] === 0) {
            return;
        }
        grid[y][x] = 0;
        dfs(y + 1, x);
        dfs(y, x + 1);
        dfs(y - 1, x);
        dfs(y, x - 1);
    };
    for (let i = 0; i < n; i++) {
        dfs(0, i);
        dfs(m - 1, i);
    }
    for (let i = 0; i < m; i++) {
        dfs(i, 0);
        dfs(i, n - 1);
    }
    for (let i = 1; i < m - 1; i++) {
        for (let j = 1; j < n - 1; j++) {
            if (grid[i][j] === 1) {
                res++;
            }
        }
    }
    return res;
}

// BFS
function numEnclaves(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let ans = 0;
    let queue = [];
    // 统计全部1, 临边的1加入队列
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            let cur = grid[i][j];
            if (cur) {
                ans++;
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    queue.push([i, j]);
                    ans--;
                }
            }
        }
    }

    let directions = [
        [-1, 0],
        [1, 0],
        [0, -1],
        [0, 1],
    ];
    while (queue.length) {
        let nextQueue = [];
        for (let [x, y] of queue) {
            for (let [dx, dy] of directions) {
                let [i, j] = [x + dx, y + dy];
                if (i > 0 && i < m - 1 && j > 0 && j < n - 1 && grid[i][j]) {
                    nextQueue.push([i, j]);
                    ans--;
                    grid[i][j] = 0;
                }
            }
            queue = nextQueue;
        }
    }
    return ans;
}
