function findSubstring(s: string, words: string[]): number[] {
    const cnt: Map<string, number> = new Map();
    for (const w of words) {
        cnt.set(w, (cnt.get(w) || 0) + 1);
    }
    const ans: number[] = [];
    const [m, n, k] = [s.length, words.length, words[0].length];
    for (let i = 0; i < k; i++) {
        let [l, r] = [i, i];
        const cnt1: Map<string, number> = new Map();
        while (r + k <= m) {
            const t = s.substring(r, r + k);
            r += k;
            if (!cnt.has(t)) {
                cnt1.clear();
                l = r;
                continue;
            }
            cnt1.set(t, (cnt1.get(t) || 0) + 1);
            while (cnt1.get(t)! > cnt.get(t)!) {
                const w = s.substring(l, l + k);
                cnt1.set(w, cnt1.get(w)! - 1);
                if (cnt1.get(w) === 0) {
                    cnt1.delete(w);
                }
                l += k;
            }
            if (r - l === n * k) {
                ans.push(l);
            }
        }
    }
    return ans;
}
