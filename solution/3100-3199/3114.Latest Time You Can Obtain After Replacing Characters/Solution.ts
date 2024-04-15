function findLatestTime(s: string): string {
    for (let h = 11; ; h--) {
        for (let m = 59; m >= 0; m--) {
            const t: string = `${h.toString().padStart(2, '0')}:${m.toString().padStart(2, '0')}`;
            let ok: boolean = true;
            for (let i = 0; i < s.length; i++) {
                if (s[i] !== '?' && s[i] !== t[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return t;
            }
        }
    }
}
