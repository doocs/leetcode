func resultArray(nums []int, k int) []int64 {
	ans := make([]int64, k)
	f := make([]int64, k)
	for _, x := range nums {
		g := make([]int64, k)
		for r, cnt := range f {
			g[r*x%k] += cnt
		}
		g[x%k]++
		for r, cnt := range g {
			ans[r] += cnt
		}
		f = g
	}
	return ans
}
