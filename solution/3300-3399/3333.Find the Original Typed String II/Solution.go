func possibleStringCount(word string, k int) int {
	const mod = 1_000_000_007
	nums := []int{}
	ans := 1
	cur := 0
	n := len(word)

	for i := 0; i < n; i++ {
		cur++
		if i == n-1 || word[i] != word[i+1] {
			if cur > 1 {
				if k > 0 {
					nums = append(nums, cur-1)
				}
				ans = ans * cur % mod
			}
			cur = 0
			k--
		}
	}

	if k < 1 {
		return ans
	}

	m := len(nums)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, k)
	}
	f[0][0] = 1

	for i := 1; i <= m; i++ {
		x := nums[i-1]
		s := make([]int, k+1)
		for j := 0; j < k; j++ {
			s[j+1] = (s[j] + f[i-1][j]) % mod
		}
		for j := 0; j < k; j++ {
			l := j - x
			if l < 0 {
				l = 0
			}
			f[i][j] = (s[j+1] - s[l] + mod) % mod
		}
	}

	sum := 0
	for j := 0; j < k; j++ {
		sum = (sum + f[m][j]) % mod
	}

	return (ans - sum + mod) % mod
}
