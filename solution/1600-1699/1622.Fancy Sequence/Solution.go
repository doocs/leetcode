const mod int64 = 1e9 + 7

type node struct {
	left, right *node
	l, r, mid   int
	v, add, mul int64
}

func newNode(l, r int) *node {
	return &node{l: l, r: r, mid: (l + r) >> 1, mul: 1}
}

type segmentTree struct{ root *node }

func newSegmentTree() *segmentTree {
	return &segmentTree{root: newNode(1, 100001)}
}

func (t *segmentTree) modify(l, r int, mul, add int64, o *node) {
	if l > r {
		return
	}
	if o.l >= l && o.r <= r {
		t.apply(o, mul, add)
		return
	}
	t.pushdown(o)
	if l <= o.mid {
		t.modify(l, r, mul, add, o.left)
	}
	if r > o.mid {
		t.modify(l, r, mul, add, o.right)
	}
	t.pushup(o)
}

func (t *segmentTree) query(l, r int, o *node) int64 {
	if l > r {
		return 0
	}
	if o.l >= l && o.r <= r {
		return o.v
	}
	t.pushdown(o)
	var v int64
	if l <= o.mid {
		v = (v + t.query(l, r, o.left)) % mod
	}
	if r > o.mid {
		v = (v + t.query(l, r, o.right)) % mod
	}
	return v
}

func (t *segmentTree) apply(o *node, mul, add int64) {
	o.v = (o.v*mul + int64(o.r-o.l+1)*add) % mod
	o.add = (o.add*mul + add) % mod
	o.mul = o.mul * mul % mod
}

func (t *segmentTree) pushup(o *node) {
	o.v = (o.left.v + o.right.v) % mod
}

func (t *segmentTree) pushdown(o *node) {
	if o.left == nil {
		o.left = newNode(o.l, o.mid)
	}
	if o.right == nil {
		o.right = newNode(o.mid+1, o.r)
	}
	if o.add != 0 || o.mul != 1 {
		t.apply(o.left, o.mul, o.add)
		t.apply(o.right, o.mul, o.add)
		o.add, o.mul = 0, 1
	}
}

type Fancy struct {
	n    int
	tree *segmentTree
}

func Constructor() Fancy {
	return Fancy{tree: newSegmentTree()}
}

func (f *Fancy) Append(val int) {
	f.n++
	f.tree.modify(f.n, f.n, 1, int64(val), f.tree.root)
}

func (f *Fancy) AddAll(inc int) {
	f.tree.modify(1, f.n, 1, int64(inc), f.tree.root)
}

func (f *Fancy) MultAll(m int) {
	f.tree.modify(1, f.n, int64(m), 0, f.tree.root)
}

func (f *Fancy) GetIndex(idx int) int {
	if idx >= f.n {
		return -1
	}
	return int(f.tree.query(idx+1, idx+1, f.tree.root))
}
