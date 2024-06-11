function countBattleships(board: string[][]): number {
    const m = board.length;
    const n = board[0].length;
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] === '.') {
                continue;
            }
            if (i && board[i - 1][j] === 'X') {
                continue;
            }
            if (j && board[i][j - 1] === 'X') {
                continue;
            }
            ++ans;
        }
    }
    return ans;
}
