type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) lowbit(x int) int {
	return x & -x
}

func (this *BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= this.lowbit(x)
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

func (this *NumArray) Update(index int, val int) {
	prev := this.SumRange(index, index)
	this.tree.update(index+1, val-prev)
}

func (this *NumArray) SumRange(left int, right int) int {
	return this.tree.query(right+1) - this.tree.query(left)
}

/**
 * Your NumArray object will be instantiated and called as such:
 * obj := Constructor(nums);
 * obj.Update(index,val);
 * param_2 := obj.SumRange(left,right);
 */