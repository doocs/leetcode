func numSpecial(mat [][]int) (ans int) {
	rows := make([]int, len(mat))
	cols := make([]int, len(mat[0]))
	for i, row := range mat {
		for j, x := range row {
			rows[i] += x
			cols[j] += x
		}
	}
	for i, row := range mat {
		for j, x := range row {
			if x == 1 && rows[i] == 1 && cols[j] == 1 {
				ans++
			}
		}
	}
	return
}
