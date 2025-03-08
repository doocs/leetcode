class Encrypter {
    private mp: Map<string, string> = new Map();
    private cnt: Map<string, number> = new Map();

    constructor(keys: string[], values: string[], dictionary: string[]) {
        for (let i = 0; i < keys.length; i++) {
            this.mp.set(keys[i], values[i]);
        }
        for (const w of dictionary) {
            const encrypted = this.encrypt(w);
            if (encrypted !== '') {
                this.cnt.set(encrypted, (this.cnt.get(encrypted) || 0) + 1);
            }
        }
    }

    encrypt(word: string): string {
        let res = '';
        for (const c of word) {
            if (!this.mp.has(c)) {
                return '';
            }
            res += this.mp.get(c);
        }
        return res;
    }

    decrypt(word: string): number {
        return this.cnt.get(word) || 0;
    }
}

/**
 * Your Encrypter object will be instantiated and called as such:
 * const obj = new Encrypter(keys, values, dictionary);
 * const param_1 = obj.encrypt(word1);
 * const param_2 = obj.decrypt(word2);
 */
