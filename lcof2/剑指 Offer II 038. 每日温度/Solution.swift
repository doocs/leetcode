class Solution {
    func dailyTemperatures(_ temperatures: [Int]) -> [Int] {
        let n = temperatures.count
        var ans = [Int](repeating: 0, count: n)
        var stack = [Int]()
        
        for i in 0..<n {
            while !stack.isEmpty && temperatures[stack.last!] < temperatures[i] {
                let j = stack.removeLast()
                ans[j] = i - j
            }
            stack.append(i)
        }
        
        return ans
    }
}