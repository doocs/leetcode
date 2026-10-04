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
    const pathToStart: string[] = [];
    const pathToDest: string[] = [];
    dfs(root, startValue, pathToStart);
    dfs(root, destValue, pathToDest);
    let i = 0;
    while (i < pathToStart.length && i < pathToDest.length && pathToStart[i] === pathToDest[i]) {
        ++i;
    }
    return 'U'.repeat(pathToStart.length - i) + pathToDest.slice(i).join('');
}
