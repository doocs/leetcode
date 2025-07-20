var deleteDuplicateFolder = function (paths) {
    class Trie {
        constructor() {
            this.serial = '';
            this.children = new Map();
        }
    }

    const root = new Trie();
    for (const path of paths) {
        let cur = root;
        for (const node of path) {
            if (!cur.children.has(node)) {
                cur.children.set(node, new Trie());
            }
            cur = cur.children.get(node);
        }
    }

    const freq = new Map();
    function construct(node) {
        if (node.children.size === 0) return;
        const v = [];
        for (const [folder, child] of node.children) {
            construct(child);
            v.push(`${folder}(${child.serial})`);
        }
        v.sort();
        node.serial = v.join('');
        freq.set(node.serial, (freq.get(node.serial) || 0) + 1);
    }
    construct(root);

    const ans = [];
    const path = [];
    function operate(node) {
        if ((freq.get(node.serial) || 0) > 1) return;
        if (path.length > 0) {
            ans.push([...path]);
        }
        for (const [folder, child] of node.children) {
            path.push(folder);
            operate(child);
            path.pop();
        }
    }
    operate(root);

    return ans;
};
