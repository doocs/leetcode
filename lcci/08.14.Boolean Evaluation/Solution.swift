class Solution {
    private var memo = [String: [Int]]()

    func countEval(_ s: String, _ result: Int) -> Int {
        memo = [:]
        let ans = dfs(s)
        return result == 0 || result == 1 ? ans[result] : 0
    }

    private func dfs(_ s: String) -> [Int] {
        if let res = memo[s] {
            return res
        }
        
        var res = [0, 0]
        if s.count == 1 {
            res[Int(String(s))!] = 1
            return res
        }

        for k in 0..<s.count {
            let index = s.index(s.startIndex, offsetBy: k)
            let op = String(s[index])

            if op == "&" || op == "|" || op == "^" {
                let left = dfs(String(s[s.startIndex..<index]))
                let right = dfs(String(s[s.index(after: index)...]))
                
                for i in 0...1 {
                    for j in 0...1 {
                        var v = 0
                        if op == "&" {
                            v = i & j
                        } else if op == "|" {
                            v = i | j
                        } else if op == "^" {
                            v = i ^ j
                        }
                        res[v] += left[i] * right[j]
                    }
                }
            }
        }

        memo[s] = res
        return res
    }
}