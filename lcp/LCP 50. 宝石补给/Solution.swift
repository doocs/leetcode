class Solution {
    func giveGem(_ gem: [Int], _ operations: [[Int]]) -> Int {
        var gem = gem
        
        for op in operations {
            let x = op[0], y = op[1]
            let v = gem[x] / 2
            gem[y] += v
            gem[x] -= v
        }
        
        let maxGem = gem.max() ?? 0
        let minGem = gem.min() ?? 0
        return maxGem - minGem
    }
}