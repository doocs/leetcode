function numRookCaptures(board: string[][]): number {
    const dirs = [-1, 0, 1, 0, -1];
    const n = board.length;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (board[i][j] === 'R') {
                let ans = 0;
                for (let k = 0; k < 4; k++) {
                    let [x, y] = [i + dirs[k], j + dirs[k + 1]];
                    while (x >= 0 && x < n && y >= 0 && y < n && board[x][y] !== 'B') {
                        if (board[x][y] === 'p') {
                            ans++;
                            break;
                        }
                        x += dirs[k];
                        y += dirs[k + 1];
                    }
                }
                return ans;
            }
        }
    }
    return 0;
}
