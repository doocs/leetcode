func earliestFullBloom(plantTime []int, growTime []int) (ans int) {
	n := len(plantTime)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return growTime[idx[j]] < growTime[idx[i]] })
	t := 0
	for _, i := range idx {
		pt, gt := plantTime[i], growTime[i]
		t += pt
		ans = max(ans, t+gt)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}