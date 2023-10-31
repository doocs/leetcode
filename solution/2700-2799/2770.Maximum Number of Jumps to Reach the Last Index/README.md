# [2770. 达到末尾下标所需的最大跳跃次数](https://leetcode.cn/problems/maximum-number-of-jumps-to-reach-the-last-index)

[English Version](/solution/2700-2799/2770.Maximum%20Number%20of%20Jumps%20to%20Reach%20the%20Last%20Index/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、由 <code>n</code> 个整数组成的数组 <code>nums</code> 和一个整数 <code>target</code> 。</p>

<p>你的初始位置在下标 <code>0</code> 。在一步操作中，你可以从下标 <code>i</code> 跳跃到任意满足下述条件的下标 <code>j</code> ：</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; n</code></li>
	<li><code>-target &lt;= nums[j] - nums[i] &lt;= target</code></li>
</ul>

<p>返回到达下标 <code>n - 1</code> 处所需的 <strong>最大跳跃次数</strong> 。</p>

<p>如果无法到达下标 <code>n - 1</code> ，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,6,4,1,2], target = 2
<strong>输出：</strong>3
<strong>解释：</strong>要想以最大跳跃次数从下标 0 到下标 n - 1 ，可以按下述跳跃序列执行操作：
- 从下标 0 跳跃到下标 1 。 
- 从下标 1 跳跃到下标 3 。 
- 从下标 3 跳跃到下标 5 。 
可以证明，从 0 到 n - 1 的所有方案中，不存在比 3 步更长的跳跃序列。因此，答案是 3 。 </pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,6,4,1,2], target = 3
<strong>输出：</strong>5
<strong>解释：</strong>要想以最大跳跃次数从下标 0 到下标 n - 1 ，可以按下述跳跃序列执行操作：
- 从下标 0 跳跃到下标 1 。 
- 从下标 1 跳跃到下标 2 。 
- 从下标 2 跳跃到下标 3 。 
- 从下标 3 跳跃到下标 4 。 
- 从下标 4 跳跃到下标 5 。 
可以证明，从 0 到 n - 1 的所有方案中，不存在比 5 步更长的跳跃序列。因此，答案是 5 。 </pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,6,4,1,2], target = 0
<strong>输出：</strong>-1
<strong>解释：</strong>可以证明不存在从 0 到 n - 1 的跳跃序列。因此，答案是 -1 。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length == n &lt;= 1000</code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= nums[i]&nbsp;&lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= target &lt;= 2 * 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

对于每个位置 $i$，我们考虑向后搜索能跳到的位置 $j$，如果满足 $|nums[i] - nums[j]| \leq target$，那么我们就可以从 $i$ 跳到 $j$，并且从 $j$ 开始继续向后搜索。

因此，我们设计一个函数 $dfs(i)$，表示从位置 $i$ 开始跳跃到末尾下标所需的最大跳跃次数。那么答案就是 $dfs(0)$。

函数 $dfs(i)$ 的计算过程如下：

-   如果 $i = n - 1$，那么我们已经到达了末尾下标，不需要跳跃，因此返回 $0$；
-   否则，我们需要枚举从位置 $i$ 开始能跳到的位置 $j$，并计算从 $j$ 开始跳跃到末尾下标所需的最大跳跃次数，那么 $dfs(i)$ 就等于所有 $dfs(j)$ 中的最大值加 $1$。如果不存在从 $i$ 开始能跳到的位置 $j$，那么 $dfs(i) = -\infty$。

为了避免重复计算，我们可以使用记忆化搜索。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumJumps(self, nums: List[int], target: int) -> int:
        @cache
        def dfs(i: int) -> int:
            if i == n - 1:
                return 0
            ans = -inf
            for j in range(i + 1, n):
                if abs(nums[i] - nums[j]) <= target:
                    ans = max(ans, 1 + dfs(j))
            return ans

        n = len(nums)
        ans = dfs(0)
        return -1 if ans < 0 else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Integer[] f;
    private int[] nums;
    private int n;
    private int target;

    public int maximumJumps(int[] nums, int target) {
        n = nums.length;
        this.target = target;
        this.nums = nums;
        f = new Integer[n];
        int ans = dfs(0);
        return ans < 0 ? -1 : ans;
    }

    private int dfs(int i) {
        if (i == n - 1) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int ans = -(1 << 30);
        for (int j = i + 1; j < n; ++j) {
            if (Math.abs(nums[i] - nums[j]) <= target) {
                ans = Math.max(ans, 1 + dfs(j));
            }
        }
        return f[i] = ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumJumps(vector<int>& nums, int target) {
        int n = nums.size();
        int f[n];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int i) {
            if (i == n - 1) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            f[i] = -(1 << 30);
            for (int j = i + 1; j < n; ++j) {
                if (abs(nums[i] - nums[j]) <= target) {
                    f[i] = max(f[i], 1 + dfs(j));
                }
            }
            return f[i];
        };
        int ans = dfs(0);
        return ans < 0 ? -1 : ans;
    }
};
```

### **Go**

```go
func maximumJumps(nums []int, target int) int {
	n := len(nums)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i == n-1 {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		f[i] = -(1 << 30)
		for j := i + 1; j < n; j++ {
			if abs(nums[i]-nums[j]) <= target {
				f[i] = max(f[i], 1+dfs(j))
			}
		}
		return f[i]
	}
	ans := dfs(0)
	if ans < 0 {
		return -1
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function maximumJumps(nums: number[], target: number): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(-1);
    const dfs = (i: number): number => {
        if (i === n - 1) {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        f[i] = -(1 << 30);
        for (let j = i + 1; j < n; ++j) {
            if (Math.abs(nums[i] - nums[j]) <= target) {
                f[i] = Math.max(f[i], 1 + dfs(j));
            }
        }
        return f[i];
    };
    const ans = dfs(0);
    return ans < 0 ? -1 : ans;
}
```

### **...**

```

```

<!-- tabs:end -->
