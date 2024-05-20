class Solution {
    func fib(_ n: Int) -> Int {
        var a = 0
        var b = 1
        var count = n
        while count > 0 {
            let c = (a + b) % 1000000007
            a = b
            b = c
            count -= 1
        }
        return a
    }
}