/**
 Do not return anything, modify board in-place instead.
 */
function solve(board: string[][]): void {
    function dfs(i, j) {
        board[i][j] = '.';
        const dirs = [-1, 0, 1, 0, -1];
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
                dfs(x, y);
            }
        }
    }
    const m = board.length;
    const n = board[0].length;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (
                (i == 0 || i == m - 1 || j == 0 || j == n - 1) &&
                board[i][j] == 'O'
            ) {
                dfs(i, j);
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] == '.') {
                board[i][j] = 'O';
            } else if (board[i][j] == 'O') {
                board[i][j] = 'X';
            }
        }
    }
}
