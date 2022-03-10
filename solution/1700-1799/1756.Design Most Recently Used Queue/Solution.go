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

type MRUQueue struct {
	data []int
	tree *BinaryIndexedTree
}

func Constructor(n int) MRUQueue {
	data := make([]int, n+1)
	for i := range data {
		data[i] = i
	}
	return MRUQueue{data, newBinaryIndexedTree(n + 2010)}
}

func (this *MRUQueue) Fetch(k int) int {
	left, right := 1, len(this.data)
	for left < right {
		mid := (left + right) >> 1
		if mid-this.tree.query(mid) >= k {
			right = mid
		} else {
			left = mid + 1
		}
	}
	this.data = append(this.data, this.data[left])
	this.tree.update(left, 1)
	return this.data[left]
}

/**
 * Your MRUQueue object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Fetch(k);
 */