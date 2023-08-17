function maxPower(s: string): number {
    let ans = 1;
    let t = 1;
    for (let i = 1; i < s.length; ++i) {
        if (s[i] === s[i - 1]) {
            ans = Math.max(ans, ++t);
        } else {
            t = 1;
        }
    }
    return ans;
}
