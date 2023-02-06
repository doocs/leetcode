# [2434. 使用机器人打印字典序最小的字符串](https://leetcode.cn/problems/using-a-robot-to-print-the-lexicographically-smallest-string)

[English Version](/solution/2400-2499/2434.Using%20a%20Robot%20to%20Print%20the%20Lexicographically%20Smallest%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;和一个机器人，机器人当前有一个空字符串&nbsp;<code>t</code>&nbsp;。执行以下操作之一，直到&nbsp;<code>s</code> 和&nbsp;<code>t</code>&nbsp;<strong>都变成空字符串：</strong></p>

<ul>
	<li>删除字符串&nbsp;<code>s</code>&nbsp;的 <strong>第一个</strong>&nbsp;字符，并将该字符给机器人。机器人把这个字符添加到 <code>t</code>&nbsp;的尾部。</li>
	<li>删除字符串&nbsp;<code>t</code>&nbsp;的&nbsp;<strong>最后一个</strong>&nbsp;字符，并将该字符给机器人。机器人将该字符写到纸上。</li>
</ul>

<p>请你返回纸上能写出的字典序最小的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "zza"
<b>输出：</b>"azz"
<b>解释：</b>用 p 表示写出来的字符串。
一开始，p="" ，s="zza" ，t="" 。
执行第一个操作三次，得到 p="" ，s="" ，t="zza" 。
执行第二个操作三次，得到 p="azz" ，s="" ，t="" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "bac"
<b>输出：</b>"abc"
<b>解释：</b>用 p 表示写出来的字符串。
执行第一个操作两次，得到 p="" ，s="c" ，t="ba" 。
执行第二个操作两次，得到 p="ab" ，s="c" ，t="" 。
执行第一个操作，得到 p="ab" ，s="" ，t="c" 。
执行第二个操作，得到 p="abc" ，s="" ，t="" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>s = "bdda"
<b>输出：</b>"addb"
<b>解释：</b>用 p 表示写出来的字符串。
一开始，p="" ，s="bdda" ，t="" 。
执行第一个操作四次，得到 p="" ，s="" ，t="bdda" 。
执行第二个操作四次，得到 p="addb" ，s="" ，t="" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 栈**

题目可以转化为，给定一个字符串序列，在借助一个辅助栈的情况下，将其转化为字典序最小的字符串序列。

我们可以用数组 `cnt` 维护字符串 $s$ 中每个字符的出现次数，用栈 `stk` 作为题目中的辅助栈，用变量 `mi` 维护还未遍历到的字符串中最小的字符。

遍历字符串 $s$，对于每个字符 $c$，我们先将字符 $c$ 在数组 `cnt` 中的出现次数减一，更新 `mi`。然后将字符 $c$ 入栈，此时如果栈顶元素小于等于 `mi`，则循环将栈顶元素出栈，并将出栈的字符加入答案。

遍历结束，返回答案即可。

时间复杂度 $O(n+C)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度，而 $C$ 为字符集大小，本题中 $C=26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def robotWithString(self, s: str) -> str:
        cnt = Counter(s)
        ans = []
        stk = []
        mi = 'a'
        for c in s:
            cnt[c] -= 1
            while mi < 'z' and cnt[mi] == 0:
                mi = chr(ord(mi) + 1)
            stk.append(c)
            while stk and stk[-1] <= mi:
                ans.append(stk.pop())
        return ''.join(ans)
```

```python
class Solution:
    def robotWithString(self, s: str) -> str:
        n = len(s)
        right = [chr(ord('z') + 1)] * (n + 1)
        for i in range(n - 1, -1, -1):
            right[i] = min(s[i], right[i + 1])
        ans = []
        stk = []
        for i, c in enumerate(s):
            stk.append(c)
            while stk and stk[-1] <= right[i + 1]:
                ans.append(stk.pop())
        return ''.join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String robotWithString(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        StringBuilder ans = new StringBuilder();
        Deque<Character> stk = new ArrayDeque<>();
        char mi = 'a';
        for (char c : s.toCharArray()) {
            --cnt[c - 'a'];
            while (mi < 'z' && cnt[mi - 'a'] == 0) {
                ++mi;
            }
            stk.push(c);
            while (!stk.isEmpty() && stk.peek() <= mi) {
                ans.append(stk.pop());
            }
        }
        return ans.toString();
    }
}
```

```java
class Solution {
    public String robotWithString(String s) {
        int n = s.length();
        int[] right = new int[n];
        right[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; --i) {
            right[i] = s.charAt(i) < s.charAt(right[i + 1]) ? i : right[i + 1];
        }
        StringBuilder ans = new StringBuilder();
        Deque<Character> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            stk.push(s.charAt(i));
            while (
                !stk.isEmpty() && (stk.peek() <= (i > n - 2 ? 'z' + 1 : s.charAt(right[i + 1])))) {
                ans.append(stk.pop());
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string robotWithString(string s) {
        int cnt[26] = {0};
        for (char& c : s) ++cnt[c - 'a'];
        char mi = 'a';
        string stk;
        string ans;
        for (char& c : s) {
            --cnt[c - 'a'];
            while (mi < 'z' && cnt[mi - 'a'] == 0) ++mi;
            stk += c;
            while (!stk.empty() && stk.back() <= mi) {
                ans += stk.back();
                stk.pop_back();
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    string robotWithString(string s) {
        int n = s.size();
        vector<int> right(n, n - 1);
        for (int i = n - 2; i >= 0; --i) {
            right[i] = s[i] < s[right[i + 1]] ? i : right[i + 1];
        }
        string ans;
        string stk;
        for (int i = 0; i < n; ++i) {
            stk += s[i];
            while (!stk.empty() && (stk.back() <= (i > n - 2 ? 'z' + 1 : s[right[i + 1]]))) {
                ans += stk.back();
                stk.pop_back();
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func robotWithString(s string) string {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	mi := byte('a')
	stk := []byte{}
	ans := []byte{}
	for i := range s {
		cnt[s[i]-'a']--
		for mi < 'z' && cnt[mi-'a'] == 0 {
			mi++
		}
		stk = append(stk, s[i])
		for len(stk) > 0 && stk[len(stk)-1] <= mi {
			ans = append(ans, stk[len(stk)-1])
			stk = stk[:len(stk)-1]
		}
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function robotWithString(s: string): string {
    let cnt = new Array(128).fill(0);
    for (let c of s) cnt[c.charCodeAt(0)] += 1;
    let min_index = 'a'.charCodeAt(0);
    let ans = [];
    let stack = [];
    for (let c of s) {
        cnt[c.charCodeAt(0)] -= 1;
        while (min_index <= 'z'.charCodeAt(0) && cnt[min_index] == 0) {
            min_index += 1;
        }
        stack.push(c);
        while (
            stack.length > 0 &&
            stack[stack.length - 1].charCodeAt(0) <= min_index
        ) {
            ans.push(stack.pop());
        }
    }
    return ans.join('');
}
```

### **...**

```

```

<!-- tabs:end -->
