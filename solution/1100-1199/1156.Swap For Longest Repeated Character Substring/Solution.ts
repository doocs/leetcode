function maxRepOpt1(text: string): number {
    const idx = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    const cnt: number[] = new Array(26).fill(0);
    for (const c of text) {
        cnt[idx(c)]++;
    }
    let ans = 0;
    let i = 0;
    const n = text.length;
    while (i < n) {
        let j = i;
        while (j < n && text[j] === text[i]) {
            ++j;
        }
        const l = j - i;
        let k = j + 1;
        while (k < n && text[k] === text[i]) {
            ++k;
        }
        const r = k - j - 1;
        ans = Math.max(ans, Math.min(cnt[idx(text[i])], l + r + 1));
        i = j;
    }
    return ans;
}
