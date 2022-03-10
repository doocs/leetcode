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

function verticalTraversal(root: TreeNode | null): number[][] {
    let solution = [];
    dfs(root, 0, 0, solution);
    solution.sort(compare);
    let ans = [];
    let pre = Number.MIN_SAFE_INTEGER;
    for (let node of solution) {
        const [val, , idx] = node;
        if (idx != pre) {
            ans.push([]);
            pre = idx;
        }
        ans[ans.length - 1].push(val);
    }
    return ans;
}

function compare(a: Array<number>, b: Array<number>) {
    const [a0, a1, a2] = a,
        [b0, b1, b2] = b;
    if (a2 == b2) {
        if (a1 == b1) {
            return a0 - b0;
        }
        return a1 - b1;
    }
    return a2 - b2;
}

function dfs(
    root: TreeNode | null,
    depth: number,
    idx: number,
    solution: Array<Array<number>>,
) {
    if (!root) return;
    solution.push([root.val, depth, idx]);
    dfs(root.left, depth + 1, idx - 1, solution);
    dfs(root.right, depth + 1, idx + 1, solution);
}
