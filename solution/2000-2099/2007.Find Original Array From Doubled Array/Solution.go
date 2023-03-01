func findOriginalArray(changed []int) []int {
	n := len(changed)
	ans := []int{}
	if n&1 == 1 {
		return ans
	}
	sort.Ints(changed)
	cnt := make([]int, changed[n-1]+1)
	for _, x := range changed {
		cnt[x]++
	}
	for _, x := range changed {
		if cnt[x] == 0 {
			continue
		}
		if x*2 >= len(cnt) || cnt[x*2] <= 0 {
			return []int{}
		}
		ans = append(ans, x)
		cnt[x]--
		cnt[x*2]--
	}
	if len(ans) != n/2 {
		return []int{}
	}
	return ans
}