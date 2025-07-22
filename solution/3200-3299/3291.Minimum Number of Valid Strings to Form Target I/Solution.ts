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
    const f = Array(n).fill(0);

    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i]) {
            return f[i];
        }
        f[i] = inf;
        let node: Trie | null = trie;
        for (let j = i; j < n; ++j) {
            const k = target[j].charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node?.children[k]) {
                break;
            }
            node = node.children[k];
            f[i] = Math.min(f[i], 1 + dfs(j + 1));
        }
        return f[i];
    };

    const ans = dfs(0);
    return ans < inf ? ans : -1;
}
