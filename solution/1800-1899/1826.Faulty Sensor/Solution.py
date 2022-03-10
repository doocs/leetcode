class Solution:
    def badSensor(self, sensor1: List[int], sensor2: List[int]) -> int:
        i, n = 0, len(sensor1)
        while i < n - 1:
            if sensor1[i] != sensor2[i]:
                break
            i += 1
        while i < n - 1:
            if sensor1[i + 1] != sensor2[i]:
                return 1
            if sensor1[i] != sensor2[i + 1]:
                return 2
            i += 1
        return -1
