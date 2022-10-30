func destroyTargets(nums []int, space int) int {
	cnt := map[int]int{}
	for _, v := range nums {
		cnt[v%space]++
	}
	ans, mx := 0, 0
	for _, v := range nums {
		t := cnt[v%space]
		if t > mx || (t == mx && v < ans) {
			ans = v
			mx = t
		}
	}
	return ans
}