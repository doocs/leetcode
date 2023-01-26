function numSub(s: string): number {
    const mod = 10 ** 9 + 7;
    let ans = 0;
    let cnt = 0;
    for (const c of s) {
        cnt = c == '1' ? cnt + 1 : 0;
        ans = (ans + cnt) % mod;
    }
    return ans;
}
