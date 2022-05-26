func construct2DArray(original []int, m int, n int) [][]int {
	if m*n != len(original) {
		return [][]int{}
	}
	var ans [][]int
	for i := 0; i < m*n; i += n {
		ans = append(ans, original[i:i+n])
	}
	return ans
}