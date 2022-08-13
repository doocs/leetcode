# [854. 相似度为 K 的字符串](https://leetcode.cn/problems/k-similar-strings)

[English Version](/solution/0800-0899/0854.K-Similar%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>字符串 <code>s1</code> 和 <code>s2</code> 是 <strong><code>k</code> 相似</strong> 的(对于某些非负整数 <code>k</code> )，如果我们可以交换 <code>s1</code> 中两个字母的位置正好 <code>k</code> 次，使结果字符串等于 <code>s2</code> 。</p>

<p>给定两个字谜游戏 <code>s1</code> 和 <code>s2</code> ，返回 <code>s1</code> 和 <code>s2</code> 与 <strong><code>k</code> 相似&nbsp;</strong>的最小 <code>k</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s1 = "ab", s2 = "ba"
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1 = "abc", s2 = "bca"
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length &lt;= 20</code></li>
	<li><code>s2.length == s1.length</code></li>
	<li><code>s1</code>&nbsp;和&nbsp;<code>s2</code>&nbsp;&nbsp;只包含集合&nbsp;<code>{'a', 'b', 'c', 'd', 'e', 'f'}</code>&nbsp;中的小写字母</li>
	<li><code>s2</code> 是 <code>s1</code> 的一个字谜</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def kSimilarity(self, s1: str, s2: str) -> int:
        def next(s):
            i = 0
            res = []
            while s[i] == s2[i]:
                i += 1
            for j in range(i + 1, n):
                if s[j] == s2[i] and s[j] != s2[j]:
                    res.append(s[:i] + s[j] + s[i + 1 : j] + s[i] + s[j + 1 :])
            return res

        q = deque([s1])
        vis = {s1}
        ans, n = 0, len(s1)
        while q:
            for _ in range(len(q)):
                s = q.popleft()
                if s == s2:
                    return ans
                for nxt in next(s):
                    if nxt not in vis:
                        vis.add(nxt)
                        q.append(nxt)
            ans += 1
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int kSimilarity(String s1, String s2) {
        Deque<String> q = new ArrayDeque<>();
        Set<String> vis = new HashSet<>();
        q.offer(s1);
        vis.add(s1);
        int ans = 0;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; --i) {
                s1 = q.poll();
                if (s1.equals(s2)) {
                    return ans;
                }
                for (String nxt : next(s1, s2)) {
                    if (!vis.contains(nxt)) {
                        vis.add(nxt);
                        q.offer(nxt);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }

    private List<String> next(String s, String s2) {
        int i = 0;
        int n = s.length();
        for (; i < n && s.charAt(i) == s2.charAt(i); ++i);
        char[] cs = s.toCharArray();
        List<String> res = new ArrayList<>();
        for (int j = i + 1; j < n; ++j) {
            if (cs[j] == s2.charAt(i) && cs[j] != s2.charAt(j)) {
                swap(cs, i, j);
                res.add(new String(cs));
                swap(cs, i, j);
            }
        }
        return res;
    }

    private void swap(char[] cs, int i, int j) {
        char t = cs[i];
        cs[i] = cs[j];
        cs[j] = t;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int kSimilarity(string s1, string s2) {
        queue<string> q {{s1}};
        unordered_set<string> vis {{s1}};
        int ans = 0;
        while (!q.empty()) {
            for (int i = q.size(); i; --i) {
                s1 = q.front();
                q.pop();
                if (s1 == s2) return ans;
                for (string nxt : next(s1, s2)) {
                    if (!vis.count(nxt)) {
                        vis.insert(nxt);
                        q.push(nxt);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }

    vector<string> next(string& s, string& s2) {
        int i = 0, n = s.size();
        for (; i < n && s[i] == s2[i]; ++i)
            ;
        vector<string> res;
        for (int j = i + 1; j < n; ++j) {
            if (s[j] == s2[i] && s[j] != s2[j]) {
                swap(s[i], s[j]);
                res.push_back(s);
                swap(s[i], s[j]);
            }
        }
        return res;
    }
};
```

### **Go**

```go
func kSimilarity(s1 string, s2 string) int {
	next := func(s string) []string {
		i := 0
		res := []string{}
		for s[i] == s2[i] {
			i++
		}
		for j := i + 1; j < len(s1); j++ {
			if s[j] == s2[i] && s[j] != s2[j] {
				res = append(res, s[0:i]+string(s[j])+s[i+1:j]+string(s[i])+s[j+1:])
			}
		}
		return res
	}

	q := []string{s1}
	vis := map[string]bool{s1: true}
	ans := 0
	for len(q) > 0 {
		for i := len(q); i > 0; i-- {
			s1 = q[0]
			q = q[1:]
			if s1 == s2 {
				return ans
			}
			for _, nxt := range next(s1) {
				if !vis[nxt] {
					vis[nxt] = true
					q = append(q, nxt)
				}
			}
		}
		ans++
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
