function maxDistance(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let queue: Array<Array<number>> = [];
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j]) {
                queue.push([i, j]);
            }
        }
    }
    if ([0, m * n].includes(queue.length)) return -1;
    const directions = [
        [0, 1],
        [0, -1],
        [1, 0],
        [-1, 0],
    ];
    let depth = 1;
    while (queue.length) {
        depth += 1;
        let nextLevel: Array<Array<number>> = [];
        for (let [x, y] of queue) {
            for (let [dx, dy] of directions) {
                const [i, j] = [x + dx, y + dy];
                if (i >= 0 && i < m && j >= 0 && j < n && !grid[i][j]) {
                    grid[i][j] = depth;
                    nextLevel.push([i, j]);
                }
            }
        }
        queue = nextLevel;
    }
    return depth - 2;
}
