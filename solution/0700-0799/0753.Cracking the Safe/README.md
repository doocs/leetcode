# [753. 破解保险箱](https://leetcode.cn/problems/cracking-the-safe)

[English Version](/solution/0700-0799/0753.Cracking%20the%20Safe/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个需要密码才能打开的保险箱。密码是&nbsp;<code>n</code> 位数, 密码的每一位是&nbsp;<code>k</code>&nbsp;位序列&nbsp;<code>0, 1, ..., k-1</code>&nbsp;中的一个 。</p>

<p>你可以随意输入密码，保险箱会自动记住最后&nbsp;<code>n</code>&nbsp;位输入，如果匹配，则能够打开保险箱。</p>

<p>举个例子，假设密码是&nbsp;<code>&quot;345&quot;</code>，你可以输入&nbsp;<code>&quot;012345&quot;</code>&nbsp;来打开它，只是你输入了 6&nbsp;个字符.</p>

<p>请返回一个能打开保险箱的最短字符串。</p>

<p>&nbsp;</p>

<p><strong>示例1:</strong></p>

<pre><strong>输入:</strong> n = 1, k = 2
<strong>输出:</strong> &quot;01&quot;
<strong>说明:</strong> &quot;10&quot;也可以打开保险箱。
</pre>

<p>&nbsp;</p>

<p><strong>示例2:</strong></p>

<pre><strong>输入:</strong> n = 2, k = 2
<strong>输出:</strong> &quot;00110&quot;
<strong>说明: </strong>&quot;01100&quot;, &quot;10011&quot;, &quot;11001&quot; 也能打开保险箱。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>n</code> 的范围是&nbsp;<code>[1, 4]</code>。</li>
	<li><code>k</code> 的范围是&nbsp;<code>[1, 10]</code>。</li>
	<li><code>k^n</code> 最大可能为&nbsp;<code>4096</code>。</li>
</ol>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：欧拉回路**

我们可以对题目中所描述的内容构建有向图：将每个点看作一个长度为 $n-1$ 的 $k$ 字符串，每条边都带有一个从 $0$ 到 $k-1$ 的字符。如果点 $u$ 到点 $v$ 之间有一条有向边 $e$，假设 $e$ 携带的字符为 $c$，那么 $u+c$ 的末尾 $k-1$ 个字符形成的字符串等于 $v$，此时边 $u+c$ 就表示了一个长度为 $n$ 的密码。

在这个有向图中，一共有 $k^{n-1}$ 个点，每个点都有 $k$ 条出边，也有 $k$ 条入边，因此，该有向图存在欧拉回路，欧拉回路所经过的路径拼接起来就是题目中的答案。

时间复杂度 $O(k^n)$，空间复杂度 $O(k^n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def crackSafe(self, n: int, k: int) -> str:
        def dfs(u):
            for x in range(k):
                e = u * 10 + x
                if e not in vis:
                    vis.add(e)
                    v = e % mod
                    dfs(v)
                    ans.append(str(x))

        mod = 10 ** (n - 1)
        vis = set()
        ans = []
        dfs(0)
        ans.append("0" * (n - 1))
        return "".join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Set<Integer> vis = new HashSet<>();
    private StringBuilder ans = new StringBuilder();
    private int mod;

    public String crackSafe(int n, int k) {
        mod = (int) Math.pow(10, n - 1);
        dfs(0, k);
        ans.append("0".repeat(n - 1));
        return ans.toString();
    }

    private void dfs(int u, int k) {
        for (int x = 0; x < k; ++x) {
            int e = u * 10 + x;
            if (vis.add(e)) {
                int v = e % mod;
                dfs(v, k);
                ans.append(x);
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string crackSafe(int n, int k) {
        unordered_set<int> vis;
        int mod = pow(10, n - 1);
        string ans;
        function<void(int)> dfs = [&](int u) {
            for (int x = 0; x < k; ++x) {
                int e = u * 10 + x;
                if (!vis.count(e)) {
                    vis.insert(e);
                    dfs(e % mod);
                    ans += (x + '0');
                }
            }
        };
        dfs(0);
        ans += string(n - 1, '0');
        return ans;
    }
};
```

### **Go**

```go
func crackSafe(n int, k int) string {
	mod := int(math.Pow(10, float64(n-1)))
	vis := map[int]bool{}
	ans := &strings.Builder{}
	var dfs func(int)
	dfs = func(u int) {
		for x := 0; x < k; x++ {
			e := u*10 + x
			if !vis[e] {
				vis[e] = true
				v := e % mod
				dfs(v)
				ans.WriteByte(byte('0' + x))
			}
		}
	}
	dfs(0)
	ans.WriteString(strings.Repeat("0", n-1))
	return ans.String()
}
```

### **...**

```

```

<!-- tabs:end -->
