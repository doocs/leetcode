func minimumDeletions(word string, k int) int {
	freq := [26]int{}
	for _, c := range word {
		freq[c-'a']++
	}
	nums := []int{}
	for _, v := range freq {
		if v > 0 {
			nums = append(nums, v)
		}
	}
	f := func(v int) int {
		ans := 0
		for _, x := range nums {
			if x < v {
				ans += x
			} else if x > v+k {
				ans += x - v - k
			}
		}
		return ans
	}
	ans := len(word)
	for i := 0; i <= len(word); i++ {
		ans = min(ans, f(i))
	}
	return ans
}