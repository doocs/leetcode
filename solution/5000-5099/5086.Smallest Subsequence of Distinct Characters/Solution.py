'''
用栈思想。元素在字符串中出现的次数视为元素的可用性。
从前往后遍历字符串。若当前字符为i，则：
1、栈为空，i进栈；
2、若i大于栈顶元素，i进栈；
3、若i小于栈顶：1）i在栈中出现过一次，i可以性减一；2）进行出栈操作直到栈为空，或栈顶元素可用性为一，或i大于栈顶元素，将i进栈。每个元素出栈则该元素可用性减一。
栈底到栈定元素次序即为所求。
'''


class Solution:
    def smallestSubsequence(self, text: str) -> str:
        dic = {}
        for i in range(len(text)):
            if text[i] not in dic:
                dic[text[i]] = 1
            else:
                dic[text[i]] += 1
        ls = []
        flag = set()
        loc = 0
        while loc < len(text):
            if len(ls) == 0:
                ls.append(text[loc])
                flag.add(text[loc])
            elif text[loc] in flag:
                dic[text[loc]] -= 1
            elif ls[-1] < text[loc]:
                ls.append(text[loc])
                flag.add(text[loc])
            elif ls[-1] >= text[loc]:
                while len(ls) > 0 and ls[-1] >= text[loc]:
                    if dic[ls[-1]] == 1:
                        break
                    dic[ls[-1]] -= 1
                    flag.remove(ls[-1])
                    ls.pop(-1)
                ls.append(text[loc])
                flag.add(text[loc])
            loc += 1
        return ''.join(ls)
