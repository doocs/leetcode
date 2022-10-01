type WordDistance struct {
	d map[string][]int
}

func Constructor(wordsDict []string) WordDistance {
	d := map[string][]int{}
	for i, w := range wordsDict {
		d[w] = append(d[w], i)
	}
	return WordDistance{d}
}

func (this *WordDistance) Shortest(word1 string, word2 string) int {
	a, b := this.d[word1], this.d[word2]
	ans := 0x3f3f3f3f
	i, j := 0, 0
	for i < len(a) && j < len(b) {
		ans = min(ans, abs(a[i]-b[j]))
		if a[i] <= b[j] {
			i++
		} else {
			j++
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * obj := Constructor(wordsDict);
 * param_1 := obj.Shortest(word1,word2);
 */