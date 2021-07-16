func generate(numRows int) [][]int {
	res := make([][]int, numRows)
	for i := 0; i < numRows; i++ {
		t := make([]int, i+1)
		t[0] = 1
		t[i] = 1
		for j := 1; j < i; j++ {
			t[j] = res[i-1][j-1] + res[i-1][j]
		}
		res[i] = t
	}
	return res
}