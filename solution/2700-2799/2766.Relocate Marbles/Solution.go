func relocateMarbles(nums []int, moveFrom []int, moveTo []int) (ans []int) {
	pos := map[int]bool{}
	for _, x := range nums {
		pos[x] = true
	}
	for i, f := range moveFrom {
		t := moveTo[i]
		pos[f] = false
		pos[t] = true
	}
	for x, ok := range pos {
		if ok {
			ans = append(ans, x)
		}
	}
	sort.Ints(ans)
	return
}