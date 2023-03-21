func recoverArray(n int, sums []int) []int {
	m := 0
	for _, x := range sums {
		m = min(m, x)
	}
	m = -m
	rbt := redblacktree.NewWithIntComparator()
	merge := func(key int, value int) {
		if v, ok := rbt.Get(key); ok {
			nxt := v.(int) + value
			if nxt == 0 {
				rbt.Remove(key)
			} else {
				rbt.Put(key, nxt)
			}
		} else {
			rbt.Put(key, value)
		}
	}
	for _, x := range sums {
		merge(x+m, 1)
	}
	ans := make([]int, n)
	merge(ans[0], -1)
	ans[0] = rbt.Left().Key.(int)
	for i := 1; i < n; i++ {
		for j := 0; j < 1<<i; j++ {
			if j>>(i-1)&1 == 1 {
				s := 0
				for k := 0; k < i; k++ {
					if j>>k&1 == 1 {
						s += ans[k]
					}
				}
				merge(s, -1)
			}
		}
		ans[i] = rbt.Left().Key.(int)
	}
	for i := 0; i < 1<<n; i++ {
		s := 0
		for j := 0; j < n; j++ {
			if i>>j&1 == 1 {
				s += ans[j]
			}
		}
		if s == m {
			for j := 0; j < n; j++ {
				if i>>j&1 == 1 {
					ans[j] = -ans[j]
				}
			}
			break
		}
	}
	return ans

}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}