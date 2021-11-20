func findLHS(nums []int) int {
	counter := make(map[int]int)
	for _, num := range nums {
		counter[num]++
	}
	ans := 0
	for _, num := range nums {
		if counter[num+1] > 0 {
			ans = max(ans, counter[num]+counter[num+1])
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