/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
type FindElements struct {
	vis map[int]bool
}

func Constructor(root *TreeNode) FindElements {
	root.Val = 0
	vis := map[int]bool{}
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		vis[root.Val] = true
		if root.Left != nil {
			root.Left.Val = root.Val*2 + 1
			dfs(root.Left)
		}
		if root.Right != nil {
			root.Right.Val = root.Val*2 + 2
			dfs(root.Right)
		}
	}
	dfs(root)
	return FindElements{vis}
}

func (this *FindElements) Find(target int) bool {
	return this.vis[target]
}

/**
 * Your FindElements object will be instantiated and called as such:
 * obj := Constructor(root);
 * param_1 := obj.Find(target);
 */