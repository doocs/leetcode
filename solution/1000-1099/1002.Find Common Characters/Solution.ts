function commonChars(words: string[]): string[] {
    const cnt = Array(26).fill(20000);
    const aCode = 'a'.charCodeAt(0);
    for (const w of words) {
        const t = Array(26).fill(0);
        for (const c of w) {
            t[c.charCodeAt(0) - aCode]++;
        }
        for (let i = 0; i < 26; i++) {
            cnt[i] = Math.min(cnt[i], t[i]);
        }
    }
    const ans: string[] = [];
    for (let i = 0; i < 26; i++) {
        cnt[i] && ans.push(...String.fromCharCode(i + aCode).repeat(cnt[i]));
    }
    return ans;
}
