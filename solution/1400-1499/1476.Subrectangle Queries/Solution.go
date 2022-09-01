type SubrectangleQueries struct {
	g   [][]int
	ops [][]int
}

func Constructor(rectangle [][]int) SubrectangleQueries {
	return SubrectangleQueries{rectangle, [][]int{}}
}

func (this *SubrectangleQueries) UpdateSubrectangle(row1 int, col1 int, row2 int, col2 int, newValue int) {
	this.ops = append(this.ops, []int{row1, col1, row2, col2, newValue})
}

func (this *SubrectangleQueries) GetValue(row int, col int) int {
	for i := len(this.ops) - 1; i >= 0; i-- {
		op := this.ops[i]
		if op[0] <= row && row <= op[2] && op[1] <= col && col <= op[3] {
			return op[4]
		}
	}
	return this.g[row][col]
}

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * obj := Constructor(rectangle);
 * obj.UpdateSubrectangle(row1,col1,row2,col2,newValue);
 * param_2 := obj.GetValue(row,col);
 */