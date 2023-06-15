func binaryGap(n int) int {
	ans := 0
	for i, j := 0, -1; n != 0; i, n = i+1, n>>1 {
		if (n & 1) == 1 {
			if j != -1 && ans < i-j {
				ans = i - j
			}
			j = i
		}
	}
	return ans
}