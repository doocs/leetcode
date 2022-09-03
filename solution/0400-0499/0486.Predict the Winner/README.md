# [486. 预测赢家](https://leetcode.cn/problems/predict-the-winner)

[English Version](/solution/0400-0499/0486.Predict%20the%20Winner/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。</p>

<p>玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 <code>0</code> 。每一回合，玩家从数组的任意一端取一个数字（即，<code>nums[0]</code> 或 <code>nums[nums.length - 1]</code>），取到的数字将会从数组中移除（数组长度减 <code>1</code> ）。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。</p>

<p>如果玩家 1 能成为赢家，返回 <code>true</code> 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 <code>true</code> 。你可以假设每个玩家的玩法都会使他的分数最大化。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,2]
<strong>输出：</strong>false
<strong>解释：</strong>一开始，玩家 1 可以从 1 和 2 中进行选择。
如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。 
所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
因此，玩家 1 永远不会成为赢家，返回 false 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,233,7]
<strong>输出：</strong>true
<strong>解释：</strong>玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 true，表示玩家 1 可以成为赢家。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 20</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

定义函数 `dfs(i, j)` 表示先手面对数组 `nums[i..j]` 时，能够获得的最大分数。

我们先看后手，后手可能面对的情况有两种，分别是 `nums[i+1..j]` 和 `nums[i..j-1]`，获得的最大分数分别为 `dfs(i+1, j)` 和 `dfs(i, j-1)`。

先手要最大化自己的分数，就要让后手可获得的分数最小，即 `min(dfs(i+1, j), dfs(i, j-1))`。所以先手能获得的分数为 `sum(nums[i..j]) - min(dfs(i+1, j), dfs(i, j-1))`。

记忆化搜索即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def PredictTheWinner(self, nums: List[int]) -> bool:
        @cache
        def dfs(i, j):
            if i > j:
                return 0
            a = min(dfs(i + 1, j), dfs(i, j - 1))
            return s[j + 1] - s[i] - a

        s = list(accumulate(nums, initial=0))
        return dfs(0, len(nums) - 1) * 2 >= s[-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        if ((n & 1) == 0) {
            return true;
        }
        int[] f = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            f[i] = nums[i];
            for (int j = i + 1; j < n; ++j) {
                f[j] = Math.max(nums[i] - f[j], nums[j] - f[j - 1]);
            }
        }
        return f[n - 1] >= 0;
    }
}
```

```java
class Solution {
    private int[] s;
    private int[][] f;

    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        f = new int[n + 1][n + 1];
        for (var e : f) {
            Arrays.fill(e, -1);
        }
        return dfs(0, n - 1) * 2 >= s[n];
    }

    private int dfs(int i, int j) {
        if (i > j) {
            return 0;
        }
        if (f[i][j] != -1) {
            return f[i][j];
        }
        int a = Math.min(dfs(i + 1, j), dfs(i, j - 1));
        int res = s[j + 1] - s[i] - a;
        f[i][j] = res;
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> f;
    vector<int> s;

    bool PredictTheWinner(vector<int>& nums) {
        int n = nums.size();
        s.resize(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        f.assign(n + 1, vector<int>(n + 1, -1));
        return dfs(0, n - 1) * 2 >= s[n];
    }

    int dfs(int i, int j) {
        if (i > j) return 0;
        if (f[i][j] != -1) return f[i][j];
        int a = min(dfs(i + 1, j), dfs(i, j - 1));
        int res = s[j + 1] - s[i] - a;
        f[i][j] = res;
        return res;
    }
};
```

### **Go**

```go
func PredictTheWinner(nums []int) bool {
	n := len(nums)
	s := make([]int, n+1)
	f := make([][]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	for i := range f {
		f[i] = make([]int, n+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		a := min(dfs(i+1, j), dfs(i, j-1))
		f[i][j] = s[j+1] - s[i] - a
		return f[i][j]
	}
	return dfs(0, n-1)*2 >= s[n]
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
