func largestDivisibleSubset(nums []int) (ans []int) {
	sort.Ints(nums)
	n := len(nums)
	f := make([]int, n)
	k := 0
	for i := 0; i < n; i++ {
		f[i] = 1
		for j := 0; j < i; j++ {
			if nums[i]%nums[j] == 0 {
				f[i] = max(f[i], f[j]+1)
			}
		}
		if f[k] < f[i] {
			k = i
		}
	}
	m := f[k]
	for i := k; m > 0; i-- {
		if nums[k]%nums[i] == 0 && f[i] == m {
			ans = append(ans, nums[i])
			k = i
			m--
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}