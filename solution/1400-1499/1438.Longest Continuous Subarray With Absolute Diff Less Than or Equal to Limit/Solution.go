func longestSubarray(nums []int, limit int) (ans int) {
	merge := func(st *redblacktree.Tree[int, int], x, v int) {
		c, _ := st.Get(x)
		if c+v == 0 {
			st.Remove(x)
		} else {
			st.Put(x, c+v)
		}
	}
	st := redblacktree.New[int, int]()
	j := 0
	for i, x := range nums {
		merge(st, x, 1)
		for ; st.Right().Key-st.Left().Key > limit; j++ {
			merge(st, nums[j], -1)
		}
		ans = max(ans, i-j+1)
	}
	return
}