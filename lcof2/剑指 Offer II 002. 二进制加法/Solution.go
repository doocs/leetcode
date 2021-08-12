func addBinary(a string, b string) string {
	x, y := len(a)-1, len(b)-1
	var builder strings.Builder
	carry := 0
	for x >= 0 || y >= 0 {
		if x >= 0 {
			if a[x] == '1' {
				carry += 1
			}
			x--
		}
		if y >= 0 {
			if b[y] == '1' {
				carry += 1
			}
			y--
		}
		builder.WriteRune(rune(carry&1 + '0'))
		carry >>= 1
	}
	if carry == 1 {
		builder.WriteRune('1')
	}
	bytes := []byte(builder.String())
	for i, j := 0, len(bytes)-1; i < j; i, j = i+1, j-1 {
		bytes[i], bytes[j] = bytes[j], bytes[i]
	}
	return string(bytes)
}
