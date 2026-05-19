func powerUpdate(nums []int, p int, queries [][]int) []int {
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

	sz1, sz2 := 0, 0

	for _, x := range nums {
		merge(r, x, 1)
		sz2++

		if sz2 > queries[0][1] {
			node := r.Left()

			merge(r, node.Key, -1)
			sz2--

			merge(l, node.Key, 1)
			sz1++
		}
	}

	const mod int = 1e9 + 7

	qpow := func(a, b int) int {
		ans := 1

		for b > 0 {
			if b&1 == 1 {
				ans = ans * a % mod
			}

			a = a * a % mod
			b >>= 1
		}

		return ans
	}

	ans := make([]int, 0, len(queries))

	for _, q := range queries {
		val, k := q[0], q[1]

		merge(r, val, 1)
		sz2++

		node := r.Left()

		merge(r, node.Key, -1)
		sz2--

		merge(l, node.Key, 1)
		sz1++

		for sz2 < k {
			node = l.Right()

			merge(l, node.Key, -1)
			sz1--

			merge(r, node.Key, 1)
			sz2++
		}

		for sz2 > k {
			node = r.Left()

			merge(r, node.Key, -1)
			sz2--

			merge(l, node.Key, 1)
			sz1++
		}

		x := r.Left().Key

		p = qpow(p, x)

		ans = append(ans, p)
	}

	return ans
}