# [753. 破解保险箱](https://leetcode.cn/problems/cracking-the-safe)

[English Version](/solution/0700-0799/0753.Cracking%20the%20Safe/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个需要密码才能打开的保险箱。密码是&nbsp;<code>n</code> 位数, 密码的每一位都是范围&nbsp;<code>[0, k - 1]</code>&nbsp;中的一个数字。</p>

<p>保险箱有一种特殊的密码校验方法，你可以随意输入密码序列，保险箱会自动记住 <strong>最后&nbsp;<code>n</code>&nbsp;位输入</strong> ，如果匹配，则能够打开保险箱。</p>

<ul>
	<li>例如，正确的密码是 <code>"345"</code> ，并且你输入的是 <code>"012345"</code> ：

    <ul>
    	<li>输入 <code>0</code> 之后，最后 <code>3</code> 位输入是 <code>"0"</code> ，不正确。</li>
    	<li>输入 <code>1</code> 之后，最后 <code>3</code> 位输入是 <code>"01"</code> ，不正确。</li>
    	<li>输入 <code>2</code> 之后，最后 <code>3</code> 位输入是 <code>"012"</code> ，不正确。</li>
    	<li>输入 <code>3</code> 之后，最后 <code>3</code> 位输入是 <code>"123"</code> ，不正确。</li>
    	<li>输入 <code>4</code> 之后，最后 <code>3</code> 位输入是 <code>"234"</code> ，不正确。</li>
    	<li>输入 <code>5</code> 之后，最后 <code>3</code> 位输入是 <code>"345"</code> ，正确，打开保险箱。</li>
    </ul>
    </li>

</ul>

<p>在只知道密码位数 <code>n</code> 和范围边界 <code>k</code> 的前提下，请你找出并返回确保在输入的 <strong>某个时刻</strong> 能够打开保险箱的任一 <strong>最短</strong> 密码序列 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1, k = 2
<strong>输出：</strong>"10"
<strong>解释：</strong>密码只有 1 位，所以输入每一位就可以。"01" 也能够确保打开保险箱。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2, k = 2
<strong>输出：</strong>"01100"
<strong>解释：</strong>对于每种可能的密码：
- "00" 从第 4 位开始输入。
- "01" 从第 1 位开始输入。
- "10" 从第 3 位开始输入。
- "11" 从第 2 位开始输入。
因此 "01100" 可以确保打开保险箱。"01100"、"10011" 和 "11001" 也可以确保打开保险箱。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 4</code></li>
	<li><code>1 &lt;= k &lt;= 10</code></li>
	<li><code>1 &lt;= k<sup>n</sup> &lt;= 4096</code></li>
</ul>

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
