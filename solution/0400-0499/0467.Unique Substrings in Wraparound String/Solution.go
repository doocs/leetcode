func findSubstringInWraproundString(s string) (ans int) {
	f := [26]int{}
	k := 0
	for i := range s {
		if i > 0 && (s[i]-s[i-1]+26)%26 == 1 {
			k++
		} else {
			k = 1
		}
		f[s[i]-'a'] = max(f[s[i]-'a'], k)
	}
	for _, x := range f {
		ans += x
	}
	return
}