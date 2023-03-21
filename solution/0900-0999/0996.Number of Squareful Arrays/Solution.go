func numSquarefulPerms(nums []int) (ans int) {
	n := len(nums)
	f := make([][]int, 1<<n)
	for i := range f {
		f[i] = make([]int, n)
	}
	for j := range nums {
		f[1<<j][j] = 1
	}
	for i := 0; i < 1<<n; i++ {
		for j := 0; j < n; j++ {
			if i>>j&1 == 1 {
				for k := 0; k < n; k++ {
					if i>>k&1 == 1 && k != j {
						s := nums[j] + nums[k]
						t := int(math.Sqrt(float64(s)))
						if t*t == s {
							f[i][j] += f[i^(1<<j)][k]
						}
					}
				}
			}
		}
	}
	for j := 0; j < n; j++ {
		ans += f[(1<<n)-1][j]
	}
	g := [13]int{1}
	for i := 1; i < 13; i++ {
		g[i] = g[i-1] * i
	}
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for _, v := range cnt {
		ans /= g[v]
	}
	return
}