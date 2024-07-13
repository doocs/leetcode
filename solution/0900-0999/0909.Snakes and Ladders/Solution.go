func snakesAndLadders(board [][]int) int {
	n := len(board)
	q := []int{1}
	m := n * n
	vis := make([]bool, m+1)
	vis[1] = true

	for ans := 0; len(q) > 0; ans++ {
		for k := len(q); k > 0; k-- {
			x := q[0]
			q = q[1:]
			if x == m {
				return ans
			}
			for y := x + 1; y <= min(x+6, m); y++ {
				i, j := (y-1)/n, (y-1)%n
				if i%2 == 1 {
					j = n - j - 1
				}
				i = n - i - 1
				z := y
				if board[i][j] != -1 {
					z = board[i][j]
				}
				if !vis[z] {
					vis[z] = true
					q = append(q, z)
				}
			}
		}
	}
	return -1
}