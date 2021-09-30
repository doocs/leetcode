func eventualSafeNodes(graph [][]int) []int {
	n := len(graph)
	outDegree := make([]int, n)
	revGraph := make([][]int, n)
	queue := make([]int, 0)
	ans := make([]int, 0)

	for u, vs := range graph {
		for _, v := range vs {
			revGraph[v] = append(revGraph[v], u)
		}
		outDegree[u] = len(vs)
		if outDegree[u] == 0 {
			queue = append(queue, u)
		}
	}

	for len(queue) > 0 {
		v := queue[0]
		queue = queue[1:]
		for _, u := range revGraph[v] {
			outDegree[u]--
			if outDegree[u] == 0 {
				queue = append(queue, u)
			}
		}
	}

	for i, d := range outDegree {
		if d == 0 {
			ans = append(ans, i)
		}
	}
	return ans
}