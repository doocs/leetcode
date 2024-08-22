const inf = 1 << 29;

class Trie {
    children: (Trie | null)[];
    cost: number;

    constructor() {
        this.children = Array(26).fill(null);
        this.cost = inf;
    }

    insert(word: string, cost: number): void {
        let node: Trie = this;
        for (const c of word) {
            const idx = c.charCodeAt(0) - 97;
            if (!node.children[idx]) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx]!;
        }
        node.cost = Math.min(node.cost, cost);
    }
}

function minimumCost(target: string, words: string[], costs: number[]): number {
    const trie = new Trie();
    for (let i = 0; i < words.length; ++i) {
        trie.insert(words[i], costs[i]);
    }

    const n = target.length;
    const f: number[] = Array(n).fill(0);
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
            const idx = target.charCodeAt(j) - 97;
            if (!node?.children[idx]) {
                return f[i];
            }
            node = node.children[idx];
            f[i] = Math.min(f[i], node!.cost + dfs(j + 1));
        }
        return f[i];
    };

    const ans = dfs(0);
    return ans < inf ? ans : -1;
}
