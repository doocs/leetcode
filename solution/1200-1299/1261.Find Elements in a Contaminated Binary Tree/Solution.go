/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
type FindElements struct {
	nodes map[int]bool
}

func Constructor(root *TreeNode) FindElements {
	root.Val = 0
	nodes := make(map[int]bool)
	nodes[0] = true
	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		if root.Left != nil {
			root.Left.Val = root.Val*2 + 1
			nodes[root.Left.Val] = true
		}
		if root.Right != nil {
			root.Right.Val = root.Val*2 + 2
			nodes[root.Right.Val] = true
		}
		dfs(root.Left)
		dfs(root.Right)
	}
	dfs(root)
	return FindElements{nodes}
}

func (this *FindElements) Find(target int) bool {
	return this.nodes[target]
}

/**
 * Your FindElements object will be instantiated and called as such:
 * obj := Constructor(root);
 * param_1 := obj.Find(target);
 */