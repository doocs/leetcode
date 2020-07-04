func fib(n int) int {
    if n < 2 {
        return n
    }
    a := make([]int,n+1)
    a[0]=0
    a[1]=1
    for i := 2; i < n+1; i++ {
        a[i] = (a[i-1]+ a[i-2])%1000000007
    }
    return a[n]
}