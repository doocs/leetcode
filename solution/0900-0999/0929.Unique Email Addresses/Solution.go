func numUniqueEmails(emails []string) int {
	s := map[string]bool{}
	for _, email := range emails {
		i := strings.IndexByte(email, '@')
		local := strings.SplitN(email[:i], "+", 2)[0]
		local = strings.ReplaceAll(local, ".", "")
		domain := email[i:]
		s[local+domain] = true
	}
	return len(s)
}