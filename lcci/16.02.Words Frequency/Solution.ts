class WordsFrequency {
    private cnt: Map<string, number>;

    constructor(book: string[]) {
        const cnt = new Map<string, number>();
        for (const word of book) {
            cnt.set(word, (cnt.get(word) ?? 0) + 1);
        }
        this.cnt = cnt;
    }

    get(word: string): number {
        return this.cnt.get(word) ?? 0;
    }
}

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * var obj = new WordsFrequency(book)
 * var param_1 = obj.get(word)
 */
