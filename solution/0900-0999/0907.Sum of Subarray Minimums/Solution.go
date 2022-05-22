func sumSubarrayMins(arr []int) int {
	mod := int(1e9) + 7
	n := len(arr)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i] = -1
		right[i] = n
	}
	stk := []int{}
	for i, v := range arr {
		for len(stk) > 0 && arr[stk[len(stk)-1]] >= v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && arr[stk[len(stk)-1]] > arr[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	ans := 0
	for i, v := range arr {
		ans += (i - left[i]) * (right[i] - i) * v % mod
		ans %= mod
	}
	return ans
}