class Solution {
    var memo = [String: Bool]()
    func canCross(_ stones: [Int]) -> Bool {
        return canCross(Set(stones), 1, 0, stones[stones.count - 1])
    }
    
    func canCross(_ stones: Set<Int>, _ k: Int, _ jump: Int, _ lastStep: Int) -> Bool {
        if let res = memo["\(k) | \(jump)"] { return res }
        
        if k == 0 { return false }
        
        let curJump = jump + k
        if !stones.contains(curJump) { return false }
        if curJump == lastStep { return true }
        
        let res = canCross(stones, k, curJump, lastStep)
                || canCross(stones, k - 1,  curJump, lastStep)
                || canCross(stones, k + 1, curJump, lastStep)
        
        memo["\(k) | \(jump)"] = res
        return res
        
    }
}