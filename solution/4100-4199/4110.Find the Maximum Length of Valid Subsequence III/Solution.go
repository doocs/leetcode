func maximumLength(nums []int, k int) int {
	n := len(nums)
	f := make([]int, n)
	for i := range f {
		f[i] = 1
	}
	last := map[int]int{}
	ans := 1
	for i, v := range nums {
		x := v % k
		y := (k - x) % k
		if j, ok := last[y]; ok {
			f[i] = max(f[i], f[j]+1)
			ans = max(ans, f[i])
		}
		last[x] = i
	}
	return ans
}
