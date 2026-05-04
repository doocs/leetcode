function groupAnagrams(strs: string[]): string[][] {
    const d = new Map<string, string[]>();
    for (const s of strs) {
        const cnt = new Array(26).fill(0);
        for (const c of s) {
            cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)]++;
        }
        const key = cnt.join(',');
        if (!d.has(key)) {
            d.set(key, []);
        }
        d.get(key)!.push(s);
    }
    return Array.from(d.values());
}
