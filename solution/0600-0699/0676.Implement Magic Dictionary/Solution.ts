class Trie {
    private children: Trie[] = Array(26).fill(null);
    private isEnd: boolean = false;

    constructor() {}

    insert(w: string): void {
        let node: Trie = this;
        for (const c of w) {
            const i: number = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node.children[i]) {
                node.children[i] = new Trie();
            }
            node = node.children[i];
        }
        node.isEnd = true;
    }

    search(w: string): boolean {
        const dfs = (i: number, node: Trie, diff: number): boolean => {
            if (i >= w.length) {
                return diff === 1 && node.isEnd;
            }
            const j: number = w.charCodeAt(i) - 'a'.charCodeAt(0);
            if (node.children[j] && dfs(i + 1, node.children[j], diff)) {
                return true;
            }
            if (diff === 0) {
                for (let k = 0; k < 26; k++) {
                    if (k !== j && node.children[k] && dfs(i + 1, node.children[k], 1)) {
                        return true;
                    }
                }
            }
            return false;
        };
        return dfs(0, this, 0);
    }
}

class MagicDictionary {
    private trie: Trie;

    constructor() {
        this.trie = new Trie();
    }

    buildDict(dictionary: string[]): void {
        for (const w of dictionary) {
            this.trie.insert(w);
        }
    }

    search(searchWord: string): boolean {
        return this.trie.search(searchWord);
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * var obj = new MagicDictionary()
 * obj.buildDict(dictionary)
 * var param_2 = obj.search(searchWord)
 */
