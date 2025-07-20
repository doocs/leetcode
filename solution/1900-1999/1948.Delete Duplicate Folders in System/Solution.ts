function deleteDuplicateFolder(paths: string[][]): string[][] {
    class Trie {
        children: { [key: string]: Trie } = {};
        deleted: boolean = false;
    }

    const root = new Trie();

    for (const path of paths) {
        let cur = root;
        for (const name of path) {
            if (!cur.children[name]) {
                cur.children[name] = new Trie();
            }
            cur = cur.children[name];
        }
    }

    const g: { [key: string]: Trie } = {};

    const dfs = (node: Trie): string => {
        if (Object.keys(node.children).length === 0) return '';

        const subs: string[] = [];
        for (const [name, child] of Object.entries(node.children)) {
            subs.push(`${name}(${dfs(child)})`);
        }
        subs.sort();
        const s = subs.join('');

        if (g[s]) {
            node.deleted = true;
            g[s].deleted = true;
        } else {
            g[s] = node;
        }
        return s;
    };

    dfs(root);

    const ans: string[][] = [];
    const path: string[] = [];

    const dfs2 = (node: Trie): void => {
        if (node.deleted) return;
        if (path.length > 0) {
            ans.push([...path]);
        }
        for (const [name, child] of Object.entries(node.children)) {
            path.push(name);
            dfs2(child);
            path.pop();
        }
    };

    dfs2(root);

    return ans;
}
