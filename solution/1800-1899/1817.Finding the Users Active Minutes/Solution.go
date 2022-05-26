func findingUsersActiveMinutes(logs [][]int, k int) []int {
	d := map[int]map[int]bool{}
	for _, e := range logs {
		u, t := e[0], e[1]
		if _, ok := d[u]; !ok {
			d[u] = make(map[int]bool)
		}
		d[u][t] = true
	}
	ans := make([]int, k)
	for _, ts := range d {
		ans[len(ts)-1]++
	}
	return ans
}