function minimumPushes(word: string): number {
    const cnt: number[] = Array(26).fill(0);
    for (const c of word) {
        ++cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    cnt.sort((a, b) => b - a);
    let ans = 0;
    for (let i = 0; i < 26; ++i) {
        ans += (((i / 8) | 0) + 1) * cnt[i];
    }
    return ans;
}
