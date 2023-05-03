func shortestPathLength(graph [][]int) int {
	n := len(graph)
	q := [][2]int{}
	vis := make([][]bool, n)
	for i := range vis {
		vis[i] = make([]bool, 1<<n)
		vis[i][1<<i] = true
		q = append(q, [2]int{i, 1 << i})
	}
	for ans := 0; ; ans++ {
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			i, st := p[0], p[1]
			if st == (1<<n)-1 {
				return ans
			}
			for _, j := range graph[i] {
				nst := st | 1<<j
				if !vis[j][nst] {
					vis[j][nst] = true
					q = append(q, [2]int{j, nst})
				}
			}
		}
	}
}