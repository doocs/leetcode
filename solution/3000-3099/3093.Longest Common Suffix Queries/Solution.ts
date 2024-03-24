class Trie {
    private children: Trie[] = new Array<Trie>(26);
    private length: number = Infinity;
    private idx: number = Infinity;

    public insert(w: string, i: number): void {
        let node: Trie = this;
        if (node.length > w.length) {
            node.length = w.length;
            node.idx = i;
        }
        for (let k: number = w.length - 1; k >= 0; --k) {
            let idx: number = w.charCodeAt(k) - 'a'.charCodeAt(0);
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            if (node.length > w.length) {
                node.length = w.length;
                node.idx = i;
            }
        }
    }

    public query(w: string): number {
        let node: Trie = this;
        for (let k: number = w.length - 1; k >= 0; --k) {
            let idx: number = w.charCodeAt(k) - 'a'.charCodeAt(0);
            if (node.children[idx] == null) {
                break;
            }
            node = node.children[idx];
        }
        return node.idx;
    }
}

function stringIndices(wordsContainer: string[], wordsQuery: string[]): number[] {
    const trie: Trie = new Trie();
    for (let i: number = 0; i < wordsContainer.length; ++i) {
        trie.insert(wordsContainer[i], i);
    }
    const n: number = wordsQuery.length;
    const ans: number[] = new Array<number>(n);
    for (let i: number = 0; i < n; ++i) {
        ans[i] = trie.query(wordsQuery[i]);
    }
    return ans;
}
