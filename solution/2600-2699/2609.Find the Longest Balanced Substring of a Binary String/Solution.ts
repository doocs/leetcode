function findTheLongestBalancedSubstring(s: string): number {
    const n = s.length;
    let ans = 0;
    const check = (i: number, j: number): boolean => {
        let cnt = 0;
        for (let k = i; k <= j; ++k) {
            if (s[k] === '1') {
                ++cnt;
            } else if (cnt > 0) {
                return false;
            }
        }
        return cnt * 2 === j - i + 1;
    };
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; j += 2) {
            if (check(i, j)) {
                ans = Math.max(ans, j - i + 1);
            }
        }
    }
    return ans;
}
