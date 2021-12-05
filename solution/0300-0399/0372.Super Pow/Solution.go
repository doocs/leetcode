const mod = 1337

func superPow(a int, b []int) int {
	ans := 1
	for i := len(b) - 1; i >= 0; i-- {
		ans = ans * quickPowAndMod(a, b[i]) % mod
		a = quickPowAndMod(a, 10)
	}
	return ans
}

func quickPowAndMod(a, b int) int {
	ans := 1
	for b > 0 {
		if b&1 > 0 {
			ans = ans * a % mod
		}
		b >>= 1
		a = ((a % mod) * (a % mod)) % mod
	}
	return ans
}