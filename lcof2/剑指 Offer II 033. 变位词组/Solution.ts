function groupAnagrams(strs: string[]): string[][] {
    const map = new Map<string, string[]>();
    for (const str of strs) {
        const k = str.split('').sort().join('');
        map.set(k, (map.get(k) ?? []).concat([str]));
    }
    return [...map.values()];
}
