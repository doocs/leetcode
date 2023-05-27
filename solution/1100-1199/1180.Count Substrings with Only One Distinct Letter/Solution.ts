function countLetters(s: string): number {
    let ans = 0;
    const n = s.length;
    for (let i = 0; i < n; ) {
        let j = i;
        let cnt = 0;
        while (j < n && s[j] === s[i]) {
            ++j;
            ans += ++cnt;
        }
        i = j;
    }
    return ans;
}
