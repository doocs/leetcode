function checkMove(board: string[][], rMove: number, cMove: number, color: string): boolean {
    for (let a = -1; a <= 1; ++a) {
        for (let b = -1; b <= 1; ++b) {
            if (a === 0 && b === 0) {
                continue;
            }
            let [i, j] = [rMove, cMove];
            let cnt = 0;
            while (0 <= i + a && i + a < 8 && 0 <= j + b && j + b < 8) {
                i += a;
                j += b;
                if (++cnt > 1 && board[i][j] === color) {
                    return true;
                }
                if (board[i][j] === color || board[i][j] === '.') {
                    break;
                }
            }
        }
    }
    return false;
}
