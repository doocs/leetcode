func mincostTickets(days []int, costs []int) int {
	m := days[len(days)-1]
	f := make([]int, m+1)
	valid := [3]int{1, 7, 30}
	for i, j := 1, 0; i <= m; i++ {
		if i == days[j] {
			f[i] = 1 << 30
			for k, v := range valid {
				c := costs[k]
				f[i] = min(f[i], f[max(0, i-v)]+c)
			}
			j++
		} else {
			f[i] = f[i-1]
		}
	}
	return f[m]
}