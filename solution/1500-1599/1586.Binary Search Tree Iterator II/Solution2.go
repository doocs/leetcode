/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
type BSTIterator struct {
	nums []int
	i, n int
}

func Constructor(root *TreeNode) BSTIterator {
	nums := []int{}
	type frame struct {
		node  *TreeNode
		state int
	}
	stk := []frame{{root, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node, state := cur.node, cur.state
		if node == nil {
			continue
		}
		if state == 0 {
			stk = append(stk, frame{node, 1}, frame{node.Left, 0})
			continue
		}
		nums = append(nums, node.Val)
		stk = append(stk, frame{node.Right, 0})
	}
	return BSTIterator{nums, -1, len(nums)}
}

func (this *BSTIterator) HasNext() bool {
	return this.i < this.n-1
}

func (this *BSTIterator) Next() int {
	this.i++
	return this.nums[this.i]
}

func (this *BSTIterator) HasPrev() bool {
	return this.i > 0
}

func (this *BSTIterator) Prev() int {
	this.i--
	return this.nums[this.i]
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * obj := Constructor(root);
 * param_1 := obj.HasNext();
 * param_2 := obj.Next();
 * param_3 := obj.HasPrev();
 * param_4 := obj.Prev();
 */
