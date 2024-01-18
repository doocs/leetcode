class Trie {
    children: Array<any>;
    cnt: number;

    constructor() {
        this.children = Array(26);
        this.cnt = 0;
    }

    insert(w: string): void {
        let node = this;
        for (const c of w) {
            const idx = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node.children[idx]) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            node.cnt++;
        }
    }

    search(w: string): number {
        let node = this;
        let ans = 0;
        for (const c of w) {
            const idx = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node.children[idx]) {
                return ans;
            }
            node = node.children[idx];
            ans += node.cnt;
        }
        return ans;
    }
}

function sumPrefixScores(words: string[]): number[] {
    const trie = new Trie();
    for (const w of words) {
        trie.insert(w);
    }
    let ans = [];
    for (const w of words) {
        ans.push(trie.search(w));
    }
    return ans;
}
