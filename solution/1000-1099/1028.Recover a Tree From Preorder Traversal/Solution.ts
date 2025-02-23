function recoverFromPreorder(traversal: string): TreeNode | null {
    const stack: TreeNode[] = [];
    let i = 0;

    while (i < traversal.length) {
        let depth = 0;
        while (i < traversal.length && traversal[i] === '-') {
            depth++;
            i++;
        }

        let num = 0;
        while (i < traversal.length && !Number.isNaN(+traversal[i])) {
            num = num * 10 + +traversal[i];
            i++;
        }

        // Create the new node
        const newNode = new TreeNode(num);

        while (stack.length > depth) {
            stack.pop();
        }

        if (stack.length > 0) {
            const i = stack.length - 1;
            if (stack[i].left === null) {
                stack[i].left = newNode;
            } else {
                stack[i].right = newNode;
            }
        }

        stack.push(newNode);
    }

    return stack.length ? stack[0] : null;
}
