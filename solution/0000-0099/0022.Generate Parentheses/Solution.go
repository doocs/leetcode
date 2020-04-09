func generateParenthesis(n int) []string {
	result := make([]string, 0)
	backParenthesis(&result, "", 0, 0, n)
	return result
}

func backParenthesis(result *[]string, cur string, open, close, max int) {
	if len(cur) == 2 * max {
		*result = append(*result, cur)
		return
	}

	if open < max {
		backParenthesis(result, cur+"(", open+1, close, max)
	}
	if close < open {
		backParenthesis(result, cur+")", open, close+1, max)
	}
}