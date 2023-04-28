function equalFrequency(word: string): boolean {
    const cnt: number[] = new Array(26).fill(0);
    for (const c of word) {
        cnt[c.charCodeAt(0) - 97]++;
    }
    for (let i = 0; i < 26; ++i) {
        if (cnt[i]) {
            cnt[i]--;
            let x = 0;
            let ok = true;
            for (const v of cnt) {
                if (v === 0) {
                    continue;
                }
                if (x && v !== x) {
                    ok = false;
                    break;
                }
                x = v;
            }
            if (ok) {
                return true;
            }
            cnt[i]++;
        }
    }
    return false;
}
