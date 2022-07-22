class Solution:
    def asteroidsDestroyed(self, mass: int, asteroids: List[int]) -> bool:
        asteroids.sort()
        for v in asteroids:
            if mass < v:
                return False
            mass += v
        return True
