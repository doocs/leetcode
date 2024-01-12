function countWords(words1: string[], words2: string[]): number {
    const cnt1 = new Map<string, number>();
    const cnt2 = new Map<string, number>();
    for (const w of words1) {
        cnt1.set(w, (cnt1.get(w) ?? 0) + 1);
    }
    for (const w of words2) {
        cnt2.set(w, (cnt2.get(w) ?? 0) + 1);
    }
    let ans = 0;
    for (const [w, v] of cnt1) {
        if (v === 1 && cnt2.get(w) === 1) {
            ++ans;
        }
    }
    return ans;
}
