func diagonalSum(mat [][]int) (ans int) {
	n := len(mat)
	for i, row := range mat {
		ans += row[i]
		if j := n - i - 1; j != i {
			ans += row[j]
		}
	}
	return
}