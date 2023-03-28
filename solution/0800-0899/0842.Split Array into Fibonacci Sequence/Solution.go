func splitIntoFibonacci(num string) []int {
	n := len(num)
	ans := []int{}
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i == n {
			return len(ans) > 2
		}
		x := 0
		for j := i; j < n; j++ {
			if j > i && num[i] == '0' {
				break
			}
			x = x*10 + int(num[j]-'0')
			if x > math.MaxInt32 || (len(ans) > 1 && x > ans[len(ans)-1]+ans[len(ans)-2]) {
				break
			}
			if len(ans) < 2 || x == ans[len(ans)-1]+ans[len(ans)-2] {
				ans = append(ans, x)
				if dfs(j + 1) {
					return true
				}
				ans = ans[:len(ans)-1]
			}
		}
		return false
	}
	dfs(0)
	return ans
}