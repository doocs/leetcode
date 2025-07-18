class Trie {
    constructor() {
        this.children = {};
        this.fid = -1;
    }

    insert(i, f) {
        let node = this;
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

    search() {
        const ans = [];
        const dfs = root => {
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

/**
 * @param {string[]} folder
 * @return {string[]}
 */
var removeSubfolders = function (folder) {
    const trie = new Trie();
    for (let i = 0; i < folder.length; ++i) {
        trie.insert(i, folder[i]);
    }
    return trie.search().map(i => folder[i]);
};
