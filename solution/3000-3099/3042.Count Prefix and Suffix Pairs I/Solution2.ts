class Node {
    children: Map<number, Node> = new Map<number, Node>();
    cnt: number = 0;
}

function countPrefixSuffixPairs(words: string[]): number {
    let ans: number = 0;
    const trie: Node = new Node();
    for (const s of words) {
        let node: Node = trie;
        const m: number = s.length;
        for (let i: number = 0; i < m; ++i) {
            const p: number = s.charCodeAt(i) * 32 + s.charCodeAt(m - i - 1);
            if (!node.children.has(p)) {
                node.children.set(p, new Node());
            }
            node = node.children.get(p)!;
            ans += node.cnt;
        }
        ++node.cnt;
    }
    return ans;
}
