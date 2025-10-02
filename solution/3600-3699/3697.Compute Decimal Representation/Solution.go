func decimalRepresentation(n int) []int {
    ans := []int{}
    p := 1
    for n > 0 {
        v := n % 10
        n /= 10
        if v != 0 {
            ans = append(ans, p*v)
        }
        p *= 10
    }
    slices.Reverse(ans)
    return ans
}
