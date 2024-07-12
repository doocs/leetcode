function snakesAndLadders(board: number[][]): number {
    const n = board.length;
    const q: number[] = [1];
    const m = n * n;
    const vis: boolean[] = Array(m + 1).fill(false);
    vis[1] = true;

    for (let ans = 0; q.length > 0; ans++) {
        const nq: number[] = [];
        for (const x of q) {
            if (x === m) {
                return ans;
            }
            for (let y = x + 1; y <= Math.min(x + 6, m); y++) {
                let i = Math.floor((y - 1) / n);
                let j = (y - 1) % n;
                if (i % 2 === 1) {
                    j = n - j - 1;
                }
                i = n - i - 1;
                const z = board[i][j] === -1 ? y : board[i][j];
                if (!vis[z]) {
                    vis[z] = true;
                    nq.push(z);
                }
            }
        }
        q.length = 0;
        for (const x of nq) {
            q.push(x);
        }
    }
    return -1;
}
