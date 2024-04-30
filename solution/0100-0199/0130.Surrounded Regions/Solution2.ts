function solve(board: string[][]): void {
    const m = board.length;
    const n = board[0].length;
    const p: number[] = Array(m * n + 1)
        .fill(0)
        .map((_, i) => i);
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] === 'O') {
                if (i === 0 || i === m - 1 || j === 0 || j === n - 1) {
                    p[find(i * n + j)] = find(m * n);
                } else {
                    for (let k = 0; k < 4; ++k) {
                        const [x, y] = [i + dirs[k], j + dirs[k + 1]];
                        if (board[x][y] === 'O') {
                            p[find(x * n + y)] = find(i * n + j);
                        }
                    }
                }
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] === 'O' && find(i * n + j) !== find(m * n)) {
                board[i][j] = 'X';
            }
        }
    }
}
