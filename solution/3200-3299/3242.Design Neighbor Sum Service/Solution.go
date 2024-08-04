type neighborSum struct {
	grid [][]int
	d    map[int][2]int
	dirs [2][5]int
}

func Constructor(grid [][]int) neighborSum {
	d := map[int][2]int{}
	for i, row := range grid {
		for j, x := range row {
			d[x] = [2]int{i, j}
		}
	}
	dirs := [2][5]int{{-1, 0, 1, 0, -1}, {-1, 1, 1, -1, -1}}
	return neighborSum{grid, d, dirs}
}

func (this *neighborSum) AdjacentSum(value int) int {
	return this.cal(value, 0)
}

func (this *neighborSum) DiagonalSum(value int) int {
	return this.cal(value, 1)
}

func (this *neighborSum) cal(value, k int) int {
	p := this.d[value]
	s := 0
	for q := 0; q < 4; q++ {
		x, y := p[0]+this.dirs[k][q], p[1]+this.dirs[k][q+1]
		if x >= 0 && x < len(this.grid) && y >= 0 && y < len(this.grid[0]) {
			s += this.grid[x][y]
		}
	}
	return s
}

/**
 * Your neighborSum object will be instantiated and called as such:
 * obj := Constructor(grid);
 * param_1 := obj.AdjacentSum(value);
 * param_2 := obj.DiagonalSum(value);
 */
