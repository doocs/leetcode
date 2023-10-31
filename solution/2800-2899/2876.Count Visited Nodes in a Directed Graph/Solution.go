func countVisitedNodes(edges []int) []int {
	n := len(edges)
	ans := make([]int, n)
	vis := make([]int, n)
	for i := range ans {
		if ans[i] == 0 {
			cnt, j := 0, i
			for vis[j] == 0 {
				cnt++
				vis[j] = cnt
				j = edges[j]
			}
			cycle, total := 0, cnt+ans[j]
			if ans[j] == 0 {
				cycle = cnt - vis[j] + 1
			}
			j = i
			for ans[j] == 0 {
				ans[j] = max(total, cycle)
				total--
				j = edges[j]
			}
		}
	}
	return ans
}