func numMatchingSubseq(s string, words []string) int {
	buckets := make([][]string, 26)
	for _, word := range words {
		buckets[word[0]-'a'] = append(buckets[word[0]-'a'], word)
	}
	res := 0
	for _, c := range s {
		old := buckets[c-'a']
		buckets[c-'a'] = nil
		for _, t := range old {
			if len(t) == 1 {
				res++
			} else {
				buckets[t[1]-'a'] = append(buckets[t[1]-'a'], t[1:])
			}
		}
	}
	return res
}