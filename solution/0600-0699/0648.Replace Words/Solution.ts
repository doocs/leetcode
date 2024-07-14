class Trie {
    #children: Record<string, Trie> = {};
    #ref = -1;

    insert(w: string, i: number) {
        let node: Trie = this;
        for (const c of w) {
            node.#children[c] ??= new Trie();
            node = node.#children[c];
        }
        node.#ref = i;
    }

    search(w: string): number {
        let node: Trie = this;
        for (const c of w) {
            if (!node.#children[c]) {
                return -1;
            }
            node = node.#children[c];
            if (node.#ref !== -1) {
                return node.#ref;
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
