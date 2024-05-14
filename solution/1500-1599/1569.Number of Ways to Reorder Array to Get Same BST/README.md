# [1569. 将子数组重新排序得到同一个二叉搜索树的方案数](https://leetcode.cn/problems/number-of-ways-to-reorder-array-to-get-same-bst)

[English Version](/solution/1500-1599/1569.Number%20of%20Ways%20to%20Reorder%20Array%20to%20Get%20Same%20BST/README_EN.md)

<!-- tags:树,并查集,二叉搜索树,记忆化搜索,数组,数学,分治,动态规划,二叉树,组合数学 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>nums</code>&nbsp;表示 <code>1</code>&nbsp;到 <code>n</code>&nbsp;的一个排列。我们按照元素在 <code>nums</code>&nbsp;中的顺序依次插入一个初始为空的二叉搜索树（BST）。请你统计将 <code>nums</code>&nbsp;重新排序后，统计满足如下条件的方案数：重排后得到的二叉搜索树与 <code>nums</code>&nbsp;原本数字顺序得到的二叉搜索树相同。</p>

<p>比方说，给你&nbsp;<code>nums = [2,1,3]</code>，我们得到一棵 2 为根，1 为左孩子，3 为右孩子的树。数组&nbsp;<code>[2,3,1]</code>&nbsp;也能得到相同的 BST，但&nbsp;<code>[3,2,1]</code>&nbsp;会得到一棵不同的&nbsp;BST 。</p>

<p>请你返回重排 <code>nums</code>&nbsp;后，与原数组 <code>nums</code> 得到相同二叉搜索树的方案数。</p>

<p>由于答案可能会很大，请将结果对<strong>&nbsp;</strong><code>10^9 + 7</code>&nbsp;取余数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1569.Number%20of%20Ways%20to%20Reorder%20Array%20to%20Get%20Same%20BST/images/bb.png" style="height: 101px; width: 121px;" /></p>

<pre>
<strong>输入：</strong>nums = [2,1,3]
<strong>输出：</strong>1
<strong>解释：</strong>我们将 nums 重排， [2,3,1] 能得到相同的 BST 。没有其他得到相同 BST 的方案了。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1569.Number%20of%20Ways%20to%20Reorder%20Array%20to%20Get%20Same%20BST/images/ex1.png" style="height: 161px; width: 241px;" /></strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,5,1,2]
<strong>输出：</strong>5
<strong>解释：</strong>下面 5 个数组会得到相同的 BST：
[3,1,2,4,5]
[3,1,4,2,5]
[3,1,4,5,2]
[3,4,1,2,5]
[3,4,1,5,2]
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1569.Number%20of%20Ways%20to%20Reorder%20Array%20to%20Get%20Same%20BST/images/ex4.png" style="height: 161px; width: 121px;" /></strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>0
<strong>解释：</strong>没有别的排列顺序能得到相同的 BST 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= nums.length</code></li>
	<li><code>nums</code>&nbsp;中所有数 <strong>互不相同</strong>&nbsp;。</li>
</ul>

## 解法

### 方法一：组合计数 + 递归

我们设计一个函数 $dfs(nums)$，它的功能是计算以 $nums$ 为节点构成的二叉搜索树的方案数。那么答案就是 $dfs(nums)-1$，因为 $dfs(nums)$ 计算的是以 $nums$ 为节点构成的二叉搜索树的方案数，而题目要求的是重排后与原数组 $nums$ 得到相同二叉搜索树的方案数，因此答案需要减去一。

接下来，我们来看一下 $dfs(nums)$ 的计算方法。

对于一个数组 $nums$，它的第一个元素是根节点，那么它的左子树的元素都小于它，右子树的元素都大于它。因此，我们可以将数组分为三部分，第一部分是根节点，第二部分是左子树的元素，记为 $left$，第三部分是右子树的元素，记为 $right$。那么，左子树的元素个数为 $m$，右子树的元素个数为 $n$，那么 $left$ 和 $right$ 的方案数分别为 $dfs(left)$ 和 $dfs(right)$。我们可以在数组 $nums$ 的 $m + n$ 个位置中选择 $m$ 个位置放置左子树的元素，剩下的 $n$ 个位置放置右子树的元素，这样就能保证重排后与原数组 $nums$ 得到相同二叉搜索树。因此，$dfs(nums)$ 的计算方法为：

$$
dfs(nums) = C_{m+n}^m \times dfs(left) \times dfs(right)
$$

其中 $C_{m+n}^m$ 表示从 $m + n$ 个位置中选择 $m$ 个位置的方案数，我们可以通过预处理得到。

注意答案的取模运算，因为 $dfs(nums)$ 的值可能会很大，所以我们需要在计算过程中对每一步的结果取模，最后再对整个结果取模。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def numOfWays(self, nums: List[int]) -> int:
        def dfs(nums):
            if len(nums) < 2:
                return 1
            left = [x for x in nums if x < nums[0]]
            right = [x for x in nums if x > nums[0]]
            m, n = len(left), len(right)
            a, b = dfs(left), dfs(right)
            return (((c[m + n][m] * a) % mod) * b) % mod

        n = len(nums)
        mod = 10**9 + 7
        c = [[0] * n for _ in range(n)]
        c[0][0] = 1
        for i in range(1, n):
            c[i][0] = 1
            for j in range(1, i + 1):
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod
        return (dfs(nums) - 1 + mod) % mod
```

```java
class Solution {
    private int[][] c;
    private final int mod = (int) 1e9 + 7;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        c = new int[n][n];
        c[0][0] = 1;
        for (int i = 1; i < n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int x : nums) {
            list.add(x);
        }
        return (dfs(list) - 1 + mod) % mod;
    }

    private int dfs(List<Integer> nums) {
        if (nums.size() < 2) {
            return 1;
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int x : nums) {
            if (x < nums.get(0)) {
                left.add(x);
            } else if (x > nums.get(0)) {
                right.add(x);
            }
        }
        int m = left.size(), n = right.size();
        int a = dfs(left), b = dfs(right);
        return (int) ((long) a * b % mod * c[m + n][n] % mod);
    }
}
```

```cpp
class Solution {
public:
    int numOfWays(vector<int>& nums) {
        int n = nums.size();
        const int mod = 1e9 + 7;
        int c[n][n];
        memset(c, 0, sizeof(c));
        c[0][0] = 1;
        for (int i = 1; i < n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }
        function<int(vector<int>)> dfs = [&](vector<int> nums) -> int {
            if (nums.size() < 2) {
                return 1;
            }
            vector<int> left, right;
            for (int& x : nums) {
                if (x < nums[0]) {
                    left.push_back(x);
                } else if (x > nums[0]) {
                    right.push_back(x);
                }
            }
            int m = left.size(), n = right.size();
            int a = dfs(left), b = dfs(right);
            return c[m + n][m] * 1ll * a % mod * b % mod;
        };
        return (dfs(nums) - 1 + mod) % mod;
    }
};
```

```go
func numOfWays(nums []int) int {
	n := len(nums)
	const mod = 1e9 + 7
	c := make([][]int, n)
	for i := range c {
		c[i] = make([]int, n)
	}
	c[0][0] = 1
	for i := 1; i < n; i++ {
		c[i][0] = 1
		for j := 1; j <= i; j++ {
			c[i][j] = (c[i-1][j] + c[i-1][j-1]) % mod
		}
	}
	var dfs func(nums []int) int
	dfs = func(nums []int) int {
		if len(nums) < 2 {
			return 1
		}
		var left, right []int
		for _, x := range nums[1:] {
			if x < nums[0] {
				left = append(left, x)
			} else {
				right = append(right, x)
			}
		}
		m, n := len(left), len(right)
		a, b := dfs(left), dfs(right)
		return c[m+n][m] * a % mod * b % mod
	}
	return (dfs(nums) - 1 + mod) % mod
}
```

```ts
function numOfWays(nums: number[]): number {
    const n = nums.length;
    const mod = 1e9 + 7;
    const c = new Array(n).fill(0).map(() => new Array(n).fill(0));
    c[0][0] = 1;
    for (let i = 1; i < n; ++i) {
        c[i][0] = 1;
        for (let j = 1; j <= i; ++j) {
            c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % mod;
        }
    }
    const dfs = (nums: number[]): number => {
        if (nums.length < 2) {
            return 1;
        }
        const left: number[] = [];
        const right: number[] = [];
        for (let i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[0]) {
                left.push(nums[i]);
            } else {
                right.push(nums[i]);
            }
        }
        const m = left.length;
        const n = right.length;
        const a = dfs(left);
        const b = dfs(right);
        return Number((BigInt(c[m + n][m]) * BigInt(a) * BigInt(b)) % BigInt(mod));
    };
    return (dfs(nums) - 1 + mod) % mod;
}
```

<!-- tabs:end -->

<!-- end -->
