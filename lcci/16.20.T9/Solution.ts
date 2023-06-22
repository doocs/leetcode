function getValidT9Words(num: string, words: string[]): string[] {
    const s = '22233344455566677778889999';
    const d: string[] = Array(26);
    for (let i = 0; i < 26; ++i) {
        d[i] = s[i];
    }
    const ans: string[] = [];
    const n = num.length;
    for (const w of words) {
        let ok = true;
        for (let i = 0; i < n; ++i) {
            if (d[w[i].charCodeAt(0) - 97] !== num[i]) {
                ok = false;
                break;
            }
        }
        if (ok) {
            ans.push(w);
        }
    }
    return ans;
}
