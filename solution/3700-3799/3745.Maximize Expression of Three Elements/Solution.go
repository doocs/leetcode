func maximizeExpressionOfThree(nums []int) int {
    const inf = 1 << 30
    a, b, c := -inf, -inf, inf
    for _, x := range nums {
        if x < c {
            c = x
        }
        if x >= a {
            b = a
            a = x
        } else if x > b {
            b = x
        }
    }
    return a + b - c
}
