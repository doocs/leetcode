func concatHex36(n int) string {
	x := n * n
	y := n * n * n
	return f(x, 16) + f(y, 36)
}

func f(x, k int) string {
	res := []byte{}
	for x > 0 {
		v := x % k
		if v <= 9 {
			res = append(res, byte('0'+v))
		} else {
			res = append(res, byte('A'+v-10))
		}
		x /= k
	}
	for i, j := 0, len(res)-1; i < j; i, j = i+1, j-1 {
		res[i], res[j] = res[j], res[i]
	}
	return string(res)
}
