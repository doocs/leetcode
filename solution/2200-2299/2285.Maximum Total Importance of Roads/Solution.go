func maximumImportance(n int, roads [][]int) (ans int64) {
	deg := make([]int, n)
	for _, r := range roads {
		deg[r[0]]++
		deg[r[1]]++
	}
	sort.Ints(deg)
	for i, x := range deg {
		ans += int64(x) * int64(i+1)
	}
	return
}