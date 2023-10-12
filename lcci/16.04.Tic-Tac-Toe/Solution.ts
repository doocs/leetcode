function tictactoe(board: string[]): string {
    const n = board.length;
    const rows = Array(n).fill(0);
    const cols = Array(n).fill(0);
    let [dg, udg] = [0, 0];
    let hasEmptyGrid = false;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            const c = board[i][j];
            if (c === ' ') {
                hasEmptyGrid = true;
                continue;
            }
            const v = c === 'X' ? 1 : -1;
            rows[i] += v;
            cols[j] += v;
            if (i === j) {
                dg += v;
            }
            if (i + j === n - 1) {
                udg += v;
            }
            if (
                Math.abs(rows[i]) === n ||
                Math.abs(cols[j]) === n ||
                Math.abs(dg) === n ||
                Math.abs(udg) === n
            ) {
                return c;
            }
        }
    }
    return hasEmptyGrid ? 'Pending' : 'Draw';
}
