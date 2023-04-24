func sortPeople(names []string, heights []int) (ans []string) {
	n := len(names)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return heights[idx[j]] < heights[idx[i]] })
	for _, i := range idx {
		ans = append(ans, names[i])
	}
	return
}