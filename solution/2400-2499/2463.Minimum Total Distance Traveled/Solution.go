func minimumTotalDistance(robot []int, factory [][]int) int64 {
	sort.Ints(robot)
	sort.Slice(factory, func(i, j int) bool { return factory[i][0] < factory[j][0] })
	f := make([][]int, len(robot))
	for i := range f {
		f[i] = make([]int, len(factory))
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i == len(robot) {
			return 0
		}
		if j == len(factory) {
			return 1e15
		}
		if f[i][j] != 0 {
			return f[i][j]
		}
		ans := dfs(i, j+1)
		t := 0
		for k := 0; k < factory[j][1]; k++ {
			if i+k >= len(robot) {
				break
			}
			t += abs(robot[i+k] - factory[j][0])
			ans = min(ans, t+dfs(i+k+1, j+1))
		}
		f[i][j] = ans
		return ans
	}
	return int64(dfs(0, 0))
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}