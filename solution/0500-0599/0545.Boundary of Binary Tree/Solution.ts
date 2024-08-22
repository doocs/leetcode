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

function boundaryOfBinaryTree(root: TreeNode | null): number[] {
    const ans: number[] = [root.val];
    if (root.left === root.right) {
        return ans;
    }

    const left: number[] = [];
    const leaves: number[] = [];
    const right: number[] = [];

    const dfs = function (nums: number[], root: TreeNode | null, i: number) {
        if (!root) {
            return;
        }
        if (i === 0) {
            if (root.left !== root.right) {
                nums.push(root.val);
                if (root.left) {
                    dfs(nums, root.left, i);
                } else {
                    dfs(nums, root.right, i);
                }
            }
        } else if (i === 1) {
            if (root.left === root.right) {
                nums.push(root.val);
            } else {
                dfs(nums, root.left, i);
                dfs(nums, root.right, i);
            }
        } else {
            if (root.left !== root.right) {
                nums.push(root.val);
                if (root.right) {
                    dfs(nums, root.right, i);
                } else {
                    dfs(nums, root.left, i);
                }
            }
        }
    };

    dfs(left, root.left, 0);
    dfs(leaves, root, 1);
    dfs(right, root.right, 2);

    return ans.concat(left).concat(leaves).concat(right.reverse());
}
