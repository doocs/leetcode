function filterCharacters(s: string, k: number): string {
    const cnt: Record<string, number> = {};
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    const ans: string[] = [];
    for (const c of s) {
        if (cnt[c] < k) {
            ans.push(c);
        }
    }
    return ans.join('');
}
