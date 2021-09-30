func findMaxLength(nums []int) int {
	m := map[int]int{0: -1}
	ans, sum := 0, 0
	for i, num := range nums {
		if num == 0 {
			sum -= 1
		} else {
			sum += 1
		}
		if j, ok := m[sum]; ok {
			ans = max(ans, i-j)
		} else {
			m[sum] = i
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
