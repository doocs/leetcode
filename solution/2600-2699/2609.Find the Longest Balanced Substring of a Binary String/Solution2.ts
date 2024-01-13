function findTheLongestBalancedSubstring(s: string): number {
    let zero = 0;
    let one = 0;
    let ans = 0;
    for (const c of s) {
        if (c === '0') {
            if (one > 0) {
                zero = 0;
                one = 0;
            }
            ++zero;
        } else {
            ans = Math.max(ans, 2 * Math.min(zero, ++one));
        }
    }
    return ans;
}
