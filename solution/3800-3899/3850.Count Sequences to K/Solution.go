func countSequences(nums []int, k int64) int {
	n := len(nums)
	type state struct {
		i int
		p int64
		q int64
	}
	f := make(map[state]int)

	var gcd func(int64, int64) int64
	gcd = func(a, b int64) int64 {
		for b != 0 {
			a, b = b, a%b
		}
		return a
	}

	var dfs func(int, int64, int64) int
	dfs = func(i int, p int64, q int64) int {
		if i == n {
			if p == k && q == 1 {
				return 1
			}
			return 0
		}

		key := state{i, p, q}
		if v, ok := f[key]; ok {
			return v
		}

		res := dfs(i+1, p, q)

		x := int64(nums[i])

		g1 := gcd(p*x, q)
		res += dfs(i+1, (p*x)/g1, q/g1)

		g2 := gcd(p, q*x)
		res += dfs(i+1, p/g2, (q*x)/g2)

		f[key] = res
		return res
	}

	return dfs(0, 1, 1)
}
