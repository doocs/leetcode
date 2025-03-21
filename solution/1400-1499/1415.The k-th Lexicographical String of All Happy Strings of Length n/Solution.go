func getHappyString(n int, k int) string {
    ans := []string{}
    var s []byte

    var dfs func()
    dfs = func() {
        if len(s) == n {
            ans = append(ans, string(s))
            return
        }
        if len(ans) >= k {
            return
        }
        for c := byte('a'); c <= 'c'; c++ {
            if len(s) == 0 || s[len(s)-1] != c {
                s = append(s, c)
                dfs()
                s = s[:len(s)-1]
            }
        }
    }

    dfs()
    if len(ans) < k {
        return ""
    }
    return ans[k-1]
}
