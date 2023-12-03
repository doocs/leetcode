func countCompleteSubstrings(word string, k int) (ans int) {
	n := len(word)
	f := func(s string) (ans int) {
		m := len(s)
		for i := 1; i <= 26 && i*k <= m; i++ {
			l := i * k
			cnt := [26]int{}
			for j := 0; j < l; j++ {
				cnt[int(s[j]-'a')]++
			}
			freq := map[int]int{}
			for _, x := range cnt {
				if x > 0 {
					freq[x]++
				}
			}
			if freq[k] == i {
				ans++
			}
			for j := l; j < m; j++ {
				a := int(s[j] - 'a')
				b := int(s[j-l] - 'a')
				freq[cnt[a]]--
				cnt[a]++
				freq[cnt[a]]++

				freq[cnt[b]]--
				cnt[b]--
				freq[cnt[b]]++

				if freq[k] == i {
					ans++
				}
			}
		}
		return
	}
	for i := 0; i < n; {
		j := i + 1
		for j < n && abs(int(word[j])-int(word[j-1])) <= 2 {
			j++
		}
		ans += f(word[i:j])
		i = j
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}