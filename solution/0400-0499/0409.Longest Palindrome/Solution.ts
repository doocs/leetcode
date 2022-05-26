function longestPalindrome(s: string): number {
    let n = s.length;
    let ans = 0;
    let record = new Array(128).fill(0);
    for (let i = 0; i < n; i++) {
        record[s.charCodeAt(i)]++;
    }
    for (let i = 65; i < 128; i++) {
        let count = record[i];
        ans += count % 2 == 0 ? count : count - 1;
    }
    return ans < s.length ? ans + 1 : ans;
}
