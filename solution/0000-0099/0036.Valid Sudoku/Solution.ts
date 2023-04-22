function isValidSudoku(board: string[][]): boolean {
    const row: boolean[][] = Array.from({ length: 9 }, () =>
        Array.from({ length: 9 }, () => false),
    );
    const col: boolean[][] = Array.from({ length: 9 }, () =>
        Array.from({ length: 9 }, () => false),
    );
    const sub: boolean[][] = Array.from({ length: 9 }, () =>
        Array.from({ length: 9 }, () => false),
    );
    for (let i = 0; i < 9; ++i) {
        for (let j = 0; j < 9; ++j) {
            const num = board[i][j].charCodeAt(0) - '1'.charCodeAt(0);
            if (num < 0 || num > 8) {
                continue;
            }
            const k = Math.floor(i / 3) * 3 + Math.floor(j / 3);
            if (row[i][num] || col[j][num] || sub[k][num]) {
                return false;
            }
            row[i][num] = true;
            col[j][num] = true;
            sub[k][num] = true;
        }
    }
    return true;
}
