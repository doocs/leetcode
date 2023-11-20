class Trie {
    private children: Trie[] = Array(26);
    private cnt: number = 0;

    insert(w: string): void {
        let node: Trie = this;
        for (const c of w) {
            const idx: number = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node.children[idx]) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            node.cnt++;
        }
    }

    search(w: string): number {
        let node: Trie = this;
        let ans: number = 0;
        for (const c of w) {
            ans++;
            const idx: number = c.charCodeAt(0) - 'a'.charCodeAt(0);
            node = node.children[idx];
            if (node.cnt === 1) {
                return ans;
            }
        }
        return w.length;
    }
}

function wordsAbbreviation(words: string[]): string[] {
    const tries: Map<string, Trie> = new Map();
    for (const w of words) {
        const key: string = `${w.length}-${w.charCodeAt(w.length - 1) - 'a'.charCodeAt(0)}`;
        if (!tries.get(key)) {
            tries.set(key, new Trie());
        }
        tries.get(key)!.insert(w);
    }

    const ans: string[] = [];
    for (const w of words) {
        const m: number = w.length;
        const key: string = `${m}-${w.charCodeAt(m - 1) - 'a'.charCodeAt(0)}`;
        const cnt: number = tries.get(key)!.search(w);
        ans.push(cnt + 2 >= m ? w : w.substring(0, cnt) + (m - cnt - 1) + w.substring(m - 1));
    }

    return ans;
}
