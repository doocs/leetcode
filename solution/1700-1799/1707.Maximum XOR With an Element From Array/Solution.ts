class Trie {
    children: (Trie | null)[];

    constructor() {
        this.children = [null, null];
    }

    insert(x: number): void {
        let node: Trie | null = this;
        for (let i = 30; ~i; i--) {
            const v = (x >> i) & 1;
            if (node.children[v] === null) {
                node.children[v] = new Trie();
            }
            node = node.children[v] as Trie;
        }
    }

    search(x: number): number {
        let node: Trie | null = this;
        let ans = 0;
        for (let i = 30; ~i; i--) {
            const v = (x >> i) & 1;
            if (node.children[v ^ 1] !== null) {
                ans |= 1 << i;
                node = node.children[v ^ 1] as Trie;
            } else if (node.children[v] !== null) {
                node = node.children[v] as Trie;
            } else {
                return -1;
            }
        }
        return ans;
    }
}

function maximizeXor(nums: number[], queries: number[][]): number[] {
    nums.sort((a, b) => a - b);
    const n = queries.length;
    const idx = Array.from({ length: n }, (_, i) => i);
    idx.sort((i, j) => queries[i][1] - queries[j][1]);
    const ans: number[] = [];
    const trie = new Trie();
    let j = 0;
    for (const i of idx) {
        const x = queries[i][0];
        const m = queries[i][1];
        while (j < nums.length && nums[j] <= m) {
            trie.insert(nums[j++]);
        }
        ans[i] = trie.search(x);
    }
    return ans;
}
