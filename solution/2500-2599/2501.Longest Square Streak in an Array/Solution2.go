func longestSquareStreak(nums []int) (ans int) {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	f := map[int]int{}
	var dfs func(int) int
	dfs = func(x int) int {
		if !s[x] {
			return 0
		}
		if v, ok := f[x]; ok {
			return v
		}
		f[x] = 1 + dfs(x*x)
		return f[x]
	}
	for x := range s {
		if t := dfs(x); ans < t {
			ans = t
		}
	}
	if ans < 2 {
		return -1
	}
	return ans
}