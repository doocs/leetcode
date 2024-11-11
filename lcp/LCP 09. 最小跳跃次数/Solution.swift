class Solution {
    func minJump(_ jump: [Int]) -> Int {
        let n = jump.count
        var vis = Array(repeating: false, count: n)
        var queue = [0]
        vis[0] = true
        var ans = 0
        var maxReach = 1
        
        while !queue.isEmpty {
            ans += 1
            let size = queue.count
            
            for _ in 0..<size {
                let i = queue.removeFirst()

                let forwardJump = i + jump[i]
                if forwardJump >= n {
                    return ans
                }
                
                if !vis[forwardJump] {
                    queue.append(forwardJump)
                    vis[forwardJump] = true
                }
                
                while maxReach < i {
                    if !vis[maxReach] {
                        queue.append(maxReach)
                        vis[maxReach] = true
                    }
                    maxReach += 1
                }
            }
        }
        
        return -1
    }
}