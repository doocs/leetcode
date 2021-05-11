func leafSimilar(root1 *TreeNode, root2 *TreeNode) bool {
	var l1, l2 []int
	if root1 != nil {
		dfs(root1, &l1)
	}
	if root2 != nil {
		dfs(root2, &l2)
	}
	return reflect.DeepEqual(l1, l2)
}

func dfs(root *TreeNode, leaves *[]int) {
	if root.Left == nil && root.Right == nil {
		*leaves = append(*leaves, root.Val)
	} else {
		if root.Left != nil {
			dfs(root.Left, leaves)
		}
		if root.Right != nil {
			dfs(root.Right, leaves)
		}
	}
}
