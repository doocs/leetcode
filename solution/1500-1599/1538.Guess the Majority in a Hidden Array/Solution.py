# """
# This is the ArrayReader's API interface.
# You should not implement it, or speculate about its implementation
# """
# class ArrayReader(object):
# 	 # Compares 4 different elements in the array
# 	 # return 4 if the values of the 4 elements are the same (0 or 1).
# 	 # return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
# 	 # return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
#    def query(self, a: int, b: int, c: int, d: int) -> int:
#
# 	 # Returns the length of the array
#    def length(self) -> int:
#


class Solution:
    def guessMajority(self, reader: "ArrayReader") -> int:
        n = reader.length()
        x = reader.query(0, 1, 2, 3)
        a, b = 1, 0
        k = 0
        for i in range(4, n):
            if reader.query(0, 1, 2, i) == x:
                a += 1
            else:
                b += 1
                k = i

        y = reader.query(0, 1, 2, 4)
        if reader.query(1, 2, 3, 4) == y:
            a += 1
        else:
            b += 1
            k = 0
        if reader.query(0, 2, 3, 4) == y:
            a += 1
        else:
            b += 1
            k = 1
        if reader.query(0, 1, 3, 4) == y:
            a += 1
        else:
            b += 1
            k = 2

        if a == b:
            return -1
        return 3 if a > b else k
