/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var boundaryOfBinaryTree = function (root) {
    let leftBoundary = function (root, res) {
        while (root) {
            let curVal = root.val;
            if (root.left) {
                root = root.left;
            } else if (root.right) {
                root = root.right;
            } else {
                break;
            }
            res.push(curVal);
        }
    };
    let rightBoundary = function (root, res) {
        let stk = [];
        while (root) {
            let curVal = root.val;
            if (root.right) {
                root = root.right;
            } else if (root.left) {
                root = root.left;
            } else {
                break;
            }
            stk.push(curVal);
        }
        let len = stk.length;
        for (let i = 0; i < len; i++) {
            res.push(stk.pop());
        }
    };
    let levelBoundary = function (root, res) {
        if (root) {
            levelBoundary(root.left, res);
            if (!root.left && !root.right) {
                res.push(root.val);
            }
            levelBoundary(root.right, res);
        }
    };
    let res = [];
    if (root) {
        res.push(root.val);
        leftBoundary(root.left, res);
        if (root.left || root.right) {
            levelBoundary(root, res);
        }
        rightBoundary(root.right, res);
    }
    return res;
};
