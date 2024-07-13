class Solution {
    func validateStackSequences(_ pushed: [Int], _ popped: [Int]) -> Bool {
        var stack = [Int]()
        var j = 0
        
        for v in pushed {
            stack.append(v)
            while !stack.isEmpty && stack.last == popped[j] {
                stack.removeLast()
                j += 1
            }
        }
        
        return j == pushed.count
    }
}