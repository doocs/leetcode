func minFlips(a int, b int, c int) int {
	ans := 0
	for i := 0; i < 31; i++ {
		x, y, z := (a>>i)&1, (b>>i)&1, (c>>i)&1
		if (x | y) == z {
			continue
		}
		if x == 1 && y == 1 && z == 0 {
			ans++
		}
		ans++
	}
	return ans
}