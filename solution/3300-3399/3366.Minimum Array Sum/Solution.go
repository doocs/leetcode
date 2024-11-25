func minArraySum(nums []int, d int, op1 int, op2 int) int {
	n := len(nums)
	const inf = int(1e9)
	f := make([][][]int, n+1)
	for i := range f {
		f[i] = make([][]int, op1+1)
		for j := range f[i] {
			f[i][j] = make([]int, op2+1)
			for k := range f[i][j] {
				f[i][j][k] = inf
			}
		}
	}
	f[0][0][0] = 0
	for i := 1; i <= n; i++ {
		x := nums[i-1]
		for j := 0; j <= op1; j++ {
			for k := 0; k <= op2; k++ {
				f[i][j][k] = f[i-1][j][k] + x
				if j > 0 {
					f[i][j][k] = min(f[i][j][k], f[i-1][j-1][k]+(x+1)/2)
				}
				if k > 0 && x >= d {
					f[i][j][k] = min(f[i][j][k], f[i-1][j][k-1]+(x-d))
				}
				if j > 0 && k > 0 {
					y := (x + 1) / 2
					if y >= d {
						f[i][j][k] = min(f[i][j][k], f[i-1][j-1][k-1]+(y-d))
					}
					if x >= d {
						f[i][j][k] = min(f[i][j][k], f[i-1][j-1][k-1]+(x-d+1)/2)
					}
				}
			}
		}
	}
	ans := inf
	for j := 0; j <= op1; j++ {
		for k := 0; k <= op2; k++ {
			ans = min(ans, f[n][j][k])
		}
	}
	return ans
}
