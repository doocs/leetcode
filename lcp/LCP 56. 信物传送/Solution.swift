class Solution {
    func conveyorBelt(_ matrix: [String], _ start: [Int], _ end: [Int]) -> Int {
        let directions: [(Int, Int)] = [(-1, 0), (0, 1), (1, 0), (0, -1)]
        let directionMap: [Character: Int] = ["^": 0, ">": 1, "v": 2, "<": 3]
        
        let rows = matrix.count
        let cols = matrix[0].count
        
        var dist = Array(repeating: Array(repeating: Int.max, count: cols), count: rows)
        var deque: [(Int, Int)] = []
        
        dist[start[0]][start[1]] = 0
        deque.append((start[0], start[1]))
        
        while !deque.isEmpty {
            let (i, j) = deque.removeFirst()
            
            if i == end[0] && j == end[1] {
                return dist[i][j]
            }
            
            for (k, (di, dj)) in directions.enumerated() {
                let ni = i + di
                let nj = j + dj
                
                if ni >= 0 && ni < rows && nj >= 0 && nj < cols {
                    let currentChar = matrix[i][matrix[i].index(matrix[i].startIndex, offsetBy: j)]
                    let additionalCost = directionMap[currentChar] == k ? 0 : 1
                    let newDist = dist[i][j] + additionalCost
                    
                    if newDist < dist[ni][nj] {
                        dist[ni][nj] = newDist
                        
                        if additionalCost == 0 {
                            deque.insert((ni, nj), at: 0)
                        } else {
                            deque.append((ni, nj))
                        }
                    }
                }
            }
        }
        
        return -1
    }
}