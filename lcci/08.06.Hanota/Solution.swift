class Solution {
    func hanota(_ A: inout [Int], _ B: inout [Int], _ C: inout [Int]) {
        dfs(n: A.count, a: &A, b: &B, c: &C)
    }

    private func dfs(n: Int, a: inout [Int], b: inout [Int], c: inout [Int]) {
        if n == 1 {
            c.append(a.removeLast())
            return
        }
        dfs(n: n - 1, a: &a, b: &c, c: &b)
        c.append(a.removeLast())
        dfs(n: n - 1, a: &b, b: &a, c: &c)
    }
}