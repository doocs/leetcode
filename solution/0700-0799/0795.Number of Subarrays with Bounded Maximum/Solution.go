func numSubarrayBoundedMax(nums []int, left int, right int) (ans int) {
	n := len(nums)
	l := make([]int, n)
	r := make([]int, n)
	for i := range l {
		l[i], r[i] = -1, n
	}
	stk := []int{}
	for i, v := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] <= v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			l[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		v := nums[i]
		for len(stk) > 0 && nums[stk[len(stk)-1]] < v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			r[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	for i, v := range nums {
		if left <= v && v <= right {
			ans += (i - l[i]) * (r[i] - i)
		}
	}
	return
}