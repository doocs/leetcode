func sequenceReconstruction(org []int, seqs [][]int) bool {
	n := len(org)
	nums := make(map[int]bool)
	for _, seq := range seqs {
		for _, num := range seq {
			if num < 1 || num > n {
				return false
			}
			nums[num] = true
		}
	}
	if len(nums) < n {
		return false
	}
	edges := make([][]int, n+1)
	indegree := make([]int, n+1)
	for _, seq := range seqs {
		i := seq[0]
		for _, j := range seq[1:] {
			edges[i] = append(edges[i], j)
			indegree[j]++
			i = j
		}
	}
	var q []int
	for i := 1; i <= n; i++ {
		if indegree[i] == 0 {
			q = append(q, i)
		}
	}
	cnt := 0
	for len(q) > 0 {
		if len(q) > 1 || org[cnt] != q[0] {
			return false
		}
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
	return cnt == n
}