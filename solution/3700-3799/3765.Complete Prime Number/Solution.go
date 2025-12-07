func completePrime(num int) bool {
    isPrime := func(x int) bool {
        if x < 2 {
            return false
        }
        for i := 2; i*i <= x; i++ {
            if x%i == 0 {
                return false
            }
        }
        return true
    }

    s := strconv.Itoa(num)

    x := 0
    for i := 0; i < len(s); i++ {
        x = x*10 + int(s[i]-'0')
        if !isPrime(x) {
            return false
        }
    }

    x = 0
    p := 1
    for i := len(s) - 1; i >= 0; i-- {
        x = p*int(s[i]-'0') + x
        p *= 10
        if !isPrime(x) {
            return false
        }
    }

    return true
}
