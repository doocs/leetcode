func minOperations(nums []int, k int) int64 {
	l := redblacktree.New[int, int]()
	r := redblacktree.New[int, int]()
	merge := func(st *redblacktree.Tree[int, int], x, v int) {
		c, _ := st.Get(x)
		if c+v == 0 {
			st.Remove(x)
		} else {
			st.Put(x, c+v)
		}
	}
	var s1, s2, sz1, sz2 int
	ans := math.MaxInt64
	for i, x := range nums {
		merge(l, x, 1)
		s1 += x
		y := l.Right().Key
		merge(l, y, -1)
		s1 -= y
		merge(r, y, 1)
		s2 += y
		sz2++
		if sz2-sz1 > 1 {
			y = r.Left().Key
			merge(r, y, -1)
			s2 -= y
			sz2--
			merge(l, y, 1)
			s1 += y
			sz1++
		}
		if j := i - k + 1; j >= 0 {
			ans = min(ans, s2-r.Left().Key*sz2+r.Left().Key*sz1-s1)
			if _, ok := r.Get(nums[j]); ok {
				merge(r, nums[j], -1)
				s2 -= nums[j]
				sz2--
			} else {
				merge(l, nums[j], -1)
				s1 -= nums[j]
				sz1--
			}
		}
	}
	return int64(ans)
}
