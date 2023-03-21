func findValidSplit(nums []int) int {
	first := map[int]int{}
	n := len(nums)
	last := make([]int, n)
	for i := range last {
		last[i] = i
	}
	for i, x := range nums {
		for j := 2; j <= x/j; j++ {
			if x%j == 0 {
				if k, ok := first[j]; ok {
					last[k] = i
				} else {
					first[j] = i
				}
				for x%j == 0 {
					x /= j
				}
			}
		}
		if x > 1 {
			if k, ok := first[x]; ok {
				last[k] = i
			} else {
				first[x] = i
			}
		}
	}
	mx := last[0]
	for i, x := range last {
		if mx < i {
			return mx
		}
		mx = max(mx, x)
	}
	return -1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}