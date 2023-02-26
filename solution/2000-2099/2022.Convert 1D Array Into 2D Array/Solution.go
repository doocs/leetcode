func construct2DArray(original []int, m int, n int) (ans [][]int) {
	if m*n != len(original) {
		return [][]int{}
	}
	for i := 0; i < m*n; i += n {
		ans = append(ans, original[i:i+n])
	}
	return
}