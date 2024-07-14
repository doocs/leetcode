type TicTacToe struct {
	n   int
	cnt [][]int
}

func Constructor(n int) TicTacToe {
	cnt := make([][]int, 2)
	for i := range cnt {
		cnt[i] = make([]int, (n<<1)+2)
	}
	return TicTacToe{n, cnt}
}

func (this *TicTacToe) Move(row int, col int, player int) int {
	cur := this.cnt[player-1]
	cur[row]++
	cur[this.n+col]++
	if row == col {
		cur[this.n<<1]++
	}
	if row+col == this.n-1 {
		cur[this.n<<1|1]++
	}
	if cur[row] == this.n || cur[this.n+col] == this.n || cur[this.n<<1] == this.n || cur[this.n<<1|1] == this.n {
		return player
	}
	return 0
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Move(row,col,player);
 */