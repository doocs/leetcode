class Solution {
    func lastRemaining(_ n: Int, _ m: Int) -> Int {
        return f(n, m)
    }
    
    private func f(_ n: Int, _ m: Int) -> Int {
        if n == 1 {
            return 0
        }
        let x = f(n - 1, m)
        return (m + x) % n
    }
}