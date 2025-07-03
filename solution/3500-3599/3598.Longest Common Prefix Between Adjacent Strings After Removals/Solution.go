func longestCommonPrefix(words []string) []int {
	n := len(words)
	tm := treemap.NewWithIntComparator()

	calc := func(s, t string) int {
		m := min(len(s), len(t))
		for k := 0; k < m; k++ {
			if s[k] != t[k] {
				return k
			}
		}
		return m
	}

	add := func(i, j int) {
		if i >= 0 && i < n && j >= 0 && j < n {
			x := calc(words[i], words[j])
			if v, ok := tm.Get(x); ok {
				tm.Put(x, v.(int)+1)
			} else {
				tm.Put(x, 1)
			}
		}
	}

	remove := func(i, j int) {
		if i >= 0 && i < n && j >= 0 && j < n {
			x := calc(words[i], words[j])
			if v, ok := tm.Get(x); ok {
				if v.(int) == 1 {
					tm.Remove(x)
				} else {
					tm.Put(x, v.(int)-1)
				}
			}
		}
	}

	for i := 0; i+1 < n; i++ {
		add(i, i+1)
	}

	ans := make([]int, n)
	for i := 0; i < n; i++ {
		remove(i, i+1)
		remove(i-1, i)
		add(i-1, i+1)

		if !tm.Empty() {
			if maxKey, _ := tm.Max(); maxKey.(int) > 0 {
				ans[i] = maxKey.(int)
			}
		}

		remove(i-1, i+1)
		add(i-1, i)
		add(i, i+1)
	}

	return ans
}