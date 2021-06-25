/**
 Do not return anything, modify board in-place instead.
 */
 function solve(board: string[][]): void {
    let m = board.length, n = board[0].length;
    if (m < 3 || n < 3) return;
    let visited = Array.from({ length: m }, v => new Array(n).fill(false));
    // 第一行，最后一行， 第一列， 最后一列
    for (let i of [0, m-1]) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] == 'X') {
                visited[i][j] = true;
            } else {
                dfs(board, i, j, visited, true);
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j of [0, n - 1]) {
            if (board[i][j] == 'X') {
                visited[i][j] = true;
            } else {
                dfs(board, i, j, visited, true);
            }
        }
    }
    for (let i = 1; i < m - 1; ++i) {
        for (let j = 1; j < n - 1; ++j) {
            !visited[i][j] && dfs(board, i, j, visited);
        }
    }
};

function dfs(board: string[][], i: number, j: number, visited: boolean[][], edge = false): void {
    let m = board.length, n = board[0].length;
    if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || visited[i][j]) {
        return;
    }

    visited[i][j] = true;
    if (board[i][j] == 'X') {
        return;
    }
    if (!edge) {
        board[i][j] = 'X';
    }
    for (let [dx, dy] of [[0, 1], [0, -1], [1, 0], [-1, 0]]) {
        let x = i + dx, y = j + dy;
        dfs(board, x, y, visited, edge);
    }
}