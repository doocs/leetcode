func goodDaysToRobBank(security []int, time int) []int {
	n := len(security)
	if n <= time*2 {
		return []int{}
	}
	left := make([]int, n)
	right := make([]int, n)
	for i := 1; i < n; i++ {
		if security[i] <= security[i-1] {
			left[i] = left[i-1] + 1
		}
	}
	for i := n - 2; i >= 0; i-- {
		if security[i] <= security[i+1] {
			right[i] = right[i+1] + 1
		}
	}
	var ans []int
	for i := time; i < n - time; i++ {
		if time <= left[i] && time <= right[i] {
			ans = append(ans, i)
		}
	}
	return ans
}