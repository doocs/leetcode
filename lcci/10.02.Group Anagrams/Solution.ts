function groupAnagrams(strs: string[]): string[][] {
    const map = new Map<string, string[]>();
    for (const s of strs) {
        const k = s.split('').sort().join();
        map.set(k, (map.get(k) || []).concat([s]));
    }
    return [...map.values()];
}
