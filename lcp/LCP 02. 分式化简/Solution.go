func fraction(cont []int) []int {
	var dfs func(int) []int
	dfs = func(i int) []int {
		if i == len(cont)-1 {
			return []int{cont[i], 1}
		}
		next := dfs(i + 1)
		a, b := next[0], next[1]
		x, y := a*cont[i]+b, a
		g := gcd(x, y)
		return []int{x / g, y / g}
	}
	return dfs(0)
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}