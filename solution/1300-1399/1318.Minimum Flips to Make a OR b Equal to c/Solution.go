func minFlips(a int, b int, c int) (ans int) {
	for i := 0; i < 32; i++ {
		x, y, z := a>>i&1, b>>i&1, c>>i&1
		if z == 0 {
			ans += x + y
		} else if x == 0 && y == 0 {
			ans++
		}
	}
	return
}