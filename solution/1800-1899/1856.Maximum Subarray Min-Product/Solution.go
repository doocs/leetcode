func maxSumMinProduct(nums []int) int {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i] = -1
		right[i] = n
	}
	stk := []int{}
	for i, v := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] >= v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && nums[stk[len(stk)-1]] > nums[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	s := make([]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	ans := 0
	for i, v := range nums {
		t := v * (s[right[i]] - s[left[i]+1])
		if ans < t {
			ans = t
		}
	}
	mod := int(1e9) + 7
	return ans % mod
}