function mostCommonWord(paragraph: string, banned: string[]): string {
    const s = paragraph.toLocaleLowerCase();
    const map = new Map<string, number>();
    const set = new Set<string>(banned);
    for (const word of s.split(/[^A-z]/)) {
        if (word === '' || set.has(word)) {
            continue;
        }
        map.set(word, (map.get(word) ?? 0) + 1);
    }
    return [...map.entries()].reduce(
        (r, v) => (v[1] > r[1] ? v : r),
        ['', 0],
    )[0];
}
