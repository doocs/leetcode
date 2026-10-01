/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
type BSTIterator struct {
	cur  int
	vals []int
}

func Constructor(root *TreeNode) BSTIterator {
	it := BSTIterator{vals: []int{}}
	var inorder func(*TreeNode)
	inorder = func(root *TreeNode) {
		if root != nil {
			inorder(root.Left)
			it.vals = append(it.vals, root.Val)
			inorder(root.Right)
		}
	}
	inorder(root)
	return it
}

func (this *BSTIterator) Next() int {
	res := this.vals[this.cur]
	this.cur++
	return res
}

func (this *BSTIterator) HasNext() bool {
	return this.cur < len(this.vals)
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * obj := Constructor(root);
 * param_1 := obj.Next();
 * param_2 := obj.HasNext();
 */
