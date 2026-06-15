func getLength(nums []int) int {
	n := len(nums)
	ans := 1
	for l := 0; l < n; l++ {
		cnt := make(map[int]int)
		freq := make(map[int]int)
		for r := l; r < n; r++ {
			x := nums[r]
			c := cnt[x]
			if freq[c] > 0 {
				freq[c]--
				if freq[c] == 0 {
					delete(freq, c)
				}
			}
			cnt[x] = c + 1
			freq[cnt[x]]++
			cx := cnt[x]
			if len(cnt) == 1 || (len(freq) == 2 && (freq[cx*2] > 0 || (cx%2 == 0 && freq[cx/2] > 0))) {
				ans = max(ans, r-l+1)
			}
		}
	}
	return ans
}
