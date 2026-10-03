class Trie {
    children: (Trie | null)[] = Array(26).fill(null);

    insert(word: string): void {
        let node: Trie = this;
        for (const c of word) {
            const i = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node.children[i]) {
                node.children[i] = new Trie();
            }
            node = node.children[i];
        }
    }
}

function minValidStrings(words: string[], target: string): number {
    const n = target.length;
    const trie = new Trie();
    for (const w of words) {
        trie.insert(w);
    }
    const inf = 1 << 30;
    const f: number[] = Array(n + 1).fill(inf);
    f[n] = 0;
    for (let i = n - 1; i >= 0; --i) {
        let node: Trie | null = trie;
        for (let j = i; j < n; ++j) {
            const k = target.charCodeAt(j) - 97;
            if (!node?.children[k]) {
                break;
            }
            node = node.children[k];
            f[i] = Math.min(f[i], 1 + f[j + 1]);
        }
    }
    return f[0] < inf ? f[0] : -1;
}
