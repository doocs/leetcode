func countConsistent(nums []int) int64 {
	n := len(nums)
	left := make([]int, n)
	for i := range left {
		left[i] = -1
	}
	stk := []int{}
	for i, x := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]]&x == x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}

	stk = []int{}
	var ans int64
	for i := n - 1; i >= 0; i-- {
		x := nums[i]
		for len(stk) > 0 && nums[stk[len(stk)-1]] != x && nums[stk[len(stk)-1]]&x == x {
			stk = stk[:len(stk)-1]
		}
		right := n
		if len(stk) > 0 {
			right = stk[len(stk)-1]
		}
		stk = append(stk, i)
		ans += int64(i-left[i]) * int64(right-i)
	}
	return ans
}
