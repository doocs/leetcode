function maxNumOfSubstrings(s: string): string[] {
    const n = s.length;
    const idx = (c: string) => c.charCodeAt(0) - 97;
    const first = Array(26).fill(-1);
    const last = Array(26).fill(0);
    for (let i = 0; i < n; ++i) {
        const x = idx(s[i]);
        if (first[x] === -1) {
            first[x] = i;
        }
        last[x] = i;
    }
    const segs: number[][] = [];
    for (let x = 0; x < 26; ++x) {
        if (first[x] === -1) {
            continue;
        }
        let l = first[x],
            r = last[x];
        let i = l;
        for (; i <= r; ++i) {
            const y = idx(s[i]);
            if (first[y] < l) {
                break;
            }
            r = Math.max(r, last[y]);
        }
        if (i > r) {
            segs.push([l, r]);
        }
    }
    segs.sort((a, b) => a[1] - b[1]);
    const ans: string[] = [];
    let end = -1;
    for (const [l, r] of segs) {
        if (l > end) {
            ans.push(s.slice(l, r + 1));
            end = r;
        }
    }
    return ans;
}
