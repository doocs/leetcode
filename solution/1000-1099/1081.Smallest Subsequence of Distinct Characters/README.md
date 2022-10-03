# [1081. 不同字符的最小子序列](https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters)

[English Version](/solution/1000-1099/1081.Smallest%20Subsequence%20of%20Distinct%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>返回 <code>s</code> 字典序最小的子序列，该子序列包含 <code>s</code> 的所有不同字符，且只包含一次。</p>

<p><strong>注意：</strong>该题与 316 <a href="https://leetcode.com/problems/remove-duplicate-letters/">https://leetcode.com/problems/remove-duplicate-letters/</a> 相同</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong><code>s = "bcabc"</code>
<strong>输出<code>：</code></strong><code>"abc"</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong><code>s = "cbacdcbc"</code>
<strong>输出：</strong><code>"acdb"</code></pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 1000</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：栈**

我们用一个数组 `last` 记录每个字符最后一次出现的位置，用栈来保存结果字符串，用一个数组 `vis` 或者一个整型变量 `mask` 记录当前字符是否在栈中。

遍历字符串 $s$，对于每个字符 $c$，如果 $c$ 不在栈中，我们就需要判断栈顶元素是否大于 $c$，如果大于 $c$，且栈顶元素在后面还会出现，我们就将栈顶元素弹出，将 $c$ 压入栈中。

最后将栈中元素拼接成字符串作为结果返回。

时间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestSubsequence(self, s: str) -> str:
        last = defaultdict(int)
        for i, c in enumerate(s):
            last[c] = i
        stk = []
        vis = set()
        for i, c in enumerate(s):
            if c in vis:
                continue
            while stk and stk[-1] > c and last[stk[-1]] > i:
                vis.remove(stk.pop())
            stk.append(c)
            vis.add(c)
        return ''.join(stk)
```

```python
class Solution:
    def smallestSubsequence(self, s: str) -> str:
        count, in_stack = [0] * 128, [False] * 128
        stack = []
        for c in s:
            count[ord(c)] += 1

        for c in s:
            count[ord(c)] -= 1
            if in_stack[ord(c)]:
                continue
            while len(stack) and stack[-1] > c:
                peek = stack[-1]
                if count[ord(peek)] < 1:
                    break
                in_stack[ord(peek)] = False
                stack.pop()
            stack.append(c)
            in_stack[ord(c)] = True
        return ''.join(stack)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String smallestSubsequence(String text) {
        int[] cnt = new int[26];
        for (char c : text.toCharArray()) {
            ++cnt[c - 'a'];
        }
        boolean[] vis = new boolean[26];
        char[] cs = new char[text.length()];
        int top = -1;
        for (char c : text.toCharArray()) {
            --cnt[c - 'a'];
            if (!vis[c - 'a']) {
                while (top >= 0 && c < cs[top] && cnt[cs[top] - 'a'] > 0) {
                    vis[cs[top--] - 'a'] = false;
                }
                cs[++top] = c;
                vis[c - 'a'] = true;
            }
        }
        return String.valueOf(cs, 0, top + 1);
    }
}
```

```java
class Solution {
    public String smallestSubsequence(String s) {
        int n = s.length();
        int[] last = new int[26];
        for (int i = 0; i < n; ++i) {
            last[s.charAt(i) - 'a'] = i;
        }
        Deque<Character> stk = new ArrayDeque<>();
        int mask = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (((mask >> (c - 'a')) & 1) == 1) {
                continue;
            }
            while (!stk.isEmpty() && stk.peek() > c && last[stk.peek() - 'a'] > i) {
                mask ^= 1 << (stk.pop() - 'a');
            }
            stk.push(c);
            mask |= 1 << (c - 'a');
        }
        StringBuilder ans = new StringBuilder();
        for (char c : stk) {
            ans.append(c);
        }
        return ans.reverse().toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string smallestSubsequence(string s) {
        int n = s.size();
        int last[26] = {0};
        for (int i = 0; i < n; ++i) {
            last[s[i] - 'a'] = i;
        }
        string ans;
        int mask = 0;
        for (int i = 0; i < n; ++i) {
            char c = s[i];
            if ((mask >> (c - 'a')) & 1) {
                continue;
            }
            while (!ans.empty() && ans.back() > c && last[ans.back() - 'a'] > i) {
                mask ^= 1 << (ans.back() - 'a');
                ans.pop_back();
            }
            ans.push_back(c);
            mask |= 1 << (c - 'a');
        }
        return ans;
    }
};
```

### **Go**

```go
func smallestSubsequence(s string) string {
	last := make([]int, 26)
	for i, c := range s {
		last[c-'a'] = i
	}
	stk := []rune{}
	vis := make([]bool, 128)
	for i, c := range s {
		if vis[c] {
			continue
		}
		for len(stk) > 0 && stk[len(stk)-1] > c && last[stk[len(stk)-1]-'a'] > i {
			vis[stk[len(stk)-1]] = false
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, c)
		vis[c] = true
	}
	return string(stk)
}
```

```go
func smallestSubsequence(s string) string {
	count, in_stack, stack := make([]int, 128), make([]bool, 128), make([]rune, 0)
	for _, c := range s {
		count[c] += 1
	}

	for _, c := range s {
		count[c] -= 1
		if in_stack[c] {
			continue
		}
		for len(stack) > 0 && stack[len(stack)-1] > c && count[stack[len(stack)-1]] > 0 {
			peek := stack[len(stack)-1]
			stack = stack[0 : len(stack)-1]
			in_stack[peek] = false
		}
		stack = append(stack, c)
		in_stack[c] = true
	}
	return string(stack)
}
```

### **...**

```

```

<!-- tabs:end -->
