class Solution:
    def constructRectangle(self, area: int) -> List[int]:
        sr = int(math.sqrt(area))
        l = w = sr
        while l <= area and w >= 1:
            s = l * w
            if s == area:
                break
            if s > area:
                w -= 1
            else:
                l += 1
        return [l, w]