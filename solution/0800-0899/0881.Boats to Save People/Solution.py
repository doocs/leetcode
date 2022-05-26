class Solution:
    def numRescueBoats(self, people, limit):
        """
        :type people: List[int]
        :type limit: int
        :rtype: int
        """
        people.sort()
        left = 0
        right = len(people) - 1
        ans = 0
        while left < right:
            if people[left] + people[right] <= limit:
                ans += 1
                left += 1
                right -= 1
            else:
                ans += 1
                right -= 1
        else:
            if left == right:
                ans += 1
        return ans
