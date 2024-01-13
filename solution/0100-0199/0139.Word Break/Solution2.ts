function wordBreak(s: string, wordDict: string[]): boolean {
    const trie = new Trie();
    for (const w of wordDict) {
        trie.insert(w);
    }
    const n = s.length;
    const f: boolean[] = new Array(n + 1).fill(false);
    f[n] = true;
    for (let i = n - 1; i >= 0; --i) {
        let node: Trie = trie;
        for (let j = i; j < n; ++j) {
            const k = s.charCodeAt(j) - 97;
            if (!node.children[k]) {
                break;
            }
            node = node.children[k];
            if (node.isEnd && f[j + 1]) {
                f[i] = true;
                break;
            }
        }
    }
    return f[0];
}

class Trie {
    children: Trie[];
    isEnd: boolean;

    constructor() {
        this.children = new Array(26);
        this.isEnd = false;
    }

    insert(w: string): void {
        let node: Trie = this;
        for (const c of w) {
            const i = c.charCodeAt(0) - 97;
            if (!node.children[i]) {
                node.children[i] = new Trie();
            }
            node = node.children[i];
        }
        node.isEnd = true;
    }
}
