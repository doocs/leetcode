class Solution {
    func printNumbers(_ n: Int) -> [Int] {
        let maxNumber = maxNumberForDigits(n)
        return Array(1...maxNumber)
    }

    private func maxNumberForDigits(_ n: Int) -> Int {
        var maxNumber = 1
        for _ in 0..<n {
            maxNumber *= 10
        }
        return maxNumber - 1
    }

    private var s = String()
    private var ans = [String]()

    func print(_ n: Int) -> [String] {
        for i in 1...n {
            dfs(0, i)
        }
        return ans
    }

    private func dfs(_ i: Int, _ j: Int) {
        if i == j {
            ans.append(s)
            return
        }
        let start = i > 0 ? 0 : 1
        for k in start..<10 {
            s.append("\(k)")
            dfs(i + 1, j)
            s.removeLast()
        }
    }
}