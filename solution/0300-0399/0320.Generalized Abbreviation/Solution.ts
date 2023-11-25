function generateAbbreviations(word: string): string[] {
    const n = word.length;
    const ans: string[] = [];
    for (let i = 0; i < 1 << n; ++i) {
        const s: string[] = [];
        let cnt = 0;
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                ++cnt;
            } else {
                if (cnt > 0) {
                    s.push(cnt.toString());
                    cnt = 0;
                }
                s.push(word[j]);
            }
        }
        if (cnt > 0) {
            s.push(cnt.toString());
        }
        ans.push(s.join(''));
    }
    return ans;
}
