function findWords(words: string[]): string[] {
    const s = '12210111011122000010020202';
    const ans: string[] = [];
    for (const w of words) {
        const t = w.toLowerCase();
        const x = s[t.charCodeAt(0) - 'a'.charCodeAt(0)];
        let ok = true;
        for (const c of t) {
            if (s[c.charCodeAt(0) - 'a'.charCodeAt(0)] !== x) {
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
