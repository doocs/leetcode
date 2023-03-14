# [1240. 铺瓷砖](https://leetcode.cn/problems/tiling-a-rectangle-with-the-fewest-squares)

[English Version](/solution/1200-1299/1240.Tiling%20a%20Rectangle%20with%20the%20Fewest%20Squares/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你是一位施工队的工长，根据设计师的要求准备为一套设计风格独特的房子进行室内装修。</p>

<p>房子的客厅大小为&nbsp;<code>n</code>&nbsp;x <code>m</code>，为保持极简的风格，需要使用尽可能少的 <strong>正方形</strong> 瓷砖来铺盖地面。</p>

<p>假设正方形瓷砖的规格不限，边长都是整数。</p>

<p>请你帮设计师计算一下，最少需要用到多少块方形瓷砖？</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1240.Tiling%20a%20Rectangle%20with%20the%20Fewest%20Squares/images/sample_11_1592.png" style="height: 106px; width: 154px;"></p>

<pre><strong>输入：</strong>n = 2, m = 3
<strong>输出：</strong>3
<code><strong>解释：</strong>3</code> 块地砖就可以铺满卧室。
<code>     2</code> 块 <code>1x1 地砖</code>
<code>     1</code> 块 <code>2x2 地砖</code></pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1240.Tiling%20a%20Rectangle%20with%20the%20Fewest%20Squares/images/sample_22_1592.png" style="height: 126px; width: 224px;"></p>

<pre><strong>输入：</strong>n = 5, m = 8
<strong>输出：</strong>5
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1240.Tiling%20a%20Rectangle%20with%20the%20Fewest%20Squares/images/sample_33_1592.png" style="height: 189px; width: 224px;"></p>

<pre><strong>输入：</strong>n = 11, m = 13
<strong>输出：</strong>6
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 13</code></li>
	<li><code>1 &lt;= m&nbsp;&lt;=&nbsp;13</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归回溯 + 状态压缩**

我们可以按位置进行递归回溯。如果当前位置 $(i, j)$ 已经被填充，则直接递归到下一个位置 $(i, j + 1)$。否则，我们枚举当前位置 $(i, j)$ 可以填充的最大正方形的边长 $w$，并将当前位置 $(i, j)$ 到 $(i + w - 1, j + w - 1)$ 的位置全部填充，然后递归到下一个位置 $(i, j + w)$。在回溯时，我们需要将当前位置 $(i, j)$ 到 $(i + w - 1, j + w - 1)$ 的位置全部清空。

由于每个位置只有两种状态：填充或者未填充，因此我们可以使用一个整数来表示当前位置的状态。我们使用一个长度为 $n$ 的整数数组 `filled`，其中 `filled[i]` 表示第 $i$ 行的状态。如果 `filled[i]` 的第 $j$ 位为 $1$，则表示第 $i$ 行第 $j$ 列已经被填充，否则表示未填充。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def tilingRectangle(self, n: int, m: int) -> int:
        def dfs(i, j, t):
            nonlocal ans
            if j == m:
                i += 1
                j = 0
            if i == n:
                ans = t
                return
            if filled[i] >> j & 1:
                dfs(i, j + 1, t)
            elif t + 1 < ans:
                r = c = 0
                for k in range(i, n):
                    if filled[k] >> j & 1:
                        break
                    r += 1
                for k in range(j, m):
                    if filled[i] >> k & 1:
                        break
                    c += 1
                mx = r if r < c else c
                for w in range(1, mx + 1):
                    for k in range(w):
                        filled[i + w - 1] |= 1 << (j + k)
                        filled[i + k] |= 1 << (j + w - 1)
                    dfs(i, j + w, t + 1)
                for x in range(i, i + mx):
                    for y in range(j, j + mx):
                        filled[x] ^= 1 << y

        ans = n * m
        filled = [0] * n
        dfs(0, 0, 0)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int n;
    private int m;
    private int[] filled;
    private int ans;

    public int tilingRectangle(int n, int m) {
        this.n = n;
        this.m = m;
        ans = n * m;
        filled = new int[n];
        dfs(0, 0, 0);
        return ans;
    }

    private void dfs(int i, int j, int t) {
        if (j == m) {
            ++i;
            j = 0;
        }
        if (i == n) {
            ans = t;
            return;
        }
        if ((filled[i] >> j & 1) == 1) {
            dfs(i, j + 1, t);
        } else if (t + 1 < ans) {
            int r = 0, c = 0;
            for (int k = i; k < n; ++k) {
                if ((filled[k] >> j & 1) == 1) {
                    break;
                }
                ++r;
            }
            for (int k = j; k < m; ++k) {
                if ((filled[i] >> k & 1) == 1) {
                    break;
                }
                ++c;
            }
            int mx = Math.min(r, c);
            for (int w = 1; w <= mx; ++w) {
                for (int k = 0; k < w; ++k) {
                    filled[i + w - 1] |= 1 << (j + k);
                    filled[i + k] |= 1 << (j + w - 1);
                }
                dfs(i, j + w, t + 1);
            }
            for (int x = i; x < i + mx; ++x) {
                for (int y = j; y < j + mx; ++y) {
                    filled[x] ^= 1 << y;
                }
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int tilingRectangle(int n, int m) {
        memset(filled, 0, sizeof(filled));
        this->n = n;
        this->m = m;
        ans = n * m;
        dfs(0, 0, 0);
        return ans;
    }

private:
    int filled[13];
    int n, m;
    int ans;

    void dfs(int i, int j, int t) {
        if (j == m) {
            ++i;
            j = 0;
        }
        if (i == n) {
            ans = t;
            return;
        }
        if (filled[i] >> j & 1) {
            dfs(i, j + 1, t);
        } else if (t + 1 < ans) {
            int r = 0, c = 0;
            for (int k = i; k < n; ++k) {
                if (filled[k] >> j & 1) {
                    break;
                }
                ++r;
            }
            for (int k = j; k < m; ++k) {
                if (filled[i] >> k & 1) {
                    break;
                }
                ++c;
            }
            int mx = min(r, c);
            for (int w = 1; w <= mx; ++w) {
                for (int k = 0; k < w; ++k) {
                    filled[i + w - 1] |= 1 << (j + k);
                    filled[i + k] |= 1 << (j + w - 1);
                }
                dfs(i, j + w, t + 1);
            }
            for (int x = i; x < i + mx; ++x) {
                for (int y = j; y < j + mx; ++y) {
                    filled[x] ^= 1 << y;
                }
            }
        }
    }
};
```

### **Go**

```go
func tilingRectangle(n int, m int) int {
	ans := n * m
	filled := make([]int, n)
	var dfs func(i, j, t int)
	dfs = func(i, j, t int) {
		if j == m {
			i++
			j = 0
		}
		if i == n {
			ans = t
			return
		}
		if filled[i]>>j&1 == 1 {
			dfs(i, j+1, t)
		} else if t+1 < ans {
			var r, c int
			for k := i; k < n; k++ {
				if filled[k]>>j&1 == 1 {
					break
				}
				r++
			}
			for k := j; k < m; k++ {
				if filled[i]>>k&1 == 1 {
					break
				}
				c++
			}
			mx := min(r, c)
			for w := 1; w <= mx; w++ {
				for k := 0; k < w; k++ {
					filled[i+w-1] |= 1 << (j + k)
					filled[i+k] |= 1 << (j + w - 1)
				}
				dfs(i, j+w, t+1)
			}
			for x := i; x < i+mx; x++ {
				for y := j; y < j+mx; y++ {
					filled[x] ^= 1 << y
				}
			}
		}
	}
	dfs(0, 0, 0)
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
