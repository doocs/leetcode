const MX = 50

var c [MX][MX + 1]int64

func init() {
	for i := 0; i < MX; i++ {
		c[i][0] = 1
		for j := 1; j <= i; j++ {
			c[i][j] = c[i-1][j-1] + c[i-1][j]
		}
	}
}

func nthSmallest(n int64, k int) int64 {
	var ans int64 = 0
	for i := 49; i >= 0; i-- {
		if n > c[i][k] {
			n -= c[i][k]
			ans |= 1 << i
			k--
			if k == 0 {
				break
			}
		}
	}
	return ans
}
