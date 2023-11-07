/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func getNumber(root *TreeNode, ops [][]int) (ans int) {
	rbt := redblacktree.NewWithIntComparator()
	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		rbt.Put(root.Val, nil)
		dfs(root.Left)
		dfs(root.Right)
	}
	dfs(root)
	for i := len(ops) - 1; i >= 0; i-- {
		t, x, y := ops[i][0], ops[i][1], ops[i][2]
		node, _ := rbt.Ceiling(x)
		for node != nil && node.Key.(int) <= y {
			rbt.Remove(node.Key)
			node, _ = rbt.Ceiling(x)
			ans += t
		}
	}
	return
}