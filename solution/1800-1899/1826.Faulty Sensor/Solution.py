class Solution:
    def badSensor(self, sensor1: list[int], sensor2: list[int]) -> int:
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
