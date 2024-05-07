class Solution {
    func divingBoard(_ shorter: Int, _ longer: Int, _ k: Int) -> [Int] {
        if k == 0 {
            return []
        }
        if shorter == longer {
            return [shorter * k]
        }
        
        var ans = [Int](repeating: 0, count: k + 1)
        for i in 0...k {
            ans[i] = longer * i + shorter * (k - i)
        }
        return ans
    }
}