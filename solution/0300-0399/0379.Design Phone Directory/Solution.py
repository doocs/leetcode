class PhoneDirectory:
    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.provided = [False] * maxNumbers

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        for i in range(len(self.provided)):
            if not self.provided[i]:
                self.provided[i] = True
                return i
        return -1

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return not self.provided[number]

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        self.provided[number] = False


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
