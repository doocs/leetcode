type Node struct {
	l, r, v int
}

type SegmentTree struct {
	tr   []Node
	nums []int
}

func newSegmentTree(nums []int) *SegmentTree {
	n := len(nums)
	tr := make([]Node, n<<2)
	for i := range tr {
		tr[i] = Node{}
	}
	tree := &SegmentTree{
		tr:   tr,
		nums: nums,
	}
	tree.build(1, 1, n)
	return tree
}

func (tree *SegmentTree) build(u, l, r int) {
	tree.tr[u].l, tree.tr[u].r = l, r
	if l == r {
		tree.tr[u].v = tree.nums[l-1]
		return
	}
	mid := (l + r) >> 1
	tree.build(u<<1, l, mid)
	tree.build(u<<1|1, mid+1, r)
	tree.pushup(u)
}

func (tree *SegmentTree) modify(u, x, v int) {
	if tree.tr[u].l == x && tree.tr[u].r == x {
		tree.tr[u].v = v
		return
	}
	mid := (tree.tr[u].l + tree.tr[u].r) >> 1
	if x <= mid {
		tree.modify(u<<1, x, v)
	} else {
		tree.modify(u<<1|1, x, v)
	}
	tree.pushup(u)
}

func (tree *SegmentTree) query(u, l, r int) (v int) {
	if tree.tr[u].l >= l && tree.tr[u].r <= r {
		return tree.tr[u].v
	}
	mid := (tree.tr[u].l + tree.tr[u].r) >> 1
	if l <= mid {
		v += tree.query(u<<1, l, r)
	}
	if r > mid {
		v += tree.query(u<<1|1, l, r)
	}
	return v
}

func (tree *SegmentTree) pushup(u int) {
	tree.tr[u].v = tree.tr[u<<1].v + tree.tr[u<<1|1].v
}

type NumArray struct {
	tree *SegmentTree
}

func Constructor(nums []int) NumArray {
	return NumArray{
		tree: newSegmentTree(nums),
	}
}

func (this *NumArray) Update(index int, val int) {
	this.tree.modify(1, index+1, val)
}

func (this *NumArray) SumRange(left int, right int) int {
	return this.tree.query(1, left+1, right+1)
}

/**
 * Your NumArray object will be instantiated and called as such:
 * obj := Constructor(nums);
 * obj.Update(index,val);
 * param_2 := obj.SumRange(left,right);
 */