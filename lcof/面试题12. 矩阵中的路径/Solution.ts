function exist(board: string[][], word: string): boolean {
    const m = board.length;
    const n = board[0].length;
    const dfs = (i: number, j: number, k: number) => {
        if ((board[i] ?? [])[j] !== word[k]) {
            return false;
        }
        if (++k === word.length) {
            return true;
        }
        const temp = board[i][j];
        board[i][j] = ' ';
        if (
            dfs(i + 1, j, k) ||
            dfs(i, j + 1, k) ||
            dfs(i - 1, j, k) ||
            dfs(i, j - 1, k)
        ) {
            return true;
        }
        board[i][j] = temp;
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
