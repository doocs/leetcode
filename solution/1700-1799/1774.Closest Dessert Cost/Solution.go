func closestCost(baseCosts []int, toppingCosts []int, target int) int {
	arr := []int{}
	var dfs func(int, int)
	dfs = func(i, t int) {
		if i >= len(toppingCosts) {
			arr = append(arr, t)
			return
		}
		dfs(i+1, t)
		dfs(i+1, t+toppingCosts[i])
	}
	dfs(0, 0)
	sort.Ints(arr)
	const inf = 1 << 30
	ans, d := inf, inf
	for _, x := range baseCosts {
		for _, y := range arr {
			i := sort.Search(len(arr), func(i int) bool { return arr[i] >= target-x-y })
			for j := i - 1; j < i+1; j++ {
				if j >= 0 && j < len(arr) {
					t := abs(x + y + arr[j] - target)
					if d > t || (d == t && ans > x+y+arr[j]) {
						d = t
						ans = x + y + arr[j]
					}
				}
			}
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}