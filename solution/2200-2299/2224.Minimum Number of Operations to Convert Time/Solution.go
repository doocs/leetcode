func convertTime(current string, correct string) int {
    parse := func(s string) int {
        h := int(s[0] - '0') * 10 + int(s[1] - '0')
        m := int(s[3] - '0') * 10 + int(s[4] - '0')
        return h * 60 + m
    }
    a, b := parse(current), parse(correct)
    ans, d := 0, b - a
    for _, i := range []int{60, 15, 5, 1} {
        ans += d / i
        d %= i
    }
    return ans
}