type Encrypter struct {
	mp  map[byte]string
	cnt map[string]int
}

func Constructor(keys []byte, values []string, dictionary []string) Encrypter {
	mp := map[byte]string{}
	cnt := map[string]int{}
	for i, k := range keys {
		mp[k] = values[i]
	}
	e := Encrypter{mp, cnt}
	for _, v := range dictionary {
		e.cnt[e.Encrypt(v)]++
	}
	return e
}

func (this *Encrypter) Encrypt(word1 string) string {
	var ans strings.Builder
	for _, c := range word1 {
		if v, ok := this.mp[byte(c)]; ok {
			ans.WriteString(v)
		} else {
			return ""
		}
	}
	return ans.String()
}

func (this *Encrypter) Decrypt(word2 string) int {
	return this.cnt[word2]
}

/**
 * Your Encrypter object will be instantiated and called as such:
 * obj := Constructor(keys, values, dictionary);
 * param_1 := obj.Encrypt(word1);
 * param_2 := obj.Decrypt(word2);
 */