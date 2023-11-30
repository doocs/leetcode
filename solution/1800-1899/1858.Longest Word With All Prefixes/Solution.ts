class Trie {
    private children: (Trie | null)[] = Array(26).fill(null);
    private isEnd: boolean = false;

    insert(w: string): void {
        let node: Trie = this;
        for (const c of w) {
            const idx: number = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node.children[idx]) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx] as Trie;
        }
        node.isEnd = true;
    }

    search(w: string): boolean {
        let node: Trie = this;
        for (const c of w) {
            const idx: number = c.charCodeAt(0) - 'a'.charCodeAt(0);
            node = node.children[idx] as Trie;
            if (!node.isEnd) {
                return false;
            }
        }
        return true;
    }
}

function longestWord(words: string[]): string {
    const trie: Trie = new Trie();
    for (const w of words) {
        trie.insert(w);
    }
    let ans: string = '';
    for (const w of words) {
        if ((w.length > ans.length || (w.length === ans.length && w < ans)) && trie.search(w)) {
            ans = w;
        }
    }
    return ans;
}
