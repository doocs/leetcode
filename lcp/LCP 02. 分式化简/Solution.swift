class Solution {
    private var cont: [Int] = []

    func fraction(_ cont: [Int]) -> [Int] {
        self.cont = cont
        return dfs(0)
    }

    private func dfs(_ i: Int) -> [Int] {
        if i == cont.count - 1 {
            return [cont[i], 1]
        }
        let next = dfs(i + 1)
        let a = next[0]
        let b = next[1]
        let x = a * cont[i] + b
        let y = a
        let g = gcd(x, y)
        return [x / g, y / g]
    }

    private func gcd(_ a: Int, _ b: Int) -> Int {
        return b == 0 ? a : gcd(b, a % b)
    }
}
