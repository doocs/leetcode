func minSizeSubarray(nums []int, target int) int {
	s := 0
	for _, x := range nums {
		s += x
	}
	n := len(nums)
	a := 0
	if target > s {
		a = n * (target / s)
		target -= target / s * s
	}
	if target == s {
		return n
	}
	pos := map[int]int{0: -1}
	pre := 0
	b := 1 << 30
	for i, x := range nums {
		pre += x
		if j, ok := pos[pre-target]; ok {
			b = min(b, i-j)
		}
		if j, ok := pos[pre-(s-target)]; ok {
			b = min(b, n-(i-j))
		}
		pos[pre] = i
	}
	if b == 1<<30 {
		return -1
	}
	return a + b
}