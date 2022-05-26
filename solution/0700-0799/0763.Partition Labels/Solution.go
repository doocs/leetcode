/**
 * @lc app=leetcode.cn id=763 lang=golang
 * Accepted
 * 116/116 cases passed (0 ms)
 * Your runtime beats 100 % of golang submissions
 * Your memory usage beats 66.67 % of golang submissions (2.3 MB)
 * time O(n) space O(k)
 */

func partitionLabels(S string) []int {
	flag := 0
	tmp := 0
	start := -1
	last := [26]int{}
	ret := []int{}
	for i := 0; i < len(S); i++ {
		last[S[i]-'a'] = i
	}
	for i := 0; i < len(S); i++ {
		tmp = last[S[i]-'a']
		if flag < tmp {
			flag = tmp
		}
		if flag == i {
			ret = append(ret, flag-start)
			start = flag
		}
	}
	return ret
}