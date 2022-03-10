func groupStrings(strings []string) [][]string {
	mp := make(map[string][]string)
	for _, s := range strings {
		k := ""
		for i := range s {
			k += string((s[i]-s[0]+26)%26 + 'a')
		}
		mp[k] = append(mp[k], s)
	}
	var ans [][]string
	for _, v := range mp {
		ans = append(ans, v)
	}
	return ans
}