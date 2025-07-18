class Trie {
    children: Record<string, Trie>;
    fid: number;

    constructor() {
        this.children = {};
        this.fid = -1;
    }

    insert(i: number, f: string): void {
        let node: Trie = this;
        const ps = f.split('/');
        for (let j = 1; j < ps.length; ++j) {
            const p = ps[j];
            if (!(p in node.children)) {
                node.children[p] = new Trie();
            }
            node = node.children[p];
        }
        node.fid = i;
    }

    search(): number[] {
        const ans: number[] = [];
        const dfs = (root: Trie): void => {
            if (root.fid !== -1) {
                ans.push(root.fid);
                return;
            }
            for (const child of Object.values(root.children)) {
                dfs(child);
            }
        };
        dfs(this);
        return ans;
    }
}

function removeSubfolders(folder: string[]): string[] {
    const trie = new Trie();
    for (let i = 0; i < folder.length; ++i) {
        trie.insert(i, folder[i]);
    }
    return trie.search().map(i => folder[i]);
}
