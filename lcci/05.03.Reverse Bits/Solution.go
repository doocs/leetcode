func reverseBits(num int) (ans int) {
	var cnt, j int
	for i := 0; i < 32; i++ {
		cnt += num>>i&1 ^ 1
		for cnt > 1 {
			cnt -= num>>j&1 ^ 1
			j++
		}
		ans = max(ans, i-j+1)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}