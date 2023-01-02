func maximumUniqueSubarray(nums []int) (ans int) {
	d := [10001]int{}
	n := len(nums)
	s := make([]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	for i, j := 1, 0; i <= n; i++ {
		v := nums[i-1]
		j = max(j, d[v])
		ans = max(ans, s[i]-s[j])
		d[v] = i
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}