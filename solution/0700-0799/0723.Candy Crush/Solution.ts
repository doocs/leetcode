function candyCrush(board: number[][]): number[][] {
    const m = board.length;
    const n = board[0].length;
    let run = true;
    while (run) {
        run = false;
        for (let i = 0; i < m; i++) {
            for (let j = 2; j < n; j++) {
                if (
                    board[i][j] !== 0 &&
                    Math.abs(board[i][j]) === Math.abs(board[i][j - 1]) &&
                    Math.abs(board[i][j]) === Math.abs(board[i][j - 2])
                ) {
                    run = true;
                    const val = Math.abs(board[i][j]);
                    board[i][j] = board[i][j - 1] = board[i][j - 2] = -val;
                }
            }
        }
        for (let j = 0; j < n; j++) {
            for (let i = 2; i < m; i++) {
                if (
                    board[i][j] !== 0 &&
                    Math.abs(board[i][j]) === Math.abs(board[i - 1][j]) &&
                    Math.abs(board[i][j]) === Math.abs(board[i - 2][j])
                ) {
                    run = true;
                    const val = Math.abs(board[i][j]);
                    board[i][j] = board[i - 1][j] = board[i - 2][j] = -val;
                }
            }
        }
        if (run) {
            for (let j = 0; j < n; j++) {
                let k = m - 1;
                for (let i = m - 1; i >= 0; i--) {
                    if (board[i][j] > 0) {
                        board[k][j] = board[i][j];
                        k--;
                    }
                }
                while (k >= 0) {
                    board[k][j] = 0;
                    k--;
                }
            }
        }
    }
    return board;
}
