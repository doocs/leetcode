function removeSubfolders(folder) {
    const createTrie = () => ({ '#': false, children: {} });
    const trie = createTrie();

    for (const f of folder) {
        const path = f.split('/');
        path.shift();

        let node = trie;
        for (const p of path) {
            if (!node.children[p]) node.children[p] = createTrie();
            node = node.children[p];
        }
        node['#'] = true;
    }

    const ans = [];
    const dfs = (trie, path = '') => {
        if (trie['#']) {
            ans.push(path);
            return;
        }

        for (const key in trie.children) {
            dfs(trie.children[key], path + '/' + key);
        }
    };

    dfs(trie);

    return ans;
}
