func similarRGB(color string) string {
	f := func(x string) string {
		q, _ := strconv.ParseInt(x, 16, 64)
		if q%17 > 8 {
			q = q/17 + 1
		} else {
			q = q / 17
		}
		return fmt.Sprintf("%02x", 17*q)

	}
	a, b, c := color[1:3], color[3:5], color[5:7]
	return "#" + f(a) + f(b) + f(c)
}