func meetRequirement(n int, lights [][]int, requirement []int) int {
	d := make([]int, 100010)
	for _, e := range lights {
		i, j := max(0, e[0]-e[1]), min(n-1, e[0]+e[1])
		d[i]++
		d[j+1]--
	}
	var s, ans int
	for i, r := range requirement {
		s += d[i]
		if s >= r {
			ans++
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