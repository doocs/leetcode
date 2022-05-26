func sumOfBeauties(nums []int) int {
	n := len(nums)
	lmx := make([]int, n)
	rmi := make([]int, n)
	rmi[n-1] = 100001
	for i := 1; i < n; i++ {
		lmx[i] = max(lmx[i-1], nums[i-1])
	}
	for i := n - 2; i >= 0; i-- {
		rmi[i] = min(rmi[i+1], nums[i+1])
	}
	ans := 0
	for i := 1; i < n-1; i++ {
		if lmx[i] < nums[i] && nums[i] < rmi[i] {
			ans += 2
		} else if nums[i-1] < nums[i] && nums[i] < nums[i+1] {
			ans += 1
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}