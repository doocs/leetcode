function orangesRotting(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let count = 0;
    const queue = [];
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 1) {
                count++;
            } else if (grid[i][j] === 2) {
                queue.push([i, j]);
            }
        }
    }
    let res = 0;
    const dris = [1, 0, -1, 0, 1];
    while (count !== 0 && queue.length !== 0) {
        for (let i = queue.length; i > 0; i--) {
            const [x, y] = queue.shift();
            for (let j = 0; j < 4; j++) {
                const newX = x + dris[j];
                const newY = y + dris[j + 1];
                if (
                    newX >= 0 &&
                    newX < m &&
                    newY >= 0 &&
                    newY <= n &&
                    grid[newX][newY] === 1
                ) {
                    grid[newX][newY] = 2;
                    queue.push([newX, newY]);
                    count--;
                }
            }
        }
        res++;
    }
    if (count != 0) {
        return -1;
    }
    return res;
}
