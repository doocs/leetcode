type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (t *BinaryIndexedTree) update(x, delta int) {
	for ; x <= t.n; x += x & -x {
		t.c[x] += delta
	}
}

func (t *BinaryIndexedTree) query(x int) (s int) {
	for ; x > 0; x -= x & -x {
		s += t.c[x]
	}
	return s
}

type NumArray struct {
	tree *BinaryIndexedTree
}

func Constructor(nums []int) NumArray {
	tree := newBinaryIndexedTree(len(nums))
	for i, v := range nums {
		tree.update(i+1, v)
	}
	return NumArray{tree}
}

func (t *NumArray) Update(index int, val int) {
	prev := t.SumRange(index, index)
	t.tree.update(index+1, val-prev)
}

func (t *NumArray) SumRange(left int, right int) int {
	return t.tree.query(right+1) - t.tree.query(left)
}

/**
 * Your NumArray object will be instantiated and called as such:
 * obj := Constructor(nums);
 * obj.Update(index,val);
 * param_2 := obj.SumRange(left,right);
 */