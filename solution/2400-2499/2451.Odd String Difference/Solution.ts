function oddString(words: string[]): string {
    const n = words[0].length;
    const map = new Map<string, [boolean, number]>();
    words.forEach((word, i) => {
        const diff: number[] = [];
        for (let j = 1; j < n; j++) {
            diff.push(word[j].charCodeAt(0) - word[j - 1].charCodeAt(0));
        }
        const k = diff.join();
        map.set(k, [!map.has(k), i]);
    });
    for (const [isOnly, i] of map.values()) {
        if (isOnly) {
            return words[i];
        }
    }
    return '';
}
