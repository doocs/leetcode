function lexSmallestAfterDeletion(s: string): string {
    const cnt: number[] = new Array(26).fill(0);
    const n = s.length;
    const a = 'a'.charCodeAt(0);
    for (let i = 0; i < n; ++i) {
        ++cnt[s.charCodeAt(i) - a];
    }
    const stk: string[] = [];
    for (let i = 0; i < n; ++i) {
        const c = s[i];
        while (
            stk.length > 0 &&
            stk[stk.length - 1] > c &&
            cnt[stk[stk.length - 1].charCodeAt(0) - a] > 1
        ) {
            --cnt[stk.pop()!.charCodeAt(0) - a];
        }
        stk.push(c);
    }
    while (cnt[stk[stk.length - 1].charCodeAt(0) - a] > 1) {
        --cnt[stk.pop()!.charCodeAt(0) - a];
    }
    return stk.join('');
}
