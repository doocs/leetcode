func minimumCost(nums []int, k int, dist int) int64 {
	k--
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

	s := nums[0]
	for _, x := range nums[1 : dist+2] {
		s += x
		merge(l, x, 1)
	}
	size := dist + 1

	l2r := func() {
		x := l.Right().Key
		merge(l, x, -1)
		s -= x
		size--
		merge(r, x, 1)
	}
	r2l := func() {
		x := r.Left().Key
		merge(r, x, -1)
		merge(l, x, 1)
		s += x
		size++
	}

	for size > k {
		l2r()
	}

	ans := s
	for i := dist + 2; i < len(nums); i++ {
		x := nums[i-dist-1]
		if _, ok := l.Get(x); ok {
			merge(l, x, -1)
			s -= x
			size--
		} else {
			merge(r, x, -1)
		}
		y := nums[i]
		if y < l.Right().Key {
			merge(l, y, 1)
			s += y
			size++
		} else {
			merge(r, y, 1)
		}
		for size < k {
			r2l()
		}
		for size > k {
			l2r()
		}
		ans = min(ans, s)

	}
	return int64(ans)
}
