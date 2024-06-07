class TicTacToe {
    private n: number;
    private cnt: number[][];

    constructor(n: number) {
        this.n = n;
        this.cnt = [Array((n << 1) + 2).fill(0), Array((n << 1) + 2).fill(0)];
    }

    move(row: number, col: number, player: number): number {
        const cur = this.cnt[player - 1];
        cur[row]++;
        cur[this.n + col]++;
        if (row === col) {
            cur[this.n << 1]++;
        }
        if (row + col === this.n - 1) {
            cur[(this.n << 1) | 1]++;
        }
        if (
            cur[row] === this.n ||
            cur[this.n + col] === this.n ||
            cur[this.n << 1] === this.n ||
            cur[(this.n << 1) | 1] === this.n
        ) {
            return player;
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * var obj = new TicTacToe(n)
 * var param_1 = obj.move(row,col,player)
 */
