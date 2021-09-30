func groupAnagrams(strs []string) [][]string {
	chars := map[string][]string{}
	for _, s := range strs {
		key := []byte(s)
		sort.Slice(key, func(i, j int) bool {
			return key[i] < key[j]
		})
		chars[string(key)] = append(chars[string(key)], s)
	}
	var res [][]string
	for _, v := range chars {
		res = append(res, v)
	}
	return res
}