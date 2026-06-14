func checkGoodInteger(n int) bool {
    s := 0
    for ; n > 0; n /= 10 {
        x := n % 10
        s += x * (x - 1)
    }
    return s >= 50
}