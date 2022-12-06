func longestSubarray(nums []int, limit int) (ans int) {
	tm := treemap.NewWithIntComparator()
	j := 0
	for i, v := range nums {
		if x, ok := tm.Get(v); ok {
			tm.Put(v, x.(int)+1)
		} else {
			tm.Put(v, 1)
		}
		for {
			a, _ := tm.Min()
			b, _ := tm.Max()
			if b.(int)-a.(int) > limit {
				if x, _ := tm.Get(nums[j]); x.(int) == 1 {
					tm.Remove(nums[j])
				} else {
					tm.Put(nums[j], x.(int)-1)
				}
				j++
			} else {
				break
			}
		}
		ans = max(ans, i-j+1)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}