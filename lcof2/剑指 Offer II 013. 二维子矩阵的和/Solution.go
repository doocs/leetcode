type NumMatrix struct {
	pre [][]int
}

func Constructor(matrix [][]int) NumMatrix {
	m, n := len(matrix), len(matrix[0])
	pre := make([][]int, m+1)
	for i := 0; i < m+1; i++ {
		pre[i] = make([]int, n+1)
	}
	for i := 1; i < m+1; i++ {
		for j := 1; j < n+1; j++ {
			pre[i][j] = pre[i-1][j] + pre[i][j-1] + -pre[i-1][j-1] + matrix[i-1][j-1]
		}
	}
	return NumMatrix{pre}
}

func (this *NumMatrix) SumRegion(row1 int, col1 int, row2 int, col2 int) int {
	return this.pre[row2+1][col2+1] - this.pre[row2+1][col1] - this.pre[row1][col2+1] + this.pre[row1][col1]
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * obj := Constructor(matrix);
 * param_1 := obj.SumRegion(row1,col1,row2,col2);
 */