# [2375. Construct Smallest Number From DI String](https://leetcode.com/problems/construct-smallest-number-from-di-string)

[中文文档](/solution/2300-2399/2375.Construct%20Smallest%20Number%20From%20DI%20String/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>pattern</code> of length <code>n</code> consisting of the characters <code>&#39;I&#39;</code> meaning <strong>increasing</strong> and <code>&#39;D&#39;</code> meaning <strong>decreasing</strong>.</p>

<p>A <strong>0-indexed</strong> string <code>num</code> of length <code>n + 1</code> is created using the following conditions:</p>

<ul>
	<li><code>num</code> consists of the digits <code>&#39;1&#39;</code> to <code>&#39;9&#39;</code>, where each digit is used <strong>at most</strong> once.</li>
	<li>If <code>pattern[i] == &#39;I&#39;</code>, then <code>num[i] &lt; num[i + 1]</code>.</li>
	<li>If <code>pattern[i] == &#39;D&#39;</code>, then <code>num[i] &gt; num[i + 1]</code>.</li>
</ul>

<p>Return <em>the lexicographically <strong>smallest</strong> possible string </em><code>num</code><em> that meets the conditions.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> pattern = &quot;IIIDIDDD&quot;
<strong>Output:</strong> &quot;123549876&quot;
<strong>Explanation:
</strong>At indices 0, 1, 2, and 4 we must have that num[i] &lt; num[i+1].
At indices 3, 5, 6, and 7 we must have that num[i] &gt; num[i+1].
Some possible values of num are &quot;245639871&quot;, &quot;135749862&quot;, and &quot;123849765&quot;.
It can be proven that &quot;123549876&quot; is the smallest possible num that meets the conditions.
Note that &quot;123414321&quot; is not possible because the digit &#39;1&#39; is used more than once.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> pattern = &quot;DDD&quot;
<strong>Output:</strong> &quot;4321&quot;
<strong>Explanation:</strong>
Some possible values of num are &quot;9876&quot;, &quot;7321&quot;, and &quot;8742&quot;.
It can be proven that &quot;4321&quot; is the smallest possible num that meets the conditions.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= pattern.length &lt;= 8</code></li>
	<li><code>pattern</code> consists of only the letters <code>&#39;I&#39;</code> and <code>&#39;D&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
