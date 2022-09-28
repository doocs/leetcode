func subdomainVisits(cpdomains []string) []string {
	cnt := map[string]int{}
	for _, s := range cpdomains {
		i := strings.IndexByte(s, ' ')
		v, _ := strconv.Atoi(s[:i])
		for ; i < len(s); i++ {
			if s[i] == ' ' || s[i] == '.' {
				cnt[s[i+1:]] += v
			}
		}
	}
	ans := make([]string, 0, len(cnt))
	for s, v := range cnt {
		ans = append(ans, strconv.Itoa(v)+" "+s)
	}
	return ans
}