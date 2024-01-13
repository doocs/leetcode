func isValidBST(root *TreeNode) bool {
	stack := make([]*TreeNode, 0)
	var prev *TreeNode = nil
	node := root
	for len(stack) > 0 || node != nil {
		for node != nil {
			stack = append(stack, node)
			node = node.Left
		}
		node = stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		if prev == nil || node.Val > prev.Val {
			prev = node
		} else {
			return false
		}
		node = node.Right
	}
	return true
}