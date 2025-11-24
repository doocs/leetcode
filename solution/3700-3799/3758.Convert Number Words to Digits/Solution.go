func convertNumber(s string) string {
	d := []string{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"}
	n := len(s)
	var ans strings.Builder
	for i := 0; i < n; i++ {
		for j, t := range d {
			m := len(t)
			if i+m <= n && s[i:i+m] == t {
				ans.WriteString(strconv.Itoa(j))
				i += m - 1
				break
			}
		}
	}
	return ans.String()
}
