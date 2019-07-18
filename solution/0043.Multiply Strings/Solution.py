class Solution(object):
    def multiply(self, num1, num2):
       
        # Create a dictionary to store 0-9 key-value pairs
        dict_nums={"0": 0, "1": 1, "2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7, "8": 8, "9": 9}
        number1 = 0
        number2 = 0

		# Convert num1 in integer format
        for i in num1:
            key = dict_nums[i]
            number1 = (number1 * 10) + key


		# Convert num2 in integer format
        for i in num2:
			key = dict_nums[i]
			number2 = (number2 * 10) + key
            
        return str(number1 * number2)
