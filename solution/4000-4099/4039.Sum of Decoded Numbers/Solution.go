func sumDecoded(nums []int64) int {
	const mod int64 = 1000000007
	var ans int64

	for _, v := range nums {
		d, w := v/10, int(v%10)
		s := strconv.FormatInt(d, 10)

		x, _ := strconv.ParseInt(s[:w], 10, 64)
		y, _ := strconv.ParseInt(s[w:], 10, 64)

		ans = (ans + pow(x, y, mod)) % mod
	}

	return int(ans)
}

func pow(x, y, mod int64) int64 {
	res := int64(1)
	for y > 0 {
		if y&1 != 0 {
			res = res * x % mod
		}
		x = x * x % mod
		y >>= 1
	}
	return res
}
