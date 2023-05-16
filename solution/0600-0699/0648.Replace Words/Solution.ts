class Trie {
    private children: Trie[];
    private ref: number;

    constructor() {
        this.children = new Array<Trie>(26);
        this.ref = -1;
    }

    public insert(w: string, i: number) {
        let node: Trie = this;
        for (const c of w) {
            const idx = c.charCodeAt(0) - 97;
            if (!node.children[idx]) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.ref = i;
    }

    public search(w: string): number {
        let node: Trie = this;
        for (const c of w) {
            const idx = c.charCodeAt(0) - 97;
            if (!node.children[idx]) {
                return -1;
            }
            node = node.children[idx];
            if (node.ref !== -1) {
                return node.ref;
            }
        }
        return -1;
    }
}

function replaceWords(dictionary: string[], sentence: string): string {
    const trie = new Trie();
    for (let i = 0; i < dictionary.length; i++) {
        trie.insert(dictionary[i], i);
    }
    return sentence
        .split(' ')
        .map(w => {
            const idx = trie.search(w);
            return idx !== -1 ? dictionary[idx] : w;
        })
        .join(' ');
}
