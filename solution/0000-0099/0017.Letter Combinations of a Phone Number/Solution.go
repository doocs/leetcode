func letterCombinations(digits string) []string {
	ans := []string{}
	if len(digits) == 0 {
		return ans
	}
	ans = append(ans, "")
	d := []string{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}
	for _, i := range digits {
		s := d[i-'2']
		t := []string{}
		for _, a := range ans {
			for _, b := range s {
				t = append(t, a+string(b))
			}
		}
		ans = t
	}
	return ans
}