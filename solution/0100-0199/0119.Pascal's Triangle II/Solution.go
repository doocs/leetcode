func getRow(rowIndex int) []int {
	f := make([]int, rowIndex+1)
	for i := range f {
		f[i] = 1
	}
	for i := 2; i < rowIndex+1; i++ {
		for j := i - 1; j > 0; j-- {
			f[j] += f[j-1]
		}
	}
	return f
}