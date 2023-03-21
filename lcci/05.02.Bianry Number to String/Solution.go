func printBin(num float64) string {
	ans := &strings.Builder{}
	ans.WriteString("0.")
	for ans.Len() < 32 && num != 0 {
		num *= 2
		x := byte(num)
		ans.WriteByte('0' + x)
		num -= float64(x)
	}
	if num != 0 {
		return "ERROR"
	}
	return ans.String()
}