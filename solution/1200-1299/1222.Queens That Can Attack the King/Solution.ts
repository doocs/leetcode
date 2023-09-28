function queensAttacktheKing(queens: number[][], king: number[]): number[][] {
    const n = 8;
    const s: boolean[][] = Array.from({ length: n }, () => Array.from({ length: n }, () => false));
    queens.forEach(([x, y]) => (s[x][y] = true));
    const ans: number[][] = [];
    for (let a = -1; a <= 1; ++a) {
        for (let b = -1; b <= 1; ++b) {
            if (a || b) {
                let [x, y] = [king[0] + a, king[1] + b];
                while (x >= 0 && x < n && y >= 0 && y < n) {
                    if (s[x][y]) {
                        ans.push([x, y]);
                        break;
                    }
                    x += a;
                    y += b;
                }
            }
        }
    }
    return ans;
}
