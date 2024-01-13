func subArrayRanges(nums []int) int64 {
	f := func(nums []int) int64 {
		stk := []int{}
		n := len(nums)
		left := make([]int, n)
		right := make([]int, n)
		for i := range left {
			left[i] = -1
			right[i] = n
		}
		for i, v := range nums {
			for len(stk) > 0 && nums[stk[len(stk)-1]] <= v {
				stk = stk[:len(stk)-1]
			}
			if len(stk) > 0 {
				left[i] = stk[len(stk)-1]
			}
			stk = append(stk, i)
		}
		stk = []int{}
		for i := n - 1; i >= 0; i-- {
			for len(stk) > 0 && nums[stk[len(stk)-1]] < nums[i] {
				stk = stk[:len(stk)-1]
			}
			if len(stk) > 0 {
				right[i] = stk[len(stk)-1]
			}
			stk = append(stk, i)
		}
		ans := 0
		for i, v := range nums {
			ans += (i - left[i]) * (right[i] - i) * v
		}
		return int64(ans)
	}
	mx := f(nums)
	for i := range nums {
		nums[i] *= -1
	}
	mi := f(nums)
	return mx + mi
}