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

func distantSubarrays(nums []int, goal int, k int) int64 {
	n := len(nums)
	s := make([]int, n+1)

	for i, x := range nums {
		s[i+1] = s[i] + x
	}

	st := append([]int(nil), s...)
	sort.Ints(st)

	ans := n * (n + 1) / 2
	bit := NewBinaryIndexedTree(len(st) + 1)

	for _, v := range s {
		a := v - goal - k + 1
		b := v - goal + k - 1

		l := sort.SearchInts(st, a) + 1
		r := sort.SearchInts(st, b+1)

		if l <= r {
			ans -= bit.query(r) - bit.query(l-1)
		}

		bit.update(sort.SearchInts(st, v)+1, 1)
	}

	return int64(ans)
}
