func romanToInt(s string) int {
    romans := map[byte]int{'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
    ans := 0
    for i := 0; i < len(s)-1; i++ {
        if romans[s[i]] < romans[s[i+1]] {
            ans -= romans[s[i]]
        } else {
            ans += romans[s[i]]
        }
    }
    return ans + romans[s[len(s)-1]]
}