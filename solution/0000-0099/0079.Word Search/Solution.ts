function exist(board: string[][], word: string): boolean {
    let m = board.length,
        n = board[0].length;
    let visited = Array.from({ length: m }, v => new Array(n).fill(false));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (dfs(board, word, i, j, 0, visited)) {
                return true;
            }
        }
    }
    return false;
}

function dfs(
    board: string[][],
    word: string,
    i: number,
    j: number,
    depth: number,
    visited: boolean[][],
): boolean {
    let m = board.length,
        n = board[0].length;
    if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || visited[i][j]) {
        return false;
    }
    if (board[i][j] != word.charAt(depth)) {
        return false;
    }

    if (depth == word.length - 1) {
        return true;
    }

    visited[i][j] = true;
    ++depth;
    let res = false;
    for (let [dx, dy] of [
        [0, 1],
        [0, -1],
        [1, 0],
        [-1, 0],
    ]) {
        let x = i + dx,
            y = j + dy;
        res = res || dfs(board, word, x, y, depth, visited);
    }
    visited[i][j] = false;
    return res;
}
