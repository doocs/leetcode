function replaceValueInTree(root) {
    let q = [root];
    let [sum, nextSum] = [0, root.val];

    while (q.length) {
        const qNext = [];
        [sum, nextSum] = [nextSum, 0];

        for (const node of q) {
            const x = (node.left?.val ?? 0) + (node.right?.val ?? 0);
            node.val = sum - node.val;
            nextSum += x;

            if (node.left) {
                node.left.val = x;
                qNext.push(node.left);
            }

            if (node.right) {
                node.right.val = x;
                qNext.push(node.right);
            }
        }

        q = qNext;
    }

    return root;
}
