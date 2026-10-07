/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function getDirections(root: TreeNode | null, startValue: number, destValue: number): string {
    const lca = (root: TreeNode | null, p: number, q: number): TreeNode | null => {
        const ret = new Map<TreeNode, TreeNode | null>();
        const stk: [TreeNode | null, number][] = [[root, 0]];
        while (stk.length) {
            const [node, state] = stk.pop()!;
            if (state === 0) {
                if (node === null) {
                    continue;
                }
                if (node.val === p || node.val === q) {
                    ret.set(node, node);
                    continue;
                }
                stk.push([node, 1]);
                stk.push([node.right, 0]);
                stk.push([node.left, 0]);
            } else {
                const left = node!.left ? ret.get(node!.left) : null;
                const right = node!.right ? ret.get(node!.right) : null;
                ret.set(node!, left && right ? node : (left ?? right));
            }
        }
        return ret.get(root!) ?? null;
    };

    const dfs = (start: TreeNode | null, x: number, path: string[]): boolean => {
        const stk: [TreeNode | null, number][] = [[start, 0]];
        while (stk.length) {
            const [node, state] = stk.pop()!;
            if (state === 0) {
                if (node === null) {
                    continue;
                }
                if (node.val === x) {
                    return true;
                }
                path.push('L');
                stk.push([node, 1]);
                stk.push([node.left, 0]);
            } else if (state === 1) {
                path[path.length - 1] = 'R';
                stk.push([node, 2]);
                stk.push([node!.right, 0]);
            } else {
                path.pop();
            }
        }
        return false;
    };

    const node = lca(root, startValue, destValue);
    const pathToStart: string[] = [];
    const pathToDest: string[] = [];
    dfs(node, startValue, pathToStart);
    dfs(node, destValue, pathToDest);
    return 'U'.repeat(pathToStart.length) + pathToDest.join('');
}
