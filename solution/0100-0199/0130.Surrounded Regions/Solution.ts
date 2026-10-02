function solve(board: string[][]): void {
    const m = board.length;
    const n = board[0].length;
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const stack: [number, number][] = [];
    const mark = (i: number, j: number): void => {
        if (i >= 0 && i < m && j >= 0 && j < n && board[i][j] === 'O') {
            board[i][j] = '.';
            stack.push([i, j]);
        }
    };
    for (let i = 0; i < m; ++i) {
        mark(i, 0);
        mark(i, n - 1);
    }
    for (let j = 0; j < n; ++j) {
        mark(0, j);
        mark(m - 1, j);
    }
    while (stack.length > 0) {
        const [i, j] = stack.pop()!;
        for (let k = 0; k < 4; ++k) {
            mark(i + dirs[k], j + dirs[k + 1]);
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] === '.') {
                board[i][j] = 'O';
            } else if (board[i][j] === 'O') {
                board[i][j] = 'X';
            }
        }
    }
}
