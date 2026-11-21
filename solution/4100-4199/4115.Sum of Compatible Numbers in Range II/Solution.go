func sumOfGoodIntegers(n int64, k int64) int {
	const MOD int64 = 1_000_000_007
	var num int64
	var memo [64][2][2]int64

	calc := func(x int64, m int) int64 {
		num = x
		for i := range memo {
			for j := range memo[i] {
				memo[i][j] = [2]int64{-1, -1}
			}
		}

		var dfs func(int, bool) (int64, int64)
		dfs = func(i int, lim bool) (int64, int64) {
			if i < 0 {
				return 1, 0
			}
			j := 0
			if lim {
				j = 1
			}
			if memo[i][j][0] != -1 {
				return memo[i][j][0], memo[i][j][1]
			}
			up := int64(1)
			if lim {
				up = num >> i & 1
			}
			var cnt, s int64
			for cur := int64(0); cur <= up; cur++ {
				if (n>>i&1) == 1 && cur == 1 {
					continue
				}
				cnt1, s1 := dfs(i-1, lim && cur == up)
				cnt = (cnt + cnt1) % MOD
				s = (s + s1 + cnt1*((1<<i)%MOD)%MOD*cur) % MOD
			}
			memo[i][j] = [2]int64{cnt, s}
			return cnt, s
		}

		_, s := dfs(m-1, true)
		return s
	}

	lo := max(int64(1), n-k)
	hi := n + k
	m := 64 - bits.LeadingZeros64(uint64(hi))
	right := calc(hi, m)
	left := calc(lo-1, m)
	return int((right - left + MOD) % MOD)
}
