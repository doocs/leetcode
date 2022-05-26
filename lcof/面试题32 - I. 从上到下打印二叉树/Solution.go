func levelOrder(root *TreeNode) []int {
	if root == nil {
		return []int{}
	}
	q := []*TreeNode{}
	q = append(q, root)
	// 层序遍历,用队列,遍历到谁,就把谁的左右结点加入队列
	res := []int{}
	for len(q) != 0 {
		tmp := q[0]
		q = q[1:]
		res = append(res, tmp.Val)
		if tmp.Left != nil {
			q = append(q, tmp.Left)
		}
		if tmp.Right != nil {
			q = append(q, tmp.Right)
		}
	}
	return res
}