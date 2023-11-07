func leastOpsExpressTarget(x int, target int) int {
	f := map[int]int{}
	var dfs func(int) int
	dfs = func(v int) int {
		if x > v {
			return min(v*2-1, 2*(x-v))
		}
		if val, ok := f[v]; ok {
			return val
		}
		k := 2
		y := x * x
		for y < v {
			y *= x
			k++
		}
		ans := k - 1 + dfs(v-y/x)
		if y-v < v {
			ans = min(ans, k+dfs(y-v))
		}
		f[v] = ans
		return ans
	}
	return dfs(target)
}