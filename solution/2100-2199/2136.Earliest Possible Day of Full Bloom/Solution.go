func earliestFullBloom(plantTime []int, growTime []int) (ans int) {
	n := len(plantTime)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return growTime[idx[j]] < growTime[idx[i]] })
	t := 0
	for _, i := range idx {
		t += plantTime[i]
		ans = max(ans, t+growTime[i])
	}
	return
}