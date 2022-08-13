# [1717. 删除子字符串的最大得分](https://leetcode.cn/problems/maximum-score-from-removing-substrings)

[English Version](/solution/1700-1799/1717.Maximum%20Score%20From%20Removing%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 和两个整数 <code>x</code> 和 <code>y</code> 。你可以执行下面两种操作任意次。</p>

<ul>
	<li>删除子字符串 <code>"ab"</code> 并得到 <code>x</code> 分。
    <ul>
    	<li>比方说，从 <code>"c<strong>ab</strong>xbae"</code> 删除 <code>ab</code> ，得到 <code>"cxbae"</code> 。</li>
    </ul>
    </li>
    <li>删除子字符串<code>"ba"</code> 并得到 <code>y</code> 分。
    <ul>
    	<li>比方说，从 <code>"cabx<strong>ba</strong>e"</code> 删除 <code>ba</code> ，得到 <code>"cabxe"</code> 。</li>
    </ul>
    </li>
</ul>

<p>请返回对 <code>s</code> 字符串执行上面操作若干次能得到的最大得分。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "cdbcbbaaabab", x = 4, y = 5
<b>输出：</b>19
<strong>解释：</strong>
- 删除 "cdbcbbaaa<strong>ba</strong>b" 中加粗的 "ba" ，得到 s = "cdbcbbaaab" ，加 5 分。
- 删除 "cdbcbbaa<strong>ab</strong>" 中加粗的 "ab" ，得到 s = "cdbcbbaa" ，加 4 分。
- 删除 "cdbcb<strong>ba</strong>a" 中加粗的 "ba" ，得到 s = "cdbcba" ，加 5 分。
- 删除 "cdbc<strong>ba</strong>" 中加粗的 "ba" ，得到 s = "cdbc" ，加 5 分。
总得分为 5 + 4 + 5 + 5 = 19 。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "aabbaaxybbaabb", x = 5, y = 4
<b>输出：</b>20
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= x, y &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> 只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

不失一般性，可以设 `x >= y`。因此，可以先删除所有 "ab"，再删除所有 "ba"，获取最终得分 ans。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumGain(self, s: str, x: int, y: int) -> int:
        if x < y:
            return self.maximumGain(s[::-1], y, x)
        ans = 0
        stk1, stk2 = [], []
        for c in s:
            if c != 'b':
                stk1.append(c)
            else:
                if stk1 and stk1[-1] == 'a':
                    stk1.pop()
                    ans += x
                else:
                    stk1.append(c)
        while stk1:
            c = stk1.pop()
            if c != 'b':
                stk2.append(c)
            else:
                if stk2 and stk2[-1] == 'a':
                    stk2.pop()
                    ans += y
                else:
                    stk2.append(c)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumGain(String s, int x, int y) {
        if (x < y) {
            return maximumGain(new StringBuilder(s).reverse().toString(), y, x);
        }
        int ans = 0;
        Deque<Character> stk1 = new ArrayDeque<>();
        Deque<Character> stk2 = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c != 'b') {
                stk1.push(c);
            } else {
                if (!stk1.isEmpty() && stk1.peek() == 'a') {
                    stk1.pop();
                    ans += x;
                } else {
                    stk1.push(c);
                }
            }
        }
        while (!stk1.isEmpty()) {
            char c = stk1.pop();
            if (c != 'b') {
                stk2.push(c);
            } else {
                if (!stk2.isEmpty() && stk2.peek() == 'a') {
                    stk2.pop();
                    ans += y;
                } else {
                    stk2.push(c);
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumGain(string s, int x, int y) {
        if (x < y) {
            reverse(s.begin(), s.end());
            return maximumGain(s, y, x);
        }
        int ans = 0;
        stack<char> stk1;
        stack<char> stk2;
        for (char c : s) {
            if (c != 'b')
                stk1.push(c);
            else {
                if (!stk1.empty() && stk1.top() == 'a') {
                    stk1.pop();
                    ans += x;
                } else
                    stk1.push(c);
            }
        }
        while (!stk1.empty()) {
            char c = stk1.top();
            stk1.pop();
            if (c != 'b')
                stk2.push(c);
            else {
                if (!stk2.empty() && stk2.top() == 'a') {
                    stk2.pop();
                    ans += y;
                } else
                    stk2.push(c);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumGain(s string, x int, y int) int {
	if x < y {
		t := []rune(s)
		for i, j := 0, len(t)-1; i < j; i, j = i+1, j-1 {
			t[i], t[j] = t[j], t[i]
		}
		return maximumGain(string(t), y, x)
	}
	ans := 0
	var stk1 []byte
	var stk2 []byte
	for i := range s {
		if s[i] != 'b' {
			stk1 = append(stk1, s[i])
		} else {
			if len(stk1) > 0 && stk1[len(stk1)-1] == 'a' {
				stk1 = stk1[0 : len(stk1)-1]
				ans += x
			} else {
				stk1 = append(stk1, s[i])
			}
		}
	}
	for _, c := range stk1 {
		if c != 'a' {
			stk2 = append(stk2, c)
		} else {
			if len(stk2) > 0 && stk2[len(stk2)-1] == 'b' {
				stk2 = stk2[0 : len(stk2)-1]
				ans += y
			} else {
				stk2 = append(stk2, c)
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
