func findTargetSumWays(nums []int, target int) int {
	s := 0
	for _, x := range nums {
		s += x
	}
	if s < target || (s-target)%2 != 0 {
		return 0
	}
	n := (s - target) / 2
	f := make([]int, n+1)
	f[0] = 1
	for _, x := range nums {
		for j := n; j >= x; j-- {
			f[j] += f[j-x]
		}
	}
	return f[n]
}