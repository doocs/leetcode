# [2552. 统计上升四元组](https://leetcode.cn/problems/count-increasing-quadruplets)

[English Version](/solution/2500-2599/2552.Count%20Increasing%20Quadruplets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，它包含&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code>&nbsp;的所有数字，请你返回上升四元组的数目。</p>

<p>如果一个四元组&nbsp;<code>(i, j, k, l)</code>&nbsp;满足以下条件，我们称它是上升的：</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; k &lt; l &lt; n</code>&nbsp;且</li>
	<li><code>nums[i] &lt; nums[k] &lt; nums[j] &lt; nums[l]</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,3,2,4,5]
<b>输出：</b>2
<b>解释：</b>
- 当 i = 0 ，j = 1 ，k = 2 且 l = 3 时，有 nums[i] &lt; nums[k] &lt; nums[j] &lt; nums[l] 。
- 当 i = 0 ，j = 1 ，k = 2 且 l = 4 时，有 nums[i] &lt; nums[k] &lt; nums[j] &lt; nums[l] 。
没有其他的四元组，所以我们返回 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,4]
<b>输出：</b>0
<b>解释：</b>只存在一个四元组 i = 0 ，j = 1 ，k = 2 ，l = 3 ，但是 nums[j] &lt; nums[k] ，所以我们返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>4 &lt;= nums.length &lt;= 4000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= nums.length</code></li>
	<li><code>nums</code>&nbsp;中所有数字 <strong>互不相同</strong>&nbsp;，<code>nums</code>&nbsp;是一个排列。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举 + 预处理**

我们可以枚举四元组中的 $j$ 和 $k$，那么问题转化为，对于当前的 $j$ 和 $k$：

-   统计有多少个 $l$ 满足 $l \gt k$ 且 $nums[l] \gt nums[j]$；
-   统计有多少个 $i$ 满足 $i \lt j$ 且 $nums[i] \lt nums[k]$。

我们可以使用两个二维数组 $f$ 和 $g$ 分别记录这两个信息。其中 $f[j][k]$ 表示有多少个 $l$ 满足 $l \gt k$ 且 $nums[l] \gt nums[j]$，而 $g[j][k]$ 表示有多少个 $i$ 满足 $i \lt j$ 且 $nums[i] \lt nums[k]$。

那么答案就是所有的 $f[j][k] \times g[j][k]$ 的和。

时间复杂度为 $O(n^2)$，空间复杂度为 $O(n^2)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countQuadruplets(self, nums: List[int]) -> int:
        n = len(nums)
        f = [[0] * n for _ in range(n)]
        g = [[0] * n for _ in range(n)]
        for j in range(1, n - 2):
            cnt = sum(nums[l] > nums[j] for l in range(j + 1, n))
            for k in range(j + 1, n - 1):
                if nums[j] > nums[k]:
                    f[j][k] = cnt
                else:
                    cnt -= 1
        for k in range(2, n - 1):
            cnt = sum(nums[i] < nums[k] for i in range(k))
            for j in range(k - 1, 0, -1):
                if nums[j] > nums[k]:
                    g[j][k] = cnt
                else:
                    cnt -= 1
        return sum(f[j][k] * g[j][k] for j in range(1, n - 2) for k in range(j + 1, n - 1))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n][n];
        int[][] g = new int[n][n];
        for (int j = 1; j < n - 2; ++j) {
            int cnt = 0;
            for (int l = j + 1; l < n; ++l) {
                if (nums[l] > nums[j]) {
                    ++cnt;
                }
            }
            for (int k = j + 1; k < n - 1; ++k) {
                if (nums[j] > nums[k]) {
                    f[j][k] = cnt;
                } else {
                    --cnt;
                }
            }
        }
        long ans = 0;
        for (int k = 2; k < n - 1; ++k) {
            int cnt = 0;
            for (int i = 0; i < k; ++i) {
                if (nums[i] < nums[k]) {
                    ++cnt;
                }
            }
            for (int j = k - 1; j > 0; --j) {
                if (nums[j] > nums[k]) {
                    g[j][k] = cnt;
                    ans += (long) f[j][k] * g[j][k];
                } else {
                    --cnt;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
const int N = 4001;
int f[N][N];
int g[N][N];

class Solution {
public:
    long long countQuadruplets(vector<int>& nums) {
        int n = nums.size();
        memset(f, 0, sizeof f);
        memset(g, 0, sizeof g);
        for (int j = 1; j < n - 2; ++j) {
            int cnt = 0;
            for (int l = j + 1; l < n; ++l) {
                if (nums[l] > nums[j]) {
                    ++cnt;
                }
            }
            for (int k = j + 1; k < n - 1; ++k) {
                if (nums[j] > nums[k]) {
                    f[j][k] = cnt;
                } else {
                    --cnt;
                }
            }
        }
        long long ans = 0;
        for (int k = 2; k < n - 1; ++k) {
            int cnt = 0;
            for (int i = 0; i < k; ++i) {
                if (nums[i] < nums[k]) {
                    ++cnt;
                }
            }
            for (int j = k - 1; j > 0; --j) {
                if (nums[j] > nums[k]) {
                    g[j][k] = cnt;
                    ans += 1ll * f[j][k] * g[j][k];
                } else {
                    --cnt;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countQuadruplets(nums []int) int64 {
	n := len(nums)
	f := make([][]int, n)
	g := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		g[i] = make([]int, n)
	}
	for j := 1; j < n-2; j++ {
		cnt := 0
		for l := j + 1; l < n; l++ {
			if nums[l] > nums[j] {
				cnt++
			}
		}
		for k := j + 1; k < n-1; k++ {
			if nums[j] > nums[k] {
				f[j][k] = cnt
			} else {
				cnt--
			}
		}
	}
	ans := 0
	for k := 2; k < n-1; k++ {
		cnt := 0
		for i := 0; i < k; i++ {
			if nums[i] < nums[k] {
				cnt++
			}
		}
		for j := k - 1; j > 0; j-- {
			if nums[j] > nums[k] {
				g[j][k] = cnt
				ans += f[j][k] * g[j][k]
			} else {
				cnt--
			}
		}
	}
	return int64(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
