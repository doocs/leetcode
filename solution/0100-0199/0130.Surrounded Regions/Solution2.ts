/**
 Do not return anything, modify board in-place instead.
 */
function solve(board: string[][]): void {
    const m = board.length;
    const n = board[0].length;
    let p = new Array(m * n + 1);
    for (let i = 0; i < p.length; ++i) {
        p[i] = i;
    }
    function find(x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
    const dirs = [-1, 0, 1, 0, -1];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] == 'O') {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    p[find(i * n + j)] = find(m * n);
                } else {
                    for (let k = 0; k < 4; ++k) {
                        const x = i + dirs[k];
                        const y = j + dirs[k + 1];
                        if (board[x][y] == 'O') {
                            p[find(x * n + y)] = find(i * n + j);
                        }
                    }
                }
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] == 'O' && find(i * n + j) != find(m * n)) {
                board[i][j] = 'X';
            }
        }
    }
}
