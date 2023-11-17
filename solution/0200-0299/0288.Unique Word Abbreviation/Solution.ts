class ValidWordAbbr {
    private d: Map<string, Set<string>> = new Map();

    constructor(dictionary: string[]) {
        for (const s of dictionary) {
            const abbr = this.abbr(s);
            if (!this.d.has(abbr)) {
                this.d.set(abbr, new Set());
            }
            this.d.get(abbr)!.add(s);
        }
    }

    isUnique(word: string): boolean {
        const ws = this.d.get(this.abbr(word));
        return ws === undefined || (ws.size === 1 && ws.has(word));
    }

    abbr(s: string): string {
        const n = s.length;
        return n < 3 ? s : s[0] + (n - 2) + s[n - 1];
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * var obj = new ValidWordAbbr(dictionary)
 * var param_1 = obj.isUnique(word)
 */
