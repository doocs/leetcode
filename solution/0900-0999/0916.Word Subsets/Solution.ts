function wordSubsets(words1: string[], words2: string[]): string[] {
    const cnt2 = new Map<string, number>();

    for (const b of words2) {
        const cnt = new Map<string, number>();
        for (const c of b) {
            cnt.set(c, (cnt.get(c) ?? 0) + 1);
        }

        for (const [k, v] of cnt) {
            cnt2.set(k, Math.max(cnt2.get(k) ?? 0, v));
        }
    }

    return words1.filter(a => {
        const cnt1 = new Map<string, number>();
        for (const c of a) {
            cnt1.set(c, (cnt1.get(c) ?? 0) + 1);
        }

        for (const [k, v] of cnt2) {
            if ((cnt1.get(k) ?? 0) < v) return false;
        }

        return true;
    });
}
