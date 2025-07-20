class Solution:
    def checkPrimeFrequency(self, nums: List[int]) -> bool:
        def is_prime(x: int) -> bool:
            if x < 2:
                return False
            return all(x % i for i in range(2, int(sqrt(x)) + 1))

        cnt = Counter(nums)
        return any(is_prime(x) for x in cnt.values())
