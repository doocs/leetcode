class Solution(object):
    def divide(self, dividend, divisor):
        """
        :type dividend: int
        :type divisor: int
        :rtype: int
        """
        if dividend == 0:
            return 0
        if divisor == 0:
            return 2 ** 31 - 1

        sign = (dividend < 0) ^ (divisor < 0)

        quotient = 0
        dividend = abs(dividend)
        divisor = abs(divisor)
        while dividend >= divisor:
            tmp, i = divisor, 1
            while dividend >= tmp:
                dividend -= tmp
                quotient += i
                tmp <<= 1
                print('Value of temp: '+str(tmp))
                i <<= 1
                print('Value of i: '+str(i))
        quotient *= (-1) ** sign

        return min(max(quotient, - 2 ** 31), 2 ** 31 - 1)
