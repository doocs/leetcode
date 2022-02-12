function exist(board: string[][], word: string): boolean {
    const m = board.length;
    const n = board[0].length;
    const dfs = (y: number, x: number, i: number) => {
        if (i === word.length) {
            return true;
        }
        if ((board[y] || [])[x] !== word[i]) {
            return false;
        }
        const temp = board[y][x];
        board[y][x] = '';
        if (
            dfs(y + 1, x, i + 1) ||
            dfs(y, x + 1, i + 1) ||
            dfs(y - 1, x, i + 1) ||
            dfs(y, x - 1, i + 1)
        ) {
            return true;
        }
        board[y][x] = temp;
        return false;
    };
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (dfs(i, j, 0)) {
                return true;
            }
        }
    }
    return false;
}
