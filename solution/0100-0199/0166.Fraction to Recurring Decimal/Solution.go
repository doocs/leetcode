func fractionToDecimal(numerator int, denominator int) string {
	if numerator == 0 {
		return "0"
	}
	res := []byte{}
	neg := numerator*denominator < 0
	if neg {
		res = append(res, '-')
	}
	num := abs(numerator)
	d := abs(denominator)
	res = append(res, strconv.Itoa(num/d)...)
	num %= d
	if num == 0 {
		return string(res)
	}
	mp := make(map[int]int)
	res = append(res, '.')
	for num != 0 {
		mp[num] = len(res)
		num *= 10
		res = append(res, strconv.Itoa(num/d)...)
		num %= d
		if mp[num] > 0 {
			idx := mp[num]
			res = append(res[:idx], append([]byte{'('}, res[idx:]...)...)
			res = append(res, ')')
			break
		}
	}

	return string(res)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}