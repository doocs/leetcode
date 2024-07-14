function findPattern(board: number[][], pattern: string[]): number[] {
    const m: number = board.length;
    const n: number = board[0].length;
    const r: number = pattern.length;
    const c: number = pattern[0].length;

    const check = (i: number, j: number): boolean => {
        const d1: number[] = Array(26).fill(-1);
        const d2: number[] = Array(10).fill(-1);

        for (let a = 0; a < r; ++a) {
            for (let b = 0; b < c; ++b) {
                const x: number = i + a;
                const y: number = j + b;

                if (!isNaN(Number(pattern[a][b]))) {
                    const v: number = Number(pattern[a][b]);
                    if (v !== board[x][y]) {
                        return false;
                    }
                } else {
                    const v: number = pattern[a].charCodeAt(b) - 'a'.charCodeAt(0);
                    if (d1[v] !== -1 && d1[v] !== board[x][y]) {
                        return false;
                    }
                    if (d2[board[x][y]] !== -1 && d2[board[x][y]] !== v) {
                        return false;
                    }
                    d1[v] = board[x][y];
                    d2[board[x][y]] = v;
                }
            }
        }
        return true;
    };

    for (let i = 0; i < m - r + 1; ++i) {
        for (let j = 0; j < n - c + 1; ++j) {
            if (check(i, j)) {
                return [i, j];
            }
        }
    }
    return [-1, -1];
}
