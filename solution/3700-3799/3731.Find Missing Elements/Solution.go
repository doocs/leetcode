func findMissingElements(nums []int) (ans []int) {
	mn, mx := 100, 0
	s := make(map[int]bool)
	for _, x := range nums {
        mn = min(mn, x)
        mx = max(mx, x)
		s[x] = true
	}
	for x := mn + 1; x < mx; x++ {
		if !s[x] {
			ans = append(ans, x)
		}
	}
	return
}
