/*
 * Report by leetcode.com
 * Runtime: 8 ms, Memory Usage: 3.2 MB
 */
func lengthOfLongestSubstring(s string) int {
	mathMax := func(a, b int) int {
		if a > b {
			return a
		}
		return b
	}
	cache := map[rune]int{}
	var max, position int
	for i, r := range s {
		if num, ok := cache[r]; ok {
			position = mathMax(position, num+1)
		}
		cache[r] = i
		max = mathMax(max, i-position+1)
	}
	return max
}