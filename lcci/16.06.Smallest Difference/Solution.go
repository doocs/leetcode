func smallestDifference(a []int, b []int) int {
	sort.Ints(a)
	sort.Ints(b)
	i, j, res := 0, 0, 2147483647
	for i < len(a) && j < len(b) {
		res = min(res, abs(a[i]-b[j]))
		if a[i] > b[j] {
			j++
		} else {
			i++
		}
	}
	return res
}

func abs(a int) int {
	if a < 0 {
		return -a
	}
	return a
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}