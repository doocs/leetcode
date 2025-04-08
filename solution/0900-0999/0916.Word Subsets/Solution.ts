function wordSubsets(words1: string[], words2: string[]): string[] {
    const cnt: number[] = Array(26).fill(0);
    for (const b of words2) {
        const t: number[] = Array(26).fill(0);
        for (const c of b) {
            t[c.charCodeAt(0) - 97]++;
        }
        for (let i = 0; i < 26; i++) {
            cnt[i] = Math.max(cnt[i], t[i]);
        }
    }

    const ans: string[] = [];
    for (const a of words1) {
        const t: number[] = Array(26).fill(0);
        for (const c of a) {
            t[c.charCodeAt(0) - 97]++;
        }

        let ok = true;
        for (let i = 0; i < 26; i++) {
            if (cnt[i] > t[i]) {
                ok = false;
                break;
            }
        }

        if (ok) {
            ans.push(a);
        }
    }

    return ans;
}
