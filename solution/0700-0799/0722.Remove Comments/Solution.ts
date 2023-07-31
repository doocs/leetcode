function removeComments(source: string[]): string[] {
    const ans: string[] = [];
    const t: string[] = [];
    let blockComment = false;
    for (const s of source) {
        const m = s.length;
        for (let i = 0; i < m; ++i) {
            if (blockComment) {
                if (i + 1 < m && s.slice(i, i + 2) === '*/') {
                    blockComment = false;
                    ++i;
                }
            } else {
                if (i + 1 < m && s.slice(i, i + 2) === '/*') {
                    blockComment = true;
                    ++i;
                } else if (i + 1 < m && s.slice(i, i + 2) === '//') {
                    break;
                } else {
                    t.push(s[i]);
                }
            }
        }
        if (!blockComment && t.length) {
            ans.push(t.join(''));
            t.length = 0;
        }
    }
    return ans;
}
