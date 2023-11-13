class Trie {
    children: (Trie | null)[];
    cnt: number;

    constructor() {
        this.children = [null, null];
        this.cnt = 0;
    }

    insert(x: number): void {
        let node: Trie | null = this;
        for (let i = 20; i >= 0; i--) {
            const v = (x >> i) & 1;
            if (node.children[v] === null) {
                node.children[v] = new Trie();
            }
            node = node.children[v] as Trie;
            node.cnt++;
        }
    }

    search(x: number): number {
        let node: Trie | null = this;
        let ans = 0;
        for (let i = 20; i >= 0; i--) {
            const v = (x >> i) & 1;
            if (node.children[v ^ 1] !== null && (node.children[v ^ 1] as Trie).cnt > 0) {
                ans |= 1 << i;
                node = node.children[v ^ 1] as Trie;
            } else {
                node = node.children[v] as Trie;
            }
        }
        return ans;
    }

    remove(x: number): void {
        let node: Trie | null = this;
        for (let i = 20; i >= 0; i--) {
            const v = (x >> i) & 1;
            node = node.children[v] as Trie;
            node.cnt--;
        }
    }
}

function maximumStrongPairXor(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const tree = new Trie();
    let ans = 0;
    let i = 0;

    for (const y of nums) {
        tree.insert(y);

        while (y > nums[i] * 2) {
            tree.remove(nums[i++]);
        }

        ans = Math.max(ans, tree.search(y));
    }

    return ans;
}
