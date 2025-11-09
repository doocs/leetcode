type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{
		n: n,
		c: make([]int, n+1),
	}
}

func (t *BinaryIndexedTree) update(x, delta int) {
	for x <= t.n {
		t.c[x] += delta
		x += x & -x
	}
}

func (t *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += t.c[x]
		x -= x & -x
	}
	return s
}

func countMajoritySubarrays(nums []int, target int) int64 {
	n := len(nums)
	tree := NewBinaryIndexedTree(2*n + 1)
	s := n + 1
	tree.update(s, 1)
	var ans int64
	for _, x := range nums {
		if x == target {
			s++
		} else {
			s--
		}
		ans += int64(tree.query(s - 1))
		tree.update(s, 1)
	}
	return ans
}
