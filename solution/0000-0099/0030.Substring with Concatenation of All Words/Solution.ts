function findSubstring(s: string, words: string[]): number[] {
    const cnt: Map<string, number> = new Map();
    for (const w of words) {
        cnt.set(w, (cnt.get(w) || 0) + 1);
    }
    const m = s.length;
    const n = words.length;
    const k = words[0].length;
    const ans: number[] = [];
    for (let i = 0; i < k; ++i) {
        const cnt1: Map<string, number> = new Map();
        let l = i;
        let r = i;
        let t = 0;
        while (r + k <= m) {
            const w = s.slice(r, r + k);
            r += k;
            if (!cnt.has(w)) {
                cnt1.clear();
                l = r;
                t = 0;
                continue;
            }
            cnt1.set(w, (cnt1.get(w) || 0) + 1);
            ++t;
            while (cnt1.get(w)! - cnt.get(w)! > 0) {
                const remove = s.slice(l, l + k);
                cnt1.set(remove, cnt1.get(remove)! - 1);
                l += k;
                --t;
            }
            if (t === n) {
                ans.push(l);
            }
        }
    }
    return ans;
}
