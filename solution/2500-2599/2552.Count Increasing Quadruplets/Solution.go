func countQuadruplets(nums []int) int64 {
	n := len(nums)
	f := make([][]int, n)
	g := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		g[i] = make([]int, n)
	}
	for j := 1; j < n-2; j++ {
		cnt := 0
		for l := j + 1; l < n; l++ {
			if nums[l] > nums[j] {
				cnt++
			}
		}
		for k := j + 1; k < n-1; k++ {
			if nums[j] > nums[k] {
				f[j][k] = cnt
			} else {
				cnt--
			}
		}
	}
	ans := 0
	for k := 2; k < n-1; k++ {
		cnt := 0
		for i := 0; i < k; i++ {
			if nums[i] < nums[k] {
				cnt++
			}
		}
		for j := k - 1; j > 0; j-- {
			if nums[j] > nums[k] {
				g[j][k] = cnt
				ans += f[j][k] * g[j][k]
			} else {
				cnt--
			}
		}
	}
	return int64(ans)
}