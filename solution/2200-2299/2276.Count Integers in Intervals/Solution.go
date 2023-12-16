type Node struct {
	left  *Node
	right *Node
	l     int
	r     int
	mid   int
	v     int
	add   int
}

type SegmentTree struct {
	root *Node
}

func newNode(l, r int) *Node {
	return &Node{
		left:  nil,
		right: nil,
		l:     l,
		r:     r,
		mid:   (l + r) / 2,
		v:     0,
		add:   0,
	}
}

func newSegmentTree() *SegmentTree {
	return &SegmentTree{
		root: newNode(1, 1000000001),
	}
}

func (st *SegmentTree) modify(l, r, v int, node *Node) {
	if node == nil {
		node = st.root
	}
	if l > r {
		return
	}
	if node.l >= l && node.r <= r {
		node.v = node.r - node.l + 1
		node.add = v
		return
	}
	st.pushdown(node)
	if l <= node.mid {
		st.modify(l, r, v, node.left)
	}
	if r > node.mid {
		st.modify(l, r, v, node.right)
	}
	st.pushup(node)
}

func (st *SegmentTree) query(l, r int, node *Node) int {
	if node == nil {
		node = st.root
	}
	if l > r {
		return 0
	}
	if node.l >= l && node.r <= r {
		return node.v
	}
	st.pushdown(node)
	v := 0
	if l <= node.mid {
		v += st.query(l, r, node.left)
	}
	if r > node.mid {
		v += st.query(l, r, node.right)
	}
	return v
}

func (st *SegmentTree) pushup(node *Node) {
	node.v = node.left.v + node.right.v
}

func (st *SegmentTree) pushdown(node *Node) {
	if node.left == nil {
		node.left = newNode(node.l, node.mid)
	}
	if node.right == nil {
		node.right = newNode(node.mid+1, node.r)
	}
	if node.add != 0 {
		left := node.left
		right := node.right
		left.add = node.add
		right.add = node.add
		left.v = left.r - left.l + 1
		right.v = right.r - right.l + 1
		node.add = 0
	}
}

type CountIntervals struct {
	tree *SegmentTree
}

func Constructor() CountIntervals {
	return CountIntervals{
		tree: newSegmentTree(),
	}
}

func (ci *CountIntervals) Add(left, right int) {
	ci.tree.modify(left, right, 1, nil)
}

func (ci *CountIntervals) Count() int {
	return ci.tree.query(1, 1000000000, nil)
}

/**
 * Your CountIntervals object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(left,right);
 * param_2 := obj.Count();
 */