func minimumDifference(nums []int) int {
	n := len(nums) >> 1
	f := make([][]int, n+1)
	g := make([][]int, n+1)
	for i := 0; i < (1 << n); i++ {
		s, cnt := 0, 0
		s1, cnt1 := 0, 0
		for j := 0; j < n; j++ {
			if (i & (1 << j)) != 0 {
				s += nums[j]
				cnt++
				s1 += nums[n+j]
				cnt1++
			} else {
				s -= nums[j]
				s1 -= nums[n+j]
			}
		}
		f[cnt] = append(f[cnt], s)
		g[cnt1] = append(g[cnt1], s1)
	}

	for i := 0; i <= n; i++ {
		sort.Ints(f[i])
		sort.Ints(g[i])
	}
	ans := 1 << 30
	for i := 0; i <= n; i++ {
		for _, a := range f[i] {
			left, right := 0, len(g[n-i])-1
			b := -a
			for left < right {
				mid := (left + right) >> 1
				if g[n-i][mid] >= b {
					right = mid
				} else {
					left = mid + 1
				}
			}
			ans = min(ans, abs(a+g[n-i][left]))
			if left > 0 {
				ans = min(ans, abs(a+g[n-i][left-1]))
			}
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}