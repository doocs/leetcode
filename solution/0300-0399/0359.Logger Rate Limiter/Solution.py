class Logger:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.limiter = {}

    def shouldPrintMessage(self, timestamp: int, message: str) -> bool:
        """
        Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity.
        """
        t = self.limiter.get(message, 0)
        if t > timestamp:
            return False
        self.limiter[message] = timestamp + 10
        return True


# Your Logger object will be instantiated and called as such:
# obj = Logger()
# param_1 = obj.shouldPrintMessage(timestamp,message)
