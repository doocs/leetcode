type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += x & -x
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= x & -x
	}
	return s
}

type MRUQueue struct {
	q    []int
	tree *BinaryIndexedTree
}

func Constructor(n int) MRUQueue {
	q := make([]int, n+1)
	for i := 1; i <= n; i++ {
		q[i] = i
	}
	return MRUQueue{q, newBinaryIndexedTree(n + 2010)}
}

func (this *MRUQueue) Fetch(k int) int {
	l, r := 1, len(this.q)
	for l < r {
		mid := (l + r) >> 1
		if mid-this.tree.query(mid) >= k {
			r = mid
		} else {
			l = mid + 1
		}
	}
	x := this.q[l]
	this.q = append(this.q, x)
	this.tree.update(l, 1)
	return x
}

/**
 * Your MRUQueue object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Fetch(k);
 */