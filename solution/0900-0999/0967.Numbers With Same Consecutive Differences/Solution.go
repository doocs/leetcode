func numsSameConsecDiff(n int, k int) (ans []int) {
	bounary := int(math.Pow10(n - 1))
	var dfs func(int)
	dfs = func(x int) {
		if x >= bounary {
			ans = append(ans, x)
			return
		}
		last := x % 10
		if last+k < 10 {
			dfs(x*10 + last + k)
		}
		if k > 0 && last-k >= 0 {
			dfs(x*10 + last - k)
		}
	}
	for i := 1; i < 10; i++ {
		dfs(i)
	}
	return
}
