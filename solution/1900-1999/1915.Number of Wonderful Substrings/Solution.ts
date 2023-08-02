function wonderfulSubstrings(word: string): number {
    const cnt: number[] = new Array(1 << 10).fill(0);
    cnt[0] = 1;
    let ans = 0;
    let st = 0;
    for (const c of word) {
        st ^= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
        ans += cnt[st];
        for (let i = 0; i < 10; ++i) {
            ans += cnt[st ^ (1 << i)];
        }
        cnt[st]++;
    }
    return ans;
}
