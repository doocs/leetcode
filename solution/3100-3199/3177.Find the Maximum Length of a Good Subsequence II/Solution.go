func maximumLength(nums []int, k int) int {
	n := len(nums)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	mp := make([]map[int]int, k+1)
	for i := range mp {
		mp[i] = make(map[int]int)
	}
	g := make([][3]int, k+1)
	ans := 0

	for i := 0; i < n; i++ {
		for h := 0; h <= k; h++ {
			f[i][h] = mp[h][nums[i]]
			if h > 0 {
				if g[h-1][0] != nums[i] {
					if g[h-1][1] > f[i][h] {
						f[i][h] = g[h-1][1]
					}
				} else {
					if g[h-1][2] > f[i][h] {
						f[i][h] = g[h-1][2]
					}
				}
			}
			f[i][h]++
			if f[i][h] > mp[h][nums[i]] {
				mp[h][nums[i]] = f[i][h]
			}
			if g[h][0] != nums[i] {
				if f[i][h] >= g[h][1] {
					g[h][2] = g[h][1]
					g[h][1] = f[i][h]
					g[h][0] = nums[i]
				} else if f[i][h] > g[h][2] {
					g[h][2] = f[i][h]
				}
			} else {
				if f[i][h] > g[h][1] {
					g[h][1] = f[i][h]
				}
			}
			if f[i][h] > ans {
				ans = f[i][h]
			}
		}
	}

	return ans
}