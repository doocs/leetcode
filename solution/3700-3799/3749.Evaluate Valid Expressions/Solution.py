class Solution:
    def evaluateExpression(self, expression: str) -> int:
        def parse(i: int) -> (int, int):
            if expression[i].isdigit() or expression[i] == "-":
                j = i
                if expression[j] == "-":
                    j += 1
                while j < len(expression) and expression[j].isdigit():
                    j += 1
                return int(expression[i:j]), j

            j = i
            while expression[j] != "(":
                j += 1
            op = expression[i:j]
            j += 1
            val1, j = parse(j)

            j += 1
            val2, j = parse(j)
            j += 1
            res = 0
            match op:
                case "add":
                    res = val1 + val2
                case "sub":
                    res = val1 - val2
                case "mul":
                    res = val1 * val2
                case "div":
                    res = val1 // val2
            return res, j

        return parse(0)[0]
