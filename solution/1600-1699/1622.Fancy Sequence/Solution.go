const MOD int64 = 1e9 + 7

type Node struct {
	left  *Node
	right *Node
	l     int
	r     int
	mid   int
	v     int64
	add   int64
	mul   int64
}

func newNode(l, r int) *Node {
	return &Node{
		l:   l,
		r:   r,
		mid: (l + r) >> 1,
		mul: 1,
	}
}

type SegmentTree struct {
	root *Node
}

func newSegmentTree() *SegmentTree {
	return &SegmentTree{
		root: newNode(1, 100001),
	}
}

func (t *SegmentTree) modifyAdd(l, r int, inc int64) {
	t.modifyAddNode(l, r, inc, t.root)
}

func (t *SegmentTree) modifyAddNode(l, r int, inc int64, node *Node) {
	if l > r {
		return
	}

	if node.l >= l && node.r <= r {
		node.v = (node.v + int64(node.r-node.l+1)*inc) % MOD
		node.add = (node.add + inc) % MOD
		return
	}

	t.pushdown(node)

	if l <= node.mid {
		t.modifyAddNode(l, r, inc, node.left)
	}

	if r > node.mid {
		t.modifyAddNode(l, r, inc, node.right)
	}

	t.pushup(node)
}

func (t *SegmentTree) modifyMul(l, r int, m int64) {
	t.modifyMulNode(l, r, m, t.root)
}

func (t *SegmentTree) modifyMulNode(l, r int, m int64, node *Node) {
	if l > r {
		return
	}

	if node.l >= l && node.r <= r {
		node.v = node.v * m % MOD
		node.add = node.add * m % MOD
		node.mul = node.mul * m % MOD
		return
	}

	t.pushdown(node)

	if l <= node.mid {
		t.modifyMulNode(l, r, m, node.left)
	}

	if r > node.mid {
		t.modifyMulNode(l, r, m, node.right)
	}

	t.pushup(node)
}

func (t *SegmentTree) query(l, r int) int {
	return int(t.queryNode(l, r, t.root))
}

func (t *SegmentTree) queryNode(l, r int, node *Node) int64 {
	if l > r {
		return 0
	}

	if node.l >= l && node.r <= r {
		return node.v
	}

	t.pushdown(node)

	var v int64

	if l <= node.mid {
		v = (v + t.queryNode(l, r, node.left)) % MOD
	}

	if r > node.mid {
		v = (v + t.queryNode(l, r, node.right)) % MOD
	}

	return v
}

func (t *SegmentTree) pushup(node *Node) {
	node.v = (node.left.v + node.right.v) % MOD
}

func (t *SegmentTree) pushdown(node *Node) {

	if node.left == nil {
		node.left = newNode(node.l, node.mid)
	}

	if node.right == nil {
		node.right = newNode(node.mid+1, node.r)
	}

	if node.add != 0 || node.mul != 1 {

		add := node.add
		mul := node.mul

		left := node.left
		right := node.right

		left.v = (left.v*mul + int64(left.r-left.l+1)*add) % MOD
		right.v = (right.v*mul + int64(right.r-right.l+1)*add) % MOD

		left.add = (left.add*mul + add) % MOD
		right.add = (right.add*mul + add) % MOD

		left.mul = left.mul * mul % MOD
		right.mul = right.mul * mul % MOD

		node.add = 0
		node.mul = 1
	}
}

type Fancy struct {
	n    int
	tree *SegmentTree
}

func Constructor() Fancy {
	return Fancy{
		tree: newSegmentTree(),
	}
}

func (f *Fancy) Append(val int) {
	f.n++
	f.tree.modifyAdd(f.n, f.n, int64(val))
}

func (f *Fancy) AddAll(inc int) {
	f.tree.modifyAdd(1, f.n, int64(inc))
}

func (f *Fancy) MultAll(m int) {
	f.tree.modifyMul(1, f.n, int64(m))
}

func (f *Fancy) GetIndex(idx int) int {
	if idx >= f.n {
		return -1
	}
	return f.tree.query(idx+1, idx+1)
}
