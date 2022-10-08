func mostCompetitive(nums []int, k int) []int {
	stk := []int{}
	n := len(nums)
	for i, v := range nums {
		for len(stk) > 0 && stk[len(stk)-1] > v && len(stk)+n-i > k {
			stk = stk[:len(stk)-1]
		}
		if len(stk) < k {
			stk = append(stk, v)
		}
	}
	return stk
}