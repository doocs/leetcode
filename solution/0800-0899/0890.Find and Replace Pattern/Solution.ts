function findAndReplacePattern(words: string[], pattern: string): string[] {
    return words.filter(word => {
        const map1 = new Map<string, number>();
        const map2 = new Map<string, number>();
        for (let i = 0; i < word.length; i++) {
            if (map1.get(word[i]) !== map2.get(pattern[i])) {
                return false;
            }
            map1.set(word[i], i);
            map2.set(pattern[i], i);
        }
        return true;
    });
}
