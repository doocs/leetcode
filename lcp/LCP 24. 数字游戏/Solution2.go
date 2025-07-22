func numsGame(nums []int) (ans []int) {
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
	s, t := 0, 0
	lSize, rSize := 0, 0
	const mod int = 1e9 + 7
	for i, x := range nums {
		x -= i
		merge(r, x, 1)
		t += x
		x = r.Left().Key
		merge(r, x, -1)
		t -= x
		merge(l, x, 1)
		s += x
		lSize++
		for lSize-rSize > 1 {
			x = l.Right().Key
			merge(l, x, -1)
			s -= x
			lSize--
			merge(r, x, 1)
			t += x
			rSize++
		}
		mid := l.Right().Key
		v := (mid*lSize%mod - s + t - mid*rSize%mod) % mod
		ans = append(ans, v)
	}
	return
}
