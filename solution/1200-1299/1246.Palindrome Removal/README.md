# [1246. 删除回文子数组](https://leetcode.cn/problems/palindrome-removal)

[English Version](/solution/1200-1299/1246.Palindrome%20Removal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>arr</code>，每一次操作你都可以选择并删除它的一个 <strong>回文</strong> 子数组&nbsp;<code>arr[i], arr[i+1], ..., arr[j]</code>（ <code>i &lt;= j</code>）。</p>

<p>注意，每当你删除掉一个子数组，右侧元素都会自行向前移动填补空位。</p>

<p>请你计算并返回从数组中删除所有数字所需的最少操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [1,2]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1,3,4,1,5]
<strong>输出：</strong>3
<strong>解释：</strong>先删除 [4]，然后删除 [1,3,1]，最后再删除 [5]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 100</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 20</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划（区间 DP）**

我们定义 $f[i][j]$ 表示删除下标区间 $[i,..j]$ 内的所有数字所需的最少操作次数。初始时 $f[i][i] = 1$，表示只有一个数字时，需要执行一次删除操作。

对于 $f[i][j]$，如果 $i + 1 = j$，即只有两个数字时，如果 $arr[i]=arr[j]$，则 $f[i][j] = 1$，否则 $f[i][j] = 2$。

对于超过两个数字的情况，如果 $arr[i]=arr[j]$，那么 $f[i][j]$ 可以取 $f[i + 1][j - 1]$，或者我们可以在下标范围 $[i,..j-1]$ 范围内枚举 $k$，取 $f[i][k] + f[k + 1][j]$ 的最小值。将最小值赋给 $f[i][j]$。

答案即为 $f[0][n - 1]$。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumMoves(self, arr: List[int]) -> int:
        n = len(arr)
        f = [[0] * n for _ in range(n)]
        for i in range(n):
            f[i][i] = 1
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                if i + 1 == j:
                    f[i][j] = 1 if arr[i] == arr[j] else 2
                else:
                    t = f[i + 1][j - 1] if arr[i] == arr[j] else inf
                    for k in range(i, j):
                        t = min(t, f[i][k] + f[k + 1][j])
                    f[i][j] = t
        return f[0][n - 1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumMoves(int[] arr) {
        int n = arr.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][i] = 1;
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (i + 1 == j) {
                    f[i][j] = arr[i] == arr[j] ? 1 : 2;
                } else {
                    int t = arr[i] == arr[j] ? f[i + 1][j - 1] : 1 << 30;
                    for (int k = i; k < j; ++k) {
                        t = Math.min(t, f[i][k] + f[k + 1][j]);
                    }
                    f[i][j] = t;
                }
            }
        }
        return f[0][n - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumMoves(vector<int>& arr) {
        int n = arr.size();
        int f[n][n];
        memset(f, 0, sizeof f);
        for (int i = 0; i < n; ++i) {
            f[i][i] = 1;
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (i + 1 == j) {
                    f[i][j] = arr[i] == arr[j] ? 1 : 2;
                } else {
                    int t = arr[i] == arr[j] ? f[i + 1][j - 1] : 1 << 30;
                    for (int k = i; k < j; ++k) {
                        t = min(t, f[i][k] + f[k + 1][j]);
                    }
                    f[i][j] = t;
                }
            }
        }
        return f[0][n - 1];
    }
};
```

### **Go**

```go
func minimumMoves(arr []int) int {
	n := len(arr)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		f[i][i] = 1
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if i+1 == j {
				f[i][j] = 2
				if arr[i] == arr[j] {
					f[i][j] = 1
				}
			} else {
				t := 1 << 30
				if arr[i] == arr[j] {
					t = f[i+1][j-1]
				}
				for k := i; k < j; k++ {
					t = min(t, f[i][k]+f[k+1][j])
				}
				f[i][j] = t
			}
		}
	}
	return f[0][n-1]
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
