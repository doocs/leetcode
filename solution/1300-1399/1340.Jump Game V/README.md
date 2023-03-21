# [1340. 跳跃游戏 V](https://leetcode.cn/problems/jump-game-v)

[English Version](/solution/1300-1399/1340.Jump%20Game%20V/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>arr</code> 和一个整数&nbsp;<code>d</code> 。每一步你可以从下标&nbsp;<code>i</code>&nbsp;跳到：</p>

<ul>
	<li><code>i + x</code>&nbsp;，其中&nbsp;<code>i + x &lt; arr.length</code>&nbsp;且&nbsp;<code>0 &lt; x &lt;= d</code>&nbsp;。</li>
	<li><code>i - x</code>&nbsp;，其中&nbsp;<code>i - x &gt;= 0</code>&nbsp;且&nbsp;<code>0 &lt; x &lt;= d</code>&nbsp;。</li>
</ul>

<p>除此以外，你从下标&nbsp;<code>i</code> 跳到下标 <code>j</code>&nbsp;需要满足：<code>arr[i] &gt; arr[j]</code>&nbsp;且 <code>arr[i] &gt; arr[k]</code>&nbsp;，其中下标&nbsp;<code>k</code>&nbsp;是所有 <code>i</code>&nbsp;到 <code>j</code>&nbsp;之间的数字（更正式的，<code>min(i, j) &lt; k &lt; max(i, j)</code>）。</p>

<p>你可以选择数组的任意下标开始跳跃。请你返回你 <strong>最多</strong>&nbsp;可以访问多少个下标。</p>

<p>请注意，任何时刻你都不能跳到数组的外面。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1340.Jump%20Game%20V/images/meta-chart.jpeg" style="height: 419px; width: 633px;"></p>

<pre><strong>输入：</strong>arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
<strong>输出：</strong>4
<strong>解释：</strong>你可以从下标 10 出发，然后如上图依次经过 10 --&gt; 8 --&gt; 6 --&gt; 7 。
注意，如果你从下标 6 开始，你只能跳到下标 7 处。你不能跳到下标 5 处因为 13 &gt; 9 。你也不能跳到下标 4 处，因为下标 5 在下标 4 和 6 之间且 13 &gt; 9 。
类似的，你不能从下标 3 处跳到下标 2 或者下标 1 处。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [3,3,3,3,3], d = 3
<strong>输出：</strong>1
<strong>解释：</strong>你可以从任意下标处开始且你永远无法跳到任何其他坐标。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [7,6,5,4,3,2,1], d = 1
<strong>输出：</strong>7
<strong>解释：</strong>从下标 0 处开始，你可以按照数值从大到小，访问所有的下标。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>arr = [7,1,7,1,7,1], d = 2
<strong>输出：</strong>2
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>arr = [66], d = 1
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10^5</code></li>
	<li><code>1 &lt;= d &lt;= arr.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

我们设计一个函数 $dfs(i)$，表示从下标 $i$ 开始跳跃能够访问的最大下标数。我们可以枚举 $i$ 的所有合法的跳跃目标 $j$，即 $i - d \leq j \leq i + d$，并且 $arr[i] \gt arr[j]$。对于每个合法的 $j$，我们可以递归地计算 $dfs(j)$，并取其中的最大值。最终的答案即为所有 $i$ 的 $dfs(i)$ 的最大值。

我们可以使用记忆化搜索来优化这个过程，即使用一个数组 $f$ 记录每个下标的 $dfs$ 值，避免重复计算。

时间复杂度 $O(n \times d)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $arr$ 的长度。

**方法二：排序 + 动态规划**

我们可以将数组 $arr$ 中的每个元素 $x$ 与其下标 $i$ 组成一个元组 $(x, i)$，并将这些元组按照 $x$ 从小到大排序。

接下来定义 $f[i]$ 表示从下标 $i$ 开始跳跃能够访问的最大下标数。初始时 $f[i] = 1$，即每个下标都可以单独作为一次跳跃。

我们可以按照元组 $(x, i)$ 的顺序枚举 $i$，并枚举 $i$ 的所有合法的跳跃目标 $j$，即 $i - d \leq j \leq i + d$，并且 $arr[i] \gt arr[j]$。对于每个合法的 $j$，我们可以更新 $f[i]$ 的值，即 $f[i] = \max(f[i], 1 + f[j])$。

最终的答案即为 $\max_{0 \leq i \lt n} f[i]$。

时间复杂度 $O(n \log n + n \times d)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $arr$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxJumps(self, arr: List[int], d: int) -> int:
        @cache
        def dfs(i):
            ans = 1
            for j in range(i - 1, -1, -1):
                if i - j > d or arr[j] >= arr[i]:
                    break
                ans = max(ans, 1 + dfs(j))
            for j in range(i + 1, n):
                if j - i > d or arr[j] >= arr[i]:
                    break
                ans = max(ans, 1 + dfs(j))
            return ans

        n = len(arr)
        return max(dfs(i) for i in range(n))
```

```python
class Solution:
    def maxJumps(self, arr: List[int], d: int) -> int:
        n = len(arr)
        f = [1] * n
        for x, i in sorted(zip(arr, range(n))):
            for j in range(i - 1, -1, -1):
                if i - j > d or arr[j] >= x:
                    break
                f[i] = max(f[i], 1 + f[j])
            for j in range(i + 1, n):
                if j - i > d or arr[j] >= x:
                    break
                f[i] = max(f[i], 1 + f[j])
        return max(f)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int n;
    private int d;
    private int[] arr;
    private Integer[] f;

    public int maxJumps(int[] arr, int d) {
        n = arr.length;
        this.d = d;
        this.arr = arr;
        f = new Integer[n];
        int ans = 1;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, dfs(i));
        }
        return ans;
    }

    private int dfs(int i) {
        if (f[i] != null) {
            return f[i];
        }
        int ans = 1;
        for (int j = i - 1; j >= 0; --j) {
            if (i - j > d || arr[j] >= arr[i]) {
                break;
            }
            ans = Math.max(ans, 1 + dfs(j));
        }
        for (int j = i + 1; j < n; ++j) {
            if (j - i > d || arr[j] >= arr[i]) {
                break;
            }
            ans = Math.max(ans, 1 + dfs(j));
        }
        return f[i] = ans;
    }
}
```

```java
class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> arr[i] - arr[j]);
        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 0;
        for (int i : idx) {
            for (int j = i - 1; j >= 0; --j) {
                if (i - j > d || arr[j] >= arr[i]) {
                    break;
                }
                f[i] = Math.max(f[i], 1 + f[j]);
            }
            for (int j = i + 1; j < n; ++j) {
                if (j - i > d || arr[j] >= arr[i]) {
                    break;
                }
                f[i] = Math.max(f[i], 1 + f[j]);
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxJumps(vector<int>& arr, int d) {
        int n = arr.size();
        int f[n];
        memset(f, 0, sizeof(f));
        function<int(int)> dfs = [&](int i) -> int {
            if (f[i]) {
                return f[i];
            }
            int ans = 1;
            for (int j = i - 1; j >= 0; --j) {
                if (i - j > d || arr[j] >= arr[i]) {
                    break;
                }
                ans = max(ans, 1 + dfs(j));
            }
            for (int j = i + 1; j < n; ++j) {
                if (j - i > d || arr[j] >= arr[i]) {
                    break;
                }
                ans = max(ans, 1 + dfs(j));
            }
            return f[i] = ans;
        };
        int ans = 1;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, dfs(i));
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxJumps(vector<int>& arr, int d) {
        int n = arr.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) { return arr[i] < arr[j]; });
        vector<int> f(n, 1);
        for (int i : idx) {
            for (int j = i - 1; j >= 0; --j) {
                if (i - j > d || arr[j] >= arr[i]) {
                    break;
                }
                f[i] = max(f[i], 1 + f[j]);
            }
            for (int j = i + 1; j < n; ++j) {
                if (j - i > d || arr[j] >= arr[i]) {
                    break;
                }
                f[i] = max(f[i], 1 + f[j]);
            }
        }
        return *max_element(f.begin(), f.end());
    }
};
```

### **Go**

```go
func maxJumps(arr []int, d int) (ans int) {
	n := len(arr)
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if f[i] != 0 {
			return f[i]
		}
		ans := 1
		for j := i - 1; j >= 0; j-- {
			if i-j > d || arr[j] >= arr[i] {
				break
			}
			ans = max(ans, 1+dfs(j))
		}
		for j := i + 1; j < n; j++ {
			if j-i > d || arr[j] >= arr[i] {
				break
			}
			ans = max(ans, 1+dfs(j))
		}
		f[i] = ans
		return ans
	}
	for i := 0; i < n; i++ {
		ans = max(ans, dfs(i))
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func maxJumps(arr []int, d int) (ans int) {
	n := len(arr)
	idx := make([]int, n)
	f := make([]int, n)
	for i := range f {
		idx[i] = i
		f[i] = 1
	}
	sort.Slice(idx, func(i, j int) bool { return arr[idx[i]] < arr[idx[j]] })
	for _, i := range idx {
		for j := i - 1; j >= 0; j-- {
			if i-j > d || arr[j] >= arr[i] {
				break
			}
			f[i] = max(f[i], 1+f[j])
		}
		for j := i + 1; j < n; j++ {
			if j-i > d || arr[j] >= arr[i] {
				break
			}
			f[i] = max(f[i], 1+f[j])
		}
		ans = max(ans, f[i])
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
