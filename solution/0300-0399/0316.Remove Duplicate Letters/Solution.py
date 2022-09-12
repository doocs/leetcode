class Solution:
    def removeDuplicateLetters(self, s: str) -> str:
        count, in_stack = [0 for _ in range(128)], [False for _ in range(128)]
        stack = []
        for c in s:
            count[ord(c)] += 1
        
        for c in s:
            count[ord(c)] -= 1
            if in_stack[ord(c)]:
                continue
            while len(stack) > 0 and stack[len(stack)-1] > c:
                peek = stack[len(stack)-1]
                if count[ord(peek)] < 1:
                  break
                in_stack[ord(peek)] = False
                stack.pop()
            stack.append(c)
            in_stack[ord(c)] = True
        return ''.join(stack)