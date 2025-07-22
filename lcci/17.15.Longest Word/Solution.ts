function longestWord(words: string[]): string {
    const s = new Set(words);

    words.sort((a, b) => (a.length === b.length ? a.localeCompare(b) : b.length - a.length));

    const dfs = (w: string): boolean => {
        if (w === '') {
            return true;
        }
        for (let k = 1; k <= w.length; ++k) {
            if (s.has(w.substring(0, k)) && dfs(w.substring(k))) {
                return true;
            }
        }
        return false;
    };

    for (const w of words) {
        s.delete(w);
        if (dfs(w)) {
            return w;
        }
    }

    return '';
}
