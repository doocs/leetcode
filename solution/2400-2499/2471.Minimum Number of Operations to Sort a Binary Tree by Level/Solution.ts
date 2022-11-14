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

function minimumOperations(root: TreeNode | null): number {
    const queue = [root];
    let ans = 0;
    while (queue.length !== 0) {
        const n = queue.length;
        const row: number[] = [];
        for (let i = 0; i < n; i++) {
            const { val, left, right } = queue.shift();
            row.push(val);
            left && queue.push(left);
            right && queue.push(right);
        }
        for (let i = 0; i < n - 1; i++) {
            let minIdx = i;
            for (let j = i + 1; j < n; j++) {
                if (row[j] < row[minIdx]) {
                    minIdx = j;
                }
            }
            if (i !== minIdx) {
                [row[i], row[minIdx]] = [row[minIdx], row[i]];
                ans++;
            }
        }
    }
    return ans;
}
