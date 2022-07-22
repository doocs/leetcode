func maximumElementAfterDecrementingAndRearranging(arr []int) int {
	sort.Ints(arr)
	ans := 1
	arr[0] = 1
	for i := 1; i < len(arr); i++ {
		d := max(0, arr[i]-arr[i-1]-1)
		arr[i] -= d
		ans = max(ans, arr[i])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}