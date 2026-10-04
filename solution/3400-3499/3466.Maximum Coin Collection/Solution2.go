func maxCoins(lane1 []int, lane2 []int) int64 {
	n := len(lane1)
	f := make([][2][3]int64, n+1)
	for i := n - 1; i >= 0; i-- {
		for k := 0; k < 3; k++ {
			for j := 0; j < 2; j++ {
				x := int64(lane1[i])
				if j == 1 {
					x = int64(lane2[i])
				}
				ans := max(x, f[i+1][j][k]+x)
				if k > 0 {
					ans = max(ans, f[i+1][j^1][k-1]+x)
					ans = max(ans, f[i][j^1][k-1])
				}
				f[i][j][k] = ans
			}
		}
	}
	ans := f[0][0][2]
	for i := 1; i < n; i++ {
		ans = max(ans, f[i][0][2])
	}
	return ans
}
