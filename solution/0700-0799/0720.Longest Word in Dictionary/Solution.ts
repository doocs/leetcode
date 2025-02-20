class Trie {
    children: (Trie | null)[] = new Array(26).fill(null);
    isEnd: boolean = false;

    insert(w: string): void {
        let node: Trie = this;
        for (let i = 0; i < w.length; i++) {
            const idx: number = w.charCodeAt(i) - 'a'.charCodeAt(0);
            if (node.children[idx] === null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx]!;
        }
        node.isEnd = true;
    }

    search(w: string): boolean {
        let node: Trie = this;
        for (let i = 0; i < w.length; i++) {
            const idx: number = w.charCodeAt(i) - 'a'.charCodeAt(0);
            if (node.children[idx] === null || !node.children[idx]!.isEnd) {
                return false;
            }
            node = node.children[idx]!;
        }
        return true;
    }
}

function longestWord(words: string[]): string {
    const trie = new Trie();
    for (const w of words) {
        trie.insert(w);
    }

    let ans = '';
    for (const w of words) {
        if (trie.search(w) && (ans.length < w.length || (ans.length === w.length && w < ans))) {
            ans = w;
        }
    }
    return ans;
}
