const inf int = 1e18

type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) BinaryIndexedTree {
	c := make([]int, n+1)
	for i := range c {
		c[i] = -inf
	}
	return BinaryIndexedTree{n: n, c: c}
}

func (bit *BinaryIndexedTree) update(x, v int) {
	for x <= bit.n {
		bit.c[x] = max(bit.c[x], v)
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	mx := -inf
	for x > 0 {
		mx = max(mx, bit.c[x])
		x -= x & -x
	}
	return mx
}

func maxBalancedSubsequenceSum(nums []int) int64 {
	n := len(nums)
	arr := make([]int, n)
	for i, x := range nums {
		arr[i] = x - i
	}
	sort.Ints(arr)
	m := 0
	for i, x := range arr {
		if i == 0 || x != arr[i-1] {
			arr[m] = x
			m++
		}
	}
	arr = arr[:m]
	tree := NewBinaryIndexedTree(m)
	for i, x := range nums {
		j := sort.SearchInts(arr, x-i) + 1
		v := max(tree.query(j), 0) + x
		tree.update(j, v)
	}
	return int64(tree.query(m))
}