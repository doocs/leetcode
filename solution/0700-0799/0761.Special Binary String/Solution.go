func makeLargestSpecial(s string) string {
	if s == "" {
		return ""
	}
	ans := sort.StringSlice{}
	cnt := 0
	for i, j := 0, 0; i < len(s); i++ {
		if s[i] == '1' {
			cnt++
		} else {
			cnt--
		}
		if cnt == 0 {
			ans = append(ans, "1"+makeLargestSpecial(s[j+1:i])+"0")
			j = i + 1
		}
	}
	sort.Sort(sort.Reverse(ans))
	return strings.Join(ans, "")
}