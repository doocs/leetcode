# [1643. 第 K 条最小指令](https://leetcode.cn/problems/kth-smallest-instructions)

[English Version](/solution/1600-1699/1643.Kth%20Smallest%20Instructions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Bob 站在单元格 <code>(0, 0)</code> ，想要前往目的地 <code>destination</code> ：<code>(row, column)</code> 。他只能向 <strong>右</strong> 或向 <strong>下</strong> 走。你可以为 Bob 提供导航 <strong>指令</strong> 来帮助他到达目的地 <code>destination</code> 。</p>

<p><strong>指令</strong> 用字符串表示，其中每个字符：</p>

<ul>
	<li><code>'H'</code> ，意味着水平向右移动</li>
	<li><code>'V'</code> ，意味着竖直向下移动</li>
</ul>

<p>能够为 Bob 导航到目的地 <code>destination</code> 的指令可以有多种，例如，如果目的地 <code>destination</code> 是 <code>(2, 3)</code>，<code>"HHHVV"</code> 和 <code>"HVHVH"</code> 都是有效<strong> 指令</strong> 。</p>

<ul>
</ul>

<p>然而，Bob 很挑剔。因为他的幸运数字是 <code>k</code>，他想要遵循 <strong>按字典序排列后的第 <code>k</code> 条最小指令 </strong>的导航前往目的地 <code>destination</code> 。<code>k</code>  的编号 <strong>从 1 开始</strong> 。</p>

<p>给你一个整数数组 <code>destination</code> 和一个整数 <code>k</code> ，请你返回可以为<em> </em>Bob<em> </em>提供前往目的地 <code>destination</code> 导航的<strong> 按字典序排列后的第 <code>k</code> 条最小指令 </strong>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1643.Kth%20Smallest%20Instructions/images/ex1.png" style="width: 300px;" /></p>

<pre>
<strong>输入：</strong>destination = [2,3], k = 1
<strong>输出：</strong>"HHHVV"
<strong>解释：</strong>能前往 (2, 3) 的所有导航指令 <strong>按字典序排列后</strong> 如下所示：
["HHHVV", "HHVHV", "HHVVH", "HVHHV", "HVHVH", "HVVHH", "VHHHV", "VHHVH", "VHVHH", "VVHHH"].
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1643.Kth%20Smallest%20Instructions/images/ex2.png" style="width: 300px; height: 229px;" /></strong></p>

<pre>
<strong>输入：</strong>destination = [2,3], k = 2
<strong>输出：</strong>"HHVHV"
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1643.Kth%20Smallest%20Instructions/images/ex3.png" style="width: 300px; height: 229px;" /></strong></p>

<pre>
<strong>输入：</strong>destination = [2,3], k = 3
<strong>输出：</strong>"HHVVH"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>destination.length == 2</code></li>
	<li><code>1 <= row, column <= 15</code></li>
	<li><code>1 <= k <= nCr(row + column, row)</code>，其中 <code>nCr(a, b)</code> 表示组合数，即从 <code>a</code> 个物品中选 <code>b</code> 个物品的不同方案数。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：组合计数**

根据题目描述我们可以知道，最终的路径是由 $destination[0]$ 个 `'V'` 和 $destination[1]$ 个 `'H'` 组成的，且按字典序排列后的第 $k$ 条最小指令。

我们首先考虑字典序的最高位，即最左边的字符。如果高位字符是 `'V'`，那么所有以 `'H'` 开头的路径的字典序都比它小，而以 `'H'` 开头的路径总数为 $x = C_{v+h-1}^{h-1}$。

如果 $k \lt x$，那么高位字符一定是 `'V'`，我们将 $k$ 减去 $x$，并将 $v$ 减 $1$，然后继续考虑下一位字符；否则，高位字符一定是 `'H'`，我们将 $h$ 减 $1$，然后继续考虑下一位字符。

注意，如果 $h = 0$，那么高位字符一定是 `'V'`，因为剩下的字符都是 `'V'`。

问题可以转换为求解 $C_{n}^{k}$，我们可以通过公式 $C_{n}^{k} = C_{n-1}^{k-1} + C_{n-1}^{k}$ 递推求解。

时间复杂度 $O((h + v) \times h)$，空间复杂度 $O((h + v) \times h)$。其中 $h$ 和 $v$ 分别为 $destination[1]$ 和 $destination[0]$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def kthSmallestPath(self, destination: List[int], k: int) -> str:
        v, h = destination
        ans = []
        for _ in range(h + v):
            if h == 0:
                ans.append("V")
            else:
                x = comb(h + v - 1, h - 1)
                if k > x:
                    ans.append("V")
                    v -= 1
                    k -= x
                else:
                    ans.append("H")
                    h -= 1
        return "".join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String kthSmallestPath(int[] destination, int k) {
        int v = destination[0], h = destination[1];
        int n = v + h;
        int[][] c = new int[n + 1][h + 1];
        c[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= h; ++j) {
                c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = n; i > 0; --i) {
            if (h == 0) {
                ans.append('V');
            } else {
                int x = c[v + h - 1][h - 1];
                if (k > x) {
                    ans.append('V');
                    k -= x;
                    --v;
                } else {
                    ans.append('H');
                    --h;
                }
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
    string kthSmallestPath(vector<int>& destination, int k) {
        int v = destination[0], h = destination[1];
        int n = v + h;
        int c[n + 1][h + 1];
        memset(c, 0, sizeof(c));
        c[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= h; ++j) {
                c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
            }
        }
        string ans;
        for (int i = 0; i < n; ++i) {
            if (h == 0) {
                ans.push_back('V');
            } else {
                int x = c[v + h - 1][h - 1];
                if (k > x) {
                    ans.push_back('V');
                    --v;
                    k -= x;
                } else {
                    ans.push_back('H');
                    --h;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func kthSmallestPath(destination []int, k int) string {
	v, h := destination[0], destination[1]
	n := v + h
	c := make([][]int, n+1)
	for i := range c {
		c[i] = make([]int, h+1)
		c[i][0] = 1
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= h; j++ {
			c[i][j] = c[i-1][j] + c[i-1][j-1]
		}
	}
	ans := []byte{}
	for i := 0; i < n; i++ {
		if h == 0 {
			ans = append(ans, 'V')
		} else {
			x := c[v+h-1][h-1]
			if k > x {
				ans = append(ans, 'V')
				k -= x
				v--
			} else {
				ans = append(ans, 'H')
				h--
			}
		}
	}
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
