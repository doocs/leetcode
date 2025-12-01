func maxSum(nums []int, threshold []int) int64 {
	n := len(nums)
	idx := make([]int, n)
	for i := 0; i < n; i++ {
		idx[i] = i
	}
	sort.Slice(idx, func(a, b int) bool {
		return threshold[idx[a]] < threshold[idx[b]]
	})

	tree := redblacktree.NewWithIntComparator()
	var ans int64
	i := 0

	for step := 1; ; step++ {
		for i < n && threshold[idx[i]] <= step {
			val := nums[idx[i]]
			if cnt, found := tree.Get(val); found {
				tree.Put(val, cnt.(int)+1)
			} else {
				tree.Put(val, 1)
			}
			i++
		}
		if tree.Empty() {
			break
		}

		node := tree.Right()
		key := node.Key.(int)
		cnt := node.Value.(int)

		ans += int64(key)
		if cnt == 1 {
			tree.Remove(key)
		} else {
			tree.Put(key, cnt-1)
		}
	}

	return ans
}
