type SnakeGame struct {
	m     int
	n     int
	food  [][]int
	score int
	idx   int
	q     []int
	vis   map[int]bool
}

func Constructor(width int, height int, food [][]int) SnakeGame {
	return SnakeGame{height, width, food, 0, 0, []int{0}, map[int]bool{}}
}

func (this *SnakeGame) Move(direction string) int {
	f := func(i, j int) int {
		return i*this.n + j
	}
	p := this.q[0]
	i, j := p/this.n, p%this.n
	x, y := i, j
	if direction == "U" {
		x--
	} else if direction == "D" {
		x++
	} else if direction == "L" {
		y--
	} else {
		y++
	}
	if x < 0 || x >= this.m || y < 0 || y >= this.n {
		return -1
	}
	if this.idx < len(this.food) && x == this.food[this.idx][0] && y == this.food[this.idx][1] {
		this.score++
		this.idx++
	} else {
		t := this.q[len(this.q)-1]
		this.q = this.q[:len(this.q)-1]
		this.vis[t] = false
	}
	cur := f(x, y)
	if this.vis[cur] {
		return -1
	}
	this.q = append([]int{cur}, this.q...)
	this.vis[cur] = true
	return this.score
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * obj := Constructor(width, height, food);
 * param_1 := obj.Move(direction);
 */