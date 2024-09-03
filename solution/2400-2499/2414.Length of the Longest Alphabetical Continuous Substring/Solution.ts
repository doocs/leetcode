function longestContinuousSubstring(s: string): number {
    let [ans, cnt] = [1, 1];
    for (let i = 1; i < s.length; ++i) {
        if (s.charCodeAt(i) - s.charCodeAt(i - 1) === 1) {
            ans = Math.max(ans, ++cnt);
        } else {
            cnt = 1;
        }
    }
    return ans;
}
