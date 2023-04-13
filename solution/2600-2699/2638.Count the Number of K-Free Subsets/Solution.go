func countTheNumOfKFreeSubsets(nums []int, k int) int64 {
	sort.Ints(nums)
	g := map[int][]int{}
	for _, x := range nums {
		g[x%k] = append(g[x%k], x)
	}
	ans := int64(1)
	for _, arr := range g {
		m := len(arr)
		f := make([]int64, m+1)
		f[0] = 1
		f[1] = 2
		for i := 2; i <= m; i++ {
			if arr[i-1]-arr[i-2] == k {
				f[i] = f[i-1] + f[i-2]
			} else {
				f[i] = f[i-1] * 2
			}
		}
		ans *= f[m]
	}
	return ans
}