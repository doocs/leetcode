func findOrder(numCourses int, prerequisites [][]int) []int {
	edges := make([][]int, numCourses)
	indegree := make([]int, numCourses)
	for _, p := range prerequisites {
		a, b := p[0], p[1]
		edges[b] = append(edges[b], a)
		indegree[a]++
	}
	var q []int
	for i := 0; i < numCourses; i++ {
		if indegree[i] == 0 {
			q = append(q, i)
		}
	}
	var ans []int
	for len(q) > 0 {
		b := q[0]
		q = q[1:]
		ans = append(ans, b)
		for _, a := range edges[b] {
			indegree[a]--
			if indegree[a] == 0 {
				q = append(q, a)
			}
		}
	}
	if len(ans) == numCourses {
		return ans
	}
	return []int{}
}