func minFlips(a int, b int, c int) (ans int) {
	for i := 0; i < 30; i++ {
		x, y, z := a>>i&1, b>>i&1, c>>i&1
		if (x | y) != z {
			if x == 1 && y == 1 {
				ans += 2
			} else {
				ans++
			}
		}
	}
	return
}