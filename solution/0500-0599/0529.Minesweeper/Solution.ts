function updateBoard(board: string[][], click: number[]): string[][] {
    const m = board.length;
    const n = board[0].length;
    const [i, j] = click;

    const dfs = (i: number, j: number) => {
        let cnt = 0;
        for (let x = i - 1; x <= i + 1; ++x) {
            for (let y = j - 1; y <= j + 1; ++y) {
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] === 'M') {
                    ++cnt;
                }
            }
        }
        if (cnt > 0) {
            board[i][j] = cnt.toString();
            return;
        }
        board[i][j] = 'B';
        for (let x = i - 1; x <= i + 1; ++x) {
            for (let y = j - 1; y <= j + 1; ++y) {
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] === 'E') {
                    dfs(x, y);
                }
            }
        }
    };

    if (board[i][j] === 'M') {
        board[i][j] = 'X';
    } else {
        dfs(i, j);
    }
    return board;
}
