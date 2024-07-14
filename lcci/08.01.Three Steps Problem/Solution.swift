class Solution {
    func waysToStep(_ n: Int) -> Int {
        let mod = Int(1e9) + 7
        var a = 1, b = 2, c = 4
        if n == 1 { return a }
        if n == 2 { return b }
        if n == 3 { return c }

        for _ in 1..<n {
            let t = a
            a = b
            b = c
            c = ((a + b) % mod + t) % mod
        }
        return a
    }
}
