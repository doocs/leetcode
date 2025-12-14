type binaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *binaryIndexedTree {
	return &binaryIndexedTree{
		n: n,
		c: make([]int, n+1),
	}
}

func (bit *binaryIndexedTree) update(x, delta int) {
	for x <= bit.n {
		bit.c[x] += delta
		x += x & -x
	}
}

func (bit *binaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += bit.c[x]
		x -= x & -x
	}
	return s
}

func minDeletions(s string, queries [][]int) []int {
	n := len(s)
	nums := make([]int, n)
	bit := newBinaryIndexedTree(n)

	for i := 1; i < n; i++ {
		if s[i] == s[i-1] {
			nums[i] = 1
			bit.update(i+1, 1)
		}
	}

	ans := make([]int, 0)

	for _, q := range queries {
		if q[0] == 1 {
			j := q[1]

			delta := (nums[j] ^ 1 - nums[j])
			nums[j] ^= 1
			bit.update(j+1, delta)

			if j+1 < n {
				delta = (nums[j+1] ^ 1 - nums[j+1])
				nums[j+1] ^= 1
				bit.update(j+2, delta)
			}
		} else {
			l, r := q[1], q[2]
			ans = append(ans, bit.query(r+1)-bit.query(l+1))
		}
	}

	return ans
}
