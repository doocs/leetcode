func maximumLength(nums []int, k int) (ans int) {
	f := make([][]int, len(nums))
	for i := range f {
		f[i] = make([]int, k+1)
	}
	for i, x := range nums {
		for h := 0; h <= k; h++ {
			for j, y := range nums[:i] {
				if x == y {
					f[i][h] = max(f[i][h], f[j][h])
				} else if h > 0 {
					f[i][h] = max(f[i][h], f[j][h-1])
				}
			}
			f[i][h]++
		}
		ans = max(ans, f[i][k])
	}
	return
}