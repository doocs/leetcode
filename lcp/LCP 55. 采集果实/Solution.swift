class Solution {
    func getMinimumTime(_ time: [Int], _ fruits: [[Int]], _ limit: Int) -> Int {
        var ans = 0
        
        for fruit in fruits {
            let index = fruit[0]
            let num = fruit[1]
            
            ans += ((num + limit - 1) / limit) * time[index]
        }
        
        return ans
    }
}