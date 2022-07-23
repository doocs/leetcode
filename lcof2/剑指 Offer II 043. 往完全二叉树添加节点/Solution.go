/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
type CBTInserter struct {
	tree []*TreeNode
}

func Constructor(root *TreeNode) CBTInserter {
	q := []*TreeNode{root}
	tree := []*TreeNode{}
	for len(q) > 0 {
		node := q[0]
		tree = append(tree, node)
		q = q[1:]
		if node.Left != nil {
			q = append(q, node.Left)
		}
		if node.Right != nil {
			q = append(q, node.Right)
		}
	}
	return CBTInserter{tree}
}

func (this *CBTInserter) Insert(v int) int {
	pid := (len(this.tree) - 1) >> 1
	node := &TreeNode{Val: v}
	this.tree = append(this.tree, node)
	p := this.tree[pid]
	if p.Left == nil {
		p.Left = node
	} else {
		p.Right = node
	}
	return p.Val
}

func (this *CBTInserter) Get_root() *TreeNode {
	return this.tree[0]
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * obj := Constructor(root);
 * param_1 := obj.Insert(v);
 * param_2 := obj.Get_root();
 */