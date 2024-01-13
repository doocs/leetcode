func isIdealPermutation(nums []int) bool {
	n := len(nums)
	tree := newBinaryIndexedTree(n)
	cnt := 0
	for i, v := range nums {
		if i < n-1 && v > nums[i+1] {
			cnt++
		}
		cnt -= (i - tree.query(v))
		if cnt < 0 {
			break
		}
		tree.update(v+1, 1)
	}
	return cnt == 0
}

type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) BinaryIndexedTree {
	c := make([]int, n+1)
	return BinaryIndexedTree{n, c}
}

func (this BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += x & -x
	}
}

func (this BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= x & -x
	}
	return s
}