func findLHS(nums []int) int {
	counter := make(map[int]int)
	for _, num := range nums {
		counter[num]++
	}
	res := 0
	for _, num := range nums {
		if counter[num+1] > 0 {
			res = max(res, counter[num]+counter[num+1])
		}
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}