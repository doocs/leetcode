func printNumbers(n int) []int {
    d := 10
    for i := 1; i < n; i++ {
        d *= 10
    }
    res := make([]int, d - 1)
    for i := 1; i < d; i++ {
        res[i - 1] = i
    }
    return res
}