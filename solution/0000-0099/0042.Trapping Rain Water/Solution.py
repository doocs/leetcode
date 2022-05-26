class Solution:
    def trap(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        length = len(height)
        if length == 0 or length == 1:
            return 0
        slow = 0
        fast = 1
        total = 0
        while fast < length:
            # 每次更新stopPoint
            stopPoint = fast
            while fast < length and height[fast] <= height[slow]:
                if height[fast] > height[stopPoint]:
                    stopPoint = fast
                fast += 1
            # 越界了要回到stopPoint
            if fast >= length:
                fast = stopPoint
            tmp = 0
            bottom = min(height[slow], height[fast])
            for i in range(slow + 1, fast):
                tmp += bottom - height[i]
            slow = fast
            total += tmp
            fast += 1
        return total
