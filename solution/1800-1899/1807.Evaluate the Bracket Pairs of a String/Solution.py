class Solution:
    def evaluate(self, s: str, knowledge: List[List[str]]) -> str:
        def find_right_bracket(s, start, end):
            for i in range(start, end):
                if s[i] == ')':
                    return i

        knowledge_dict = {item[0]: item[1] for item in knowledge}
        res, n = [], len(s)
        i = 0
        while i < n:
            if s[i] == '(':
                right_bracket_pos = find_right_bracket(s, i + 1, n)
                key = s[i + 1 : right_bracket_pos]
                res.append(knowledge_dict.get(key, '?'))
                i = right_bracket_pos + 1
            else:
                res.append(s[i])
                i += 1
        return ''.join(res)
