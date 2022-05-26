func longestConsecutive(nums []int) int {
	s := make(map[int]bool)
	for _, num := range nums {
		s[num] = true
	}
	res := 0
	for _, num := range nums {
		if !s[num-1] {
			t, next := 1, num+1
			for s[next] {
				next++
				t++
			}
			res = max(res, t)
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