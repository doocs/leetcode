func reconstructQueue(people [][]int) [][]int {
	sort.Slice(people, func(i, j int) bool {
		a, b := people[i], people[j]
		return a[0] > b[0] || a[0] == b[0] && a[1] < b[1]
	})
	var ans [][]int
	for _, p := range people {
		i := p[1]
		ans = append(ans[:i], append([][]int{p}, ans[i:]...)...)
	}
	return ans
}