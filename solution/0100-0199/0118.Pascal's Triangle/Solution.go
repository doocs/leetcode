func generate(numRows int) [][]int {
	ans := make([][]int, numRows)
	for i := range ans {
		t := make([]int, i+1)
		t[0], t[i] = 1, 1
		for j := 1; j < i; j++ {
			t[j] = ans[i-1][j] + ans[i-1][j-1]
		}
		ans[i] = t
	}
	return ans
}