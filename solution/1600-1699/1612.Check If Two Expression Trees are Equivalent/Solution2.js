/**
 * Definition for a binary tree node.
 * function Node(val, left, right) {
 *     this.val = (val===undefined ? " " : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {Node} root1
 * @param {Node} root2
 * @return {boolean}
 */
var checkEquivalence = function (root1, root2) {
    const dfs = root => {
        const cnt = new Array(26).fill(0);
        if (!root) {
            return cnt;
        }
        if (root.val === '+' || root.val === '-') {
            const l = dfs(root.left);
            const r = dfs(root.right);
            const k = root.val === '+' ? 1 : -1;
            for (let i = 0; i < 26; ++i) {
                cnt[i] = l[i] + k * r[i];
            }
        } else {
            cnt[root.val.charCodeAt(0) - 'a'.charCodeAt(0)]++;
        }
        return cnt;
    };
    const cnt1 = dfs(root1);
    const cnt2 = dfs(root2);
    for (let i = 0; i < 26; ++i) {
        if (cnt1[i] !== cnt2[i]) {
            return false;
        }
    }
    return true;
};
