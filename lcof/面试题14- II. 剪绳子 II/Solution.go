func cuttingRope(n int) int {
    if n <= 3 {
        return n-1
    }
    sum := 1
    for n > 4 {
        sum *=  3
        sum = sum%1000000007 
        n -= 3
    }
    return sum*n%1000000007
}