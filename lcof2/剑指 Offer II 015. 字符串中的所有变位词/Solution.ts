function findAnagrams(s: string, p: string): number[] {
    const m = s.length;
    const n = p.length;
    const ans: number[] = [];
    if (m < n) {
        return ans;
    }
    const cnt: number[] = new Array(26).fill(0);
    for (let i = 0; i < n; ++i) {
        --cnt[p[i].charCodeAt(0) - 'a'.charCodeAt(0)];
        ++cnt[s[i].charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    let diff = 0;
    for (const x of cnt) {
        if (x !== 0) {
            ++diff;
        }
    }
    if (diff === 0) {
        ans.push(0);
    }
    for (let i = n; i < m; ++i) {
        const a = s[i - n].charCodeAt(0) - 'a'.charCodeAt(0);
        const b = s[i].charCodeAt(0) - 'a'.charCodeAt(0);
        if (cnt[a] === 0) {
            ++diff;
        }
        if (--cnt[a] === 0) {
            --diff;
        }
        if (cnt[b] === 0) {
            ++diff;
        }
        if (++cnt[b] === 0) {
            --diff;
        }
        if (diff === 0) {
            ans.push(i - n + 1);
        }
    }
    return ans;
}
