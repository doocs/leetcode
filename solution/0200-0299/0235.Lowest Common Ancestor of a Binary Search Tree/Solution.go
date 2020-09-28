/**
 * Definition for TreeNode.
 * type TreeNode struct {
 *     Val int
 *     Left *ListNode
 *     Right *ListNode
 * }
 */
 func lowestCommonAncestor(root, p, q *TreeNode) *TreeNode {
    if root == nil {
        return nil
    }

    for root != nil {
        //如果p、q的值都小于root，说明p q 肯定在root的左子树中；
        //如果p q都大于root，说明肯定在root的右子树中
        //如果一个在左一个在右 则说明此时的root记为对应的最近公共祖先
        if root.Val > p.Val && root.Val > q.Val {
            root = root.Left
        } else if root.Val < p.Val && root.Val < q.Val {
            root = root.Right
        } else {
            return root
        }
    }
    return nil
}
