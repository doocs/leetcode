class WordsFrequency {
    private map: Map<string, number>;

    constructor(book: string[]) {
        const map = new Map<string, number>();
        for (const word of book) {
            map.set(word, (map.get(word) ?? 0) + 1);
        }
        this.map = map;
    }

    get(word: string): number {
        return this.map.get(word) ?? 0;
    }
}

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * var obj = new WordsFrequency(book)
 * var param_1 = obj.get(word)
 */
