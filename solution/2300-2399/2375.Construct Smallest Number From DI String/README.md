# [2375. 根据模式串构造最小数字](https://leetcode.cn/problems/construct-smallest-number-from-di-string)

[English Version](/solution/2300-2399/2375.Construct%20Smallest%20Number%20From%20DI%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你下标从 <strong>0</strong> 开始、长度为 <code>n</code>&nbsp;的字符串&nbsp;<code>pattern</code>&nbsp;，它包含两种字符，<code>'I'</code>&nbsp;表示 <strong>上升</strong>&nbsp;，<code>'D'</code>&nbsp;表示 <strong>下降</strong>&nbsp;。</p>

<p>你需要构造一个下标从 <strong>0</strong>&nbsp;开始长度为&nbsp;<code>n + 1</code>&nbsp;的字符串，且它要满足以下条件：</p>

<ul>
	<li><code>num</code>&nbsp;包含数字&nbsp;<code>'1'</code>&nbsp;到&nbsp;<code>'9'</code>&nbsp;，其中每个数字&nbsp;<strong>至多</strong>&nbsp;使用一次。</li>
	<li>如果&nbsp;<code>pattern[i] == 'I'</code>&nbsp;，那么&nbsp;<code>num[i] &lt; num[i + 1]</code>&nbsp;。</li>
	<li>如果&nbsp;<code>pattern[i] == 'D'</code>&nbsp;，那么&nbsp;<code>num[i] &gt; num[i + 1]</code>&nbsp;。</li>
</ul>

<p>请你返回满足上述条件字典序 <strong>最小</strong>&nbsp;的字符串<em>&nbsp;</em><code>num</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>pattern = "IIIDIDDD"
<b>输出：</b>"123549876"
<strong>解释：
</strong>下标 0 ，1 ，2 和 4 处，我们需要使 num[i] &lt; num[i+1] 。
下标 3 ，5 ，6 和 7 处，我们需要使 num[i] &gt; num[i+1] 。
一些可能的 num 的值为 "245639871" ，"135749862" 和 "123849765" 。
"123549876" 是满足条件最小的数字。
注意，"123414321" 不是可行解因为数字 '1' 使用次数超过 1 次。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>pattern = "DDD"
<b>输出：</b>"4321"
<strong>解释：</strong>
一些可能的 num 的值为 "9876" ，"7321" 和 "8742" 。
"4321" 是满足条件最小的数字。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= pattern.length &lt;= 8</code></li>
	<li><code>pattern</code>&nbsp;只包含字符&nbsp;<code>'I'</code> 和&nbsp;<code>'D'</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

定义 $dfs(u)$，其中 $u$ 表示当前答案字符串的长度。从 $u=0$ 开始搜索，直至找到第一个符合条件的字符串。

时间复杂度 $O(n!)$，空间复杂度 $O(n)$。其中 $n$ 表示字符串 $pattern$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestNumber(self, pattern: str) -> str:
        def dfs(u):
            nonlocal ans
            if ans:
                return
            if u == len(pattern) + 1:
                ans = ''.join(t)
                return
            for i in range(1, 10):
                if not vis[i]:
                    if u and pattern[u - 1] == 'I' and int(t[-1]) >= i:
                        continue
                    if u and pattern[u - 1] == 'D' and int(t[-1]) <= i:
                        continue
                    vis[i] = True
                    t.append(str(i))
                    dfs(u + 1)
                    vis[i] = False
                    t.pop()

        vis = [False] * 10
        t = []
        ans = None
        dfs(0)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private boolean[] vis = new boolean[10];
    private StringBuilder t = new StringBuilder();
    private String p;
    private String ans;

    public String smallestNumber(String pattern) {
        p = pattern;
        dfs(0);
        return ans;
    }

    private void dfs(int u) {
        if (ans != null) {
            return;
        }
        if (u == p.length() + 1) {
            ans = t.toString();
            return;
        }
        for (int i = 1; i < 10; ++i) {
            if (!vis[i]) {
                if (u > 0 && p.charAt(u - 1) == 'I' && t.charAt(u - 1) - '0' >= i) {
                    continue;
                }
                if (u > 0 && p.charAt(u - 1) == 'D' && t.charAt(u - 1) - '0' <= i) {
                    continue;
                }
                vis[i] = true;
                t.append(i);
                dfs(u + 1);
                t.deleteCharAt(t.length() - 1);
                vis[i] = false;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string ans = "";
    string pattern;
    vector<bool> vis;
    string t = "";

    string smallestNumber(string pattern) {
        this->pattern = pattern;
        vis.assign(10, false);
        dfs(0);
        return ans;
    }

    void dfs(int u) {
        if (ans != "") return;
        if (u == pattern.size() + 1) {
            ans = t;
            return;
        }
        for (int i = 1; i < 10; ++i) {
            if (!vis[i]) {
                if (u && pattern[u - 1] == 'I' && t.back() - '0' >= i) continue;
                if (u && pattern[u - 1] == 'D' && t.back() - '0' <= i) continue;
                vis[i] = true;
                t += to_string(i);
                dfs(u + 1);
                t.pop_back();
                vis[i] = false;
            }
        }
    }
};
```

### **Go**

```go
func smallestNumber(pattern string) string {
	vis := make([]bool, 10)
	t := []byte{}
	ans := ""
	var dfs func(u int)
	dfs = func(u int) {
		if ans != "" {
			return
		}
		if u == len(pattern)+1 {
			ans = string(t)
			return
		}
		for i := 1; i < 10; i++ {
			if !vis[i] {
				if u > 0 && pattern[u-1] == 'I' && int(t[len(t)-1]-'0') >= i {
					continue
				}
				if u > 0 && pattern[u-1] == 'D' && int(t[len(t)-1]-'0') <= i {
					continue
				}
				vis[i] = true
				t = append(t, byte('0'+i))
				dfs(u + 1)
				vis[i] = false
				t = t[:len(t)-1]
			}
		}
	}
	dfs(0)
	return ans
}
```

### **TypeScript**

```ts
function smallestNumber(pattern: string): string {
    const n = pattern.length;
    const res = new Array(n + 1).fill('');
    const vis = new Array(n + 1).fill(false);
    const dfs = (i: number, num: number) => {
        if (i === n) {
            return;
        }

        if (vis[num]) {
            vis[num] = false;
            if (pattern[i] === 'I') {
                dfs(i - 1, num - 1);
            } else {
                dfs(i - 1, num + 1);
            }
            return;
        }

        vis[num] = true;
        res[i] = num;

        if (pattern[i] === 'I') {
            for (let j = res[i] + 1; j <= n + 1; j++) {
                if (!vis[j]) {
                    dfs(i + 1, j);
                    return;
                }
            }
            vis[num] = false;
            dfs(i, num - 1);
        } else {
            for (let j = res[i] - 1; j > 0; j--) {
                if (!vis[j]) {
                    dfs(i + 1, j);
                    return;
                }
            }
            vis[num] = false;
            dfs(i, num + 1);
        }
    };
    dfs(0, 1);
    for (let i = 1; i <= n + 1; i++) {
        if (!vis[i]) {
            res[n] = i;
            break;
        }
    }

    return res.join('');
}
```

### **...**

```


```

<!-- tabs:end -->
