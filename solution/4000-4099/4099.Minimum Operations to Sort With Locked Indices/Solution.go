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

func (bit *BinaryIndexedTree) update(x int, delta int) {
	for x <= bit.n {
		bit.c[x] += delta
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += bit.c[x]
		x -= x & -x
	}
	return s
}

func minOperations(nums []int, locked []int, k int) int {
	n := len(nums)
	bit := NewBinaryIndexedTree(n)
	inv := 0
	for i := n - 1; i >= 0; i-- {
		inv += bit.query(nums[i] - 1)
		bit.update(nums[i], 1)
	}

	cnt, mx, j := 0, 0, 0
	for i, x := range nums {
		mx = max(mx, x)
		if mx == i+1 {
			if i > j {
				for p := j; p <= i; p++ {
					cnt += locked[p]
				}
			}
			j = i + 1
		}
	}

	if cnt > k {
		return -1
	}
	return cnt + inv
}
