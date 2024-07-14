func findOriginalArray(changed []int) (ans []int) {
	sort.Ints(changed)
	cnt := make([]int, changed[len(changed)-1]+1)
	for _, x := range changed {
		cnt[x]++
	}
	for _, x := range changed {
		if cnt[x] == 0 {
			continue
		}
		cnt[x]--
		y := x << 1
		if y >= len(cnt) || cnt[y] <= 0 {
			return []int{}
		}
		cnt[y]--
		ans = append(ans, x)
	}
	return
}