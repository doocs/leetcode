# [2263. 数组变为有序的最小操作次数 🔒](https://leetcode.cn/problems/make-array-non-decreasing-or-non-increasing)

[English Version](/solution/2200-2299/2263.Make%20Array%20Non-decreasing%20or%20Non-increasing/README_EN.md)

<!-- tags:贪心,动态规划 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 。在一步操作中，你可以：</p>

<ul>
	<li>在范围&nbsp;<code>0 &lt;= i &lt; nums.length</code> 内选出一个下标 <code>i</code></li>
	<li>将 <code>nums[i]</code> 的值变为 <code>nums[i] + 1</code> <strong>或</strong> <code>nums[i] - 1</code></li>
</ul>

<p>返回将数组 <code>nums</code> 变为 <strong>非递增</strong> 或<strong> 非递减 </strong>所需的 <strong>最小</strong> 操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,4,5,0]
<strong>输出：</strong>4
<strong>解释：</strong>
一种可行的操作顺序，能够将 nums 变为非递增排列：
- nums[1] 加 1 一次，使其变为 3 。
- nums[2] 减 1 一次，使其变为 3 。
- nums[3] 减 1 两次，使其变为 3 。
经过 4 次操作后，nums 变为 [3,3,3,3,0] ，按非递增顺序排列。
注意，也可以用 4 步操作将 nums 变为 [4,4,4,4,0] ，同样满足题目要求。
可以证明最少需要 4 步操作才能将数组变为非递增或非递减排列。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,3,4]
<strong>输出：</strong>0
<strong>解释：</strong>数组已经是按非递减顺序排列了，无需执行任何操作，返回 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [0]
<strong>输出：</strong>0
<strong>解释：</strong>数组已经是按非递减顺序排列了，无需执行任何操作，返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计并实现时间复杂度为 <code>O(n*log(n))</code> 的解法吗?</p>

## 解法

### 方法一：动态规划

我们定义 $f[i][j]$ 表示将数组 $nums$ 的前 $i$ 个元素变为非递减序列，且第 $i$ 个元素的值为 $j$ 所需的最小操作次数。由于数组 $nums$ 元素的取值范围为 $[0, 1000]$，因此我们可以将 $f$ 数组的第二维定义为 $1001$。

状态转移方程如下：

$$
f[i][j] = \min_{0 \leq k \leq j} f[i - 1][k] + \left| j - nums[i - 1] \right|
$$

时间复杂度 $O(n \times M)$，空间复杂度 $O(n \times M)$。其中 $n$ 和 $M$ 分别为数组 $nums$ 的长度和数组 $nums$ 元素的取值范围。本题中 $M = 1001$。

由于我们定义的是非递减序列的最小操作次数，因此我们可以将数组 $nums$ 翻转，然后求出非递减序列的最小操作次数，也即是非递增序列的最小操作次数。最后取两者的最小值即可。

<!-- tabs:start -->

```python
class Solution:
    def convertArray(self, nums: List[int]) -> int:
        def solve(nums):
            n = len(nums)
            f = [[0] * 1001 for _ in range(n + 1)]
            for i, x in enumerate(nums, 1):
                mi = inf
                for j in range(1001):
                    if mi > f[i - 1][j]:
                        mi = f[i - 1][j]
                    f[i][j] = mi + abs(x - j)
            return min(f[n])

        return min(solve(nums), solve(nums[::-1]))
```

```java
class Solution {
    public int convertArray(int[] nums) {
        return Math.min(solve(nums), solve(reverse(nums)));
    }

    private int solve(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n + 1][1001];
        for (int i = 1; i <= n; ++i) {
            int mi = 1 << 30;
            for (int j = 0; j <= 1000; ++j) {
                mi = Math.min(mi, f[i - 1][j]);
                f[i][j] = mi + Math.abs(j - nums[i - 1]);
            }
        }
        int ans = 1 << 30;
        for (int x : f[n]) {
            ans = Math.min(ans, x);
        }
        return ans;
    }

    private int[] reverse(int[] nums) {
        for (int i = 0, j = nums.length - 1; i < j; ++i, --j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        return nums;
    }
}
```

```cpp
class Solution {
public:
    int convertArray(vector<int>& nums) {
        int a = solve(nums);
        reverse(nums.begin(), nums.end());
        int b = solve(nums);
        return min(a, b);
    }

    int solve(vector<int>& nums) {
        int n = nums.size();
        int f[n + 1][1001];
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= n; ++i) {
            int mi = 1 << 30;
            for (int j = 0; j <= 1000; ++j) {
                mi = min(mi, f[i - 1][j]);
                f[i][j] = mi + abs(nums[i - 1] - j);
            }
        }
        return *min_element(f[n], f[n] + 1001);
    }
};
```

```go
func convertArray(nums []int) int {
	return min(solve(nums), solve(reverse(nums)))
}

func solve(nums []int) int {
	n := len(nums)
	f := make([][1001]int, n+1)
	for i := 1; i <= n; i++ {
		mi := 1 << 30
		for j := 0; j <= 1000; j++ {
			mi = min(mi, f[i-1][j])
			f[i][j] = mi + abs(nums[i-1]-j)
		}
	}
	ans := 1 << 30
	for _, x := range f[n] {
		ans = min(ans, x)
	}
	return ans
}

func reverse(nums []int) []int {
	for i, j := 0, len(nums)-1; i < j; i, j = i+1, j-1 {
		nums[i], nums[j] = nums[j], nums[i]
	}
	return nums
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

<!-- tabs:end -->

<!-- end -->
