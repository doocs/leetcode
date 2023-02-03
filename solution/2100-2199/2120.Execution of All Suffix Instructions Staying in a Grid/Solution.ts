function executeInstructions(
    n: number,
    startPos: number[],
    s: string,
): number[] {
    const m = s.length;
    const ans = new Array(m);
    for (let i = 0; i < m; i++) {
        let [y, x] = startPos;
        let j: number;
        for (j = i; j < m; j++) {
            const c = s[j];
            if (c === 'U') {
                y--;
            } else if (c === 'D') {
                y++;
            } else if (c === 'L') {
                x--;
            } else {
                x++;
            }
            if (y === -1 || y === n || x === -1 || x === n) {
                break;
            }
        }
        ans[i] = j - i;
    }
    return ans;
}
