class Trie {
    children: Array<Trie | null>;
    isEnd: boolean;

    constructor() {
        this.children = new Array(26).fill(null);
        this.isEnd = false;
    }

    insert(w: string): void {
        let node: Trie = this;
        for (const c of w) {
            const idx = c.charCodeAt(0) - 97;
            if (!node.children[idx]) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx]!;
        }
        node.isEnd = true;
    }

    search(w: string): string {
        let node: Trie = this;
        for (let i = 0; i < w.length; i++) {
            const idx = w.charCodeAt(i) - 97;
            if (!node.children[idx]) {
                return w;
            }
            node = node.children[idx]!;
            if (node.isEnd) {
                return w.slice(0, i + 1);
            }
        }
        return w;
    }
}

function replaceWords(dictionary: string[], sentence: string): string {
    const trie = new Trie();
    for (const w of dictionary) {
        trie.insert(w);
    }

    return sentence
        .split(' ')
        .map(w => trie.search(w))
        .join(' ');
}
