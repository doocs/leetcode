func medianSlidingWindow(nums []int, k int) (ans []float64) {
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
	lSize, rSize := 0, 0
	for i, x := range nums {
		merge(r, x, 1)
		x = r.Left().Key
		merge(r, x, -1)
		merge(l, x, 1)
		lSize++
		for lSize-rSize > 1 {
			x = l.Right().Key
			merge(l, x, -1)
			merge(r, x, 1)
			lSize--
			rSize++
		}
		if j := i - k + 1; j >= 0 {
			if k%2 == 1 {
				ans = append(ans, float64(l.Right().Key))
			} else {
				ans = append(ans, float64(l.Right().Key+r.Left().Key)/2)
			}
			if x = nums[j]; x <= l.Right().Key {
				merge(l, x, -1)
				lSize--
			} else {
				merge(r, x, -1)
				rSize--
			}
		}
	}
	return
}
