func snakesAndLadders(board [][]int) int {
	n := len(board)
	get := func(x int) []int {
		i, j := (x-1)/n, (x-1)%n
		if i%2 == 1 {
			j = n - 1 - j
		}
		return []int{n - 1 - i, j}
	}
	q := []int{1}
	vis := make([]bool, n*n+1)
	vis[1] = true
	ans := 0
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			curr := q[0]
			if curr == n*n {
				return ans
			}
			q = q[1:]
			for k := curr + 1; k <= curr+6 && k <= n*n; k++ {
				p := get(k)
				next := k
				i, j := p[0], p[1]
				if board[i][j] != -1 {
					next = board[i][j]
				}
				if !vis[next] {
					vis[next] = true
					q = append(q, next)
				}
			}
		}
		ans++
	}
	return -1
}