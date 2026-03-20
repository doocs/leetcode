func longestArithmetic(nums []int) int {
	n := len(nums)
	d := make([]int, n)
	for i := 1; i < n; i++ {
		d[i] = nums[i] - nums[i-1]
	}

	f := make([]int, n)
	g := make([]int, n)
	for i := range f {
		f[i], g[i] = 2, 2
	}
	f[0], g[n-1] = 1, 1

	for i := 2; i < n; i++ {
		if d[i] == d[i-1] {
			f[i] = f[i-1] + 1
		}
	}

	for i := n - 3; i >= 0; i-- {
		if d[i+1] == d[i+2] {
			g[i] = g[i+1] + 1
		}
	}

	ans := 3
	for i := 0; i < n; i++ {
		ans = max(ans, f[i], g[i])

		if i > 0 {
			ans = max(ans, f[i-1]+1)
		}
		if i+1 < n {
			ans = max(ans, g[i+1]+1)
		}

		if i > 0 && i < n-1 {
			diff := nums[i+1] - nums[i-1]
			if diff%2 == 0 {
				diff /= 2
				k := 3
				if i > 1 && diff == d[i-1] {
					k += f[i-1] - 1
				}
				if i < n-2 && diff == d[i+2] {
					k += g[i+1] - 1
				}
				ans = max(ans, k)
			}
		}
	}
	return ans
}
