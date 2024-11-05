class Solution {
    func robot(_ command: String, _ obstacles: [[Int]], _ x: Int, _ y: Int) -> Bool {
        var visited: Set<[Int]> = []
        var i = 0, j = 0
        visited.insert([i, j])
        
        for c in command {
            if c == "U" {
                j += 1
            } else {
                i += 1
            }
            visited.insert([i, j])
        }
        
        func canReach(_ targetX: Int, _ targetY: Int) -> Bool {
            let k = min(targetX / i, targetY / j)
            return visited.contains([targetX - k * i, targetY - k * j])
        }
        
        if !canReach(x, y) {
            return false
        }
        
        for obstacle in obstacles {
            let obstacleX = obstacle[0]
            let obstacleY = obstacle[1]
            if obstacleX > x || obstacleY > y {
                continue
            }
            if canReach(obstacleX, obstacleY) {
                return false
            }
        }
        
        return true
    }
}