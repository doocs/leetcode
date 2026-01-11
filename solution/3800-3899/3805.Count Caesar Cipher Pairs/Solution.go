func countPairs(words []string) int64 {
	cnt := make(map[string]int)
	var ans int64 = 0
	for _, s := range words {
		t := []rune(s)
		k := 'z' - t[0]
		for i := 1; i < len(t); i++ {
			t[i] = 'a' + (t[i]-'a'+k)%26
		}
		t[0] = 'z'
		key := string(t)
		cnt[key]++
	}
	for _, v := range cnt {
		ans += int64(v) * int64(v-1) / 2
	}
	return ans
}
