/**
 * @lc app=leetcode.cn id=241 lang=golang
 * Accepted
 * 25/25 cases passed (4 ms)
 * Your runtime beats 52.94 % of golang submissions
 * Your memory usage beats 100 % of golang submissions (6.5 MB)
 * time complex O(n^2)
 */

import "strconv"

func diffWaysToCompute(input string) []int {
	ret := []int{}
	for i, c := range input {
		if c == '+' || c == '-' || c == '*' {
			left_ret := diffWaysToCompute(input[0:i])
			right_ret := diffWaysToCompute(input[i+1:])
			for _, l_ret := range left_ret {
				for _, r_ret := range right_ret {
					if c == '+' {
						ret = append(ret, l_ret+r_ret)
					} else if c == '-' {
						ret = append(ret, l_ret-r_ret)
					} else {
						ret = append(ret, l_ret*r_ret)
					}
				}
			}
		}
	}
	if len(ret) == 0 {
		num, _ := strconv.Atoi(input)
		ret = append(ret, num)
	}
	return ret
}