func numUniqueEmails(emails []string) int {
	s := make(map[string]struct{})
	for _, email := range emails {
		parts := strings.Split(email, "@")
		local := parts[0]
		domain := parts[1]
		var t strings.Builder
		for _, c := range local {
			if c == '.' {
				continue
			}
			if c == '+' {
				break
			}
			t.WriteByte(byte(c))
		}
		s[t.String()+"@"+domain] = struct{}{}
	}
	return len(s)
}
