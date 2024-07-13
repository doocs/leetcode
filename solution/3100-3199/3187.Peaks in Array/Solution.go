type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{n: n, c: make([]int, n+1)}
}

func (bit *BinaryIndexedTree) update(x, delta int) {
	for ; x <= bit.n; x += x & -x {
		bit.c[x] += delta
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	s := 0
	for ; x > 0; x -= x & -x {
		s += bit.c[x]
	}
	return s
}

func countOfPeaks(nums []int, queries [][]int) (ans []int) {
	n := len(nums)
	tree := NewBinaryIndexedTree(n - 1)
	update := func(i, val int) {
		if i <= 0 || i >= n-1 {
			return
		}
		if nums[i-1] < nums[i] && nums[i] > nums[i+1] {
			tree.update(i, val)
		}
	}
	for i := 1; i < n-1; i++ {
		update(i, 1)
	}
	for _, q := range queries {
		if q[0] == 1 {
			l, r := q[1]+1, q[2]-1
			t := 0
			if l <= r {
				t = tree.query(r) - tree.query(l-1)
			}
			ans = append(ans, t)
		} else {
			idx, val := q[1], q[2]
			for i := idx - 1; i <= idx+1; i++ {
				update(i, -1)
			}
			nums[idx] = val
			for i := idx - 1; i <= idx+1; i++ {
				update(i, 1)
			}
		}
	}
	return
}