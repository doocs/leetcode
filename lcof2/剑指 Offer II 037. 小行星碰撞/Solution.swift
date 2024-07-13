class Solution {
    func asteroidCollision(_ asteroids: [Int]) -> [Int] {
        var stack = [Int]()
        
        for asteroid in asteroids {
            if asteroid > 0 {
                stack.append(asteroid)
            } else {
                while !stack.isEmpty && stack.last! > 0 && stack.last! < -asteroid {
                    stack.removeLast()
                }
                if !stack.isEmpty && stack.last! == -asteroid {
                    stack.removeLast()
                } else if stack.isEmpty || stack.last! < 0 {
                    stack.append(asteroid)
                }
            }
        }
        
        return stack
    }
}