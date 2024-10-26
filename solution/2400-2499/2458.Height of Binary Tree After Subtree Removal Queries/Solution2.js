function treeQueries(root, queries) {
    const ans = [];
    const levels = new Map();
    const valToLevel = new Map();

    const dfs = (node, level = 0) => {
        if (!node) return level - 1;

        const max = Math.max(dfs(node.left, level + 1), dfs(node.right, level + 1));

        if (!levels.has(level)) {
            levels.set(level, []);
        }
        levels.get(level)?.push([max, node.val]);
        valToLevel.set(node.val, level);

        return max;
    };

    dfs(root, 0);

    for (const [_, l] of levels) {
        l.sort(([a], [b]) => b - a);
    }

    for (const q of queries) {
        const level = valToLevel.get(q);
        const maxes = levels.get(level);

        if (maxes.length === 1) {
            ans.push(level - 1);
        } else {
            const [val0, max0, max1] = [maxes[0][1], maxes[0][0], maxes[1][0]];
            const max = val0 === q ? max1 : max0;
            ans.push(max);
        }
    }

    return ans;
}
