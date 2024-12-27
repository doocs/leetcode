func longestConsecutive(nums []int) (ans int) {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	for x, _ := range s {
		if !s[x-1] {
			y := x + 1
			for s[y] {
				y++
			}
			ans = max(ans, y-x)
		}
	}
	return
}
