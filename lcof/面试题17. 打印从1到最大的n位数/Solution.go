func printNumbers(n int) []int {
	n = int(math.Pow(10, float64(n))) - 1
	ans := make([]int, n)
	for i := range ans {
		ans[i] = i + 1
	}
	return ans
}

func print(n int) []string {
	var dfs func(i, j int)
	s := []byte{}
	ans := []string{}
	dfs = func(i, j int) {
		if i == j {
			ans = append(ans, string(s))
			return
		}
		k := 0
		if i == 0 {
			k++
		}
		for k < 10 {
			s = append(s, byte('0'+k))
			dfs(i+1, j)
			s = s[:len(s)-1]
			k++
		}
	}
	for i := 1; i <= n; i++ {
		dfs(0, i)
	}
	return ans
}