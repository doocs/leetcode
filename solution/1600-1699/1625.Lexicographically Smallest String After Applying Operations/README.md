# [1625. 执行操作后字典序最小的字符串](https://leetcode.cn/problems/lexicographically-smallest-string-after-applying-operations)

[English Version](/solution/1600-1699/1625.Lexicographically%20Smallest%20String%20After%20Applying%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 以及两个整数 <code>a</code> 和 <code>b</code> 。其中，字符串 <code>s</code> 的长度为偶数，且仅由数字 <code>0</code> 到 <code>9</code> 组成。</p>

<p>你可以在 <code>s</code> 上按任意顺序多次执行下面两个操作之一：</p>

<ul>
	<li>累加：将  <code>a</code> 加到 <code>s</code> 中所有下标为奇数的元素上（<strong>下标从 0 开始</strong>）。数字一旦超过 <code>9</code> 就会变成 <code>0</code>，如此循环往复。例如，<code>s = "3456"</code> 且 <code>a = 5</code>，则执行此操作后 <code>s</code> 变成 <code>"3951"</code>。</li>
	<li>轮转：将 <code>s</code> 向右轮转 <code>b</code> 位。例如，<code>s = "3456"</code> 且 <code>b = 1</code>，则执行此操作后 <code>s</code> 变成 <code>"6345"</code>。</li>
</ul>

<p>请你返回在 <code>s</code> 上执行上述操作任意次后可以得到的 <strong>字典序最小</strong> 的字符串。</p>

<p>如果两个字符串长度相同，那么字符串 <code>a</code> 字典序比字符串 <code>b</code> 小可以这样定义：在 <code>a</code> 和 <code>b</code> 出现不同的第一个位置上，字符串 <code>a</code> 中的字符出现在字母表中的时间早于 <code>b</code> 中的对应字符。例如，<code>"0158”</code> 字典序比 <code>"0190"</code> 小，因为不同的第一个位置是在第三个字符，显然 <code>'5'</code> 出现在 <code>'9'</code> 之前。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "5525", a = 9, b = 2
<strong>输出：</strong>"2050"
<strong>解释：</strong>执行操作如下：
初态："5525"
轮转："2555"
累加："2454"
累加："2353"
轮转："5323"
累加："5222"
累加："5121"
轮转："2151"
累加："2050"​​​​​​​​​​​​
无法获得字典序小于 "2050" 的字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "74", a = 5, b = 1
<strong>输出：</strong>"24"
<strong>解释：</strong>执行操作如下：
初态："74"
轮转："47"
累加："42"
轮转："24"​​​​​​​​​​​​
无法获得字典序小于 "24" 的字符串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "0011", a = 4, b = 2
<strong>输出：</strong>"0011"
<strong>解释：</strong>无法获得字典序小于 "0011" 的字符串。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "43987654", a = 7, b = 3
<strong>输出：</strong>"00553311"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= s.length <= 100</code></li>
	<li><code>s.length</code> 是偶数</li>
	<li><code>s</code> 仅由数字 <code>0</code> 到 <code>9</code> 组成</li>
	<li><code>1 <= a <= 9</code></li>
	<li><code>1 <= b <= s.length - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findLexSmallestString(self, s: str, a: int, b: int) -> str:
        q = deque([s])
        vis = {s}
        ans = s
        while q:
            s = q.popleft()
            if s < ans:
                ans = s
            nxt1 = ''.join(
                [str((int(c) + a) % 10) if i & 1 else c for i, c in enumerate(s)]
            )
            nxt2 = s[-b:] + s[:-b]
            for nxt in (nxt1, nxt2):
                if nxt not in vis:
                    vis.add(nxt)
                    q.append(nxt)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        Queue<String> q = new ArrayDeque<>();
        q.offer(s);
        Set<String> vis = new HashSet<>();
        vis.add(s);
        String ans = s;
        while (!q.isEmpty()) {
            s = q.poll();
            if (s.compareTo(ans) < 0) {
                ans = s;
            }
            char[] cs = s.toCharArray();
            for (int i = 1; i < cs.length; i += 2) {
                cs[i] = (char) (((cs[i] - '0' + a) % 10) + '0');
            }
            String nxt1 = String.valueOf(cs);
            String nxt2 = s.substring(b) + s.substring(0, b);
            for (String nxt : new String[]{nxt1, nxt2}) {
                if (!vis.contains(nxt)) {
                    vis.add(nxt);
                    q.offer(nxt);
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
    string findLexSmallestString(string s, int a, int b) {
        unordered_set<string> vis {{s}};
        queue<string> q {{s}};
        string ans = s;
        int n = s.size();
        while (!q.empty()) {
            s = q.front();
            q.pop();
            if (s < ans) ans = s;
            string nxt1 = s;
            for (int i = 1; i < n; i += 2) nxt1[i] = ((nxt1[i] - '0' + a) % 10) + '0';
            string nxt2 = s.substr(n - b) + s.substr(0, n - b);
            for (string nxt : {nxt1, nxt2}) {
                if (!vis.count(nxt)) {
                    vis.insert(nxt);
                    q.push(nxt);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findLexSmallestString(s string, a int, b int) string {
	q := []string{s}
	vis := map[string]bool{s: true}
	ans := s
	for len(q) > 0 {
		s = q[0]
		q = q[1:]
		if s < ans {
			ans = s
		}
		for _, nxt := range []string{op1(s, a), op2(s, b)} {
			if !vis[nxt] {
				vis[nxt] = true
				q = append(q, nxt)
			}
		}
	}
	return ans
}

func op1(s string, a int) string {
	res := []byte(s)
	for i := 1; i < len(s); i += 2 {
		res[i] = byte((int(res[i]-'0')+a)%10 + '0')
	}
	return string(res)
}

func op2(s string, b int) string {
	return s[len(s)-b:] + s[:len(s)-b]
}
```

### **...**

```

```

<!-- tabs:end -->
