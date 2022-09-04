func maximumRows(mat [][]int, cols int) int {
	m, n := len(mat), len(mat[0])
	arr := make([]int, m)
	for i, row := range mat {
		x := 0
		for j, v := range row {
			x |= v << j
		}
		arr[i] = x
	}
	ans := 0
	for mask := 1; mask <= 1<<n; mask++ {
		if bits.OnesCount(uint(mask)) != cols {
			continue
		}
		t := 0
		for _, v := range arr {
			if (v & mask) == v {
				t++
			}
		}
		ans = max(ans, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}