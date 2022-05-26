type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	n += 1e5 + 1
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) lowbit(x int) int {
	x += 1e5 + 1
	return x & -x
}

func (this *BinaryIndexedTree) update(x, delta int) {
	x += 1e5 + 1
	for x <= this.n {
		this.c[x] += delta
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	x += 1e5 + 1
	for x > 0 {
		s += this.c[x]
		x -= this.lowbit(x)
	}
	return s
}

func subarraysWithMoreZerosThanOnes(nums []int) int {
	n := len(nums)
	s := make([]int, n+1)
	for i, v := range nums {
		if v == 0 {
			v = -1
		}
		s[i+1] = s[i] + v
	}
	tree := newBinaryIndexedTree(n + 1)
	ans := 0
	mod := int(1e9 + 7)
	for _, v := range s {
		ans = (ans + tree.query(v-1)) % mod
		tree.update(v, 1)
	}
	return ans
}