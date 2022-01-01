func minOperations(nums []int, x int) int {
	x = -x
	for _, v := range nums {
		x += v
	}
	s, n := 0, len(nums)
	seen := map[int]int{0: -1}
	ans := math.MaxInt32
	for i, v := range nums {
		s += v
		if _, ok := seen[s]; !ok {
			seen[s] = i
		}
		if j, ok := seen[s-x]; ok {
			ans = min(ans, n-(i-j))
		}
	}
	if ans == math.MaxInt32 {
		return -1
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}