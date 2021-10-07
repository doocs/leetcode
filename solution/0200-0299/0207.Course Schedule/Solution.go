func canFinish(numCourses int, prerequisites [][]int) bool {
	edges := make([][]int, numCourses)
	indegree := make([]int, numCourses)
	for _, p := range prerequisites {
		edges[p[1]] = append(edges[p[1]], p[0])
		indegree[p[0]]++
	}
	var q []int
	for i := 0; i < numCourses; i++ {
		if indegree[i] == 0 {
			q = append(q, i)
		}
	}
	cnt := 0
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		cnt++
		for _, j := range edges[i] {
			indegree[j]--
			if indegree[j] == 0 {
				q = append(q, j)
			}
		}
	}
	return cnt == numCourses
}