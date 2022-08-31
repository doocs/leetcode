func waysToSplit(nums []int) int {
	search := func(s []int, x, left, right int) int {
		for left < right {
			mid := (left + right) >> 1
			if s[mid] >= x {
				right = mid
			} else {
				left = mid + 1
			}
		}
		return left
	}
	var mod int = 1e9 + 7
	n := len(nums)
	s := make([]int, n)
	s[0] = nums[0]
	for i := 1; i < n; i++ {
		s[i] = s[i-1] + nums[i]
	}
	ans := 0
	for i := 0; i < n-2; i++ {
		j0 := search(s, s[i]*2, i+1, n-1)
		j1 := search(s, (s[i]+s[n-1])/2+1, j0, n-1)
		ans += j1 - j0
	}
	ans %= mod
	return ans
}