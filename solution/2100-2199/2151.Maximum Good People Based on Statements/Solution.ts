function maximumGood(statements: number[][]): number {
    const n = statements.length;
    function check(mask) {
        let cnt = 0;
        for (let i = 0; i < n; ++i) {
            if ((mask >> i) & 1) {
                for (let j = 0; j < n; ++j) {
                    const v = statements[i][j];
                    if (v < 2 && ((mask >> j) & 1) != v) {
                        return 0;
                    }
                }
                ++cnt;
            }
        }
        return cnt;
    }
    let ans = 0;
    for (let mask = 1; mask < 1 << n; ++mask) {
        ans = Math.max(ans, check(mask));
    }
    return ans;
}
