function sumPrefixScores(words: string[]): number[] {
    const map = new Map();

    for (const word of words) {
        const n = word.length;
        for (let i = 1; i <= n; i++) {
            const s = word.slice(0, i);
            map.set(s, (map.get(s) ?? 0) + 1);
        }
    }

    return words.map(word => {
        const n = word.length;
        let count = 0;
        for (let i = 1; i <= n; i++) {
            const s = word.slice(0, i);
            count += map.get(s);
        }
        return count;
    });
}
