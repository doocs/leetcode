type BIT struct {
	n int
	c []int
}

func newBIT(n int) *BIT {
	return &BIT{n: n, c: make([]int, n+1)}
}

func (b *BIT) update(x, delta int) {
	for ; x <= b.n; x += x & -x {
		b.c[x] += delta
	}
}

func (b *BIT) query(x int) int {
	s := 0
	for ; x > 0; x -= x & -x {
		s += b.c[x]
	}
	return s
}

func countSmallerOppositeParity(nums []int) []int {
	n := len(nums)
	sorted := make([]int, n)
	copy(sorted, nums)
	sort.Ints(sorted)

	m := 0
	if n > 0 {
		m = 1
		for i := 1; i < n; i++ {
			if sorted[i] != sorted[i-1] {
				sorted[m] = sorted[i]
				m++
			}
		}
		sorted = sorted[:m]
	}

	bits := []*BIT{newBIT(m), newBIT(m)}
	ans := make([]int, n)

	for i := n - 1; i >= 0; i-- {
		x := sort.SearchInts(sorted, nums[i]) + 1
		ans[i] = bits[nums[i]&1^1].query(x - 1)
		bits[nums[i]&1].update(x, 1)
	}
	return ans
}
