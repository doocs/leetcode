# [1498. 满足条件的子序列数目](https://leetcode.cn/problems/number-of-subsequences-that-satisfy-the-given-sum-condition)

[English Version](/solution/1400-1499/1498.Number%20of%20Subsequences%20That%20Satisfy%20the%20Given%20Sum%20Condition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>target</code> 。</p>

<p>请你统计并返回 <code>nums</code> 中能满足其最小元素与最大元素的 <strong>和</strong> 小于或等于 <code>target</code> 的 <strong>非空</strong> 子序列的数目。</p>

<p>由于答案可能很大，请将结果对<meta charset="UTF-8" />&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;取余后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,5,6,7], target = 9
<strong>输出：</strong>4
<strong>解释：</strong>有 4 个子序列满足该条件。
[3] -&gt; 最小元素 + 最大元素 &lt;= target (3 + 3 &lt;= 9)
[3,5] -&gt; (3 + 5 &lt;= 9)
[3,5,6] -&gt; (3 + 6 &lt;= 9)
[3,6] -&gt; (3 + 6 &lt;= 9)
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,3,6,8], target = 10
<strong>输出：</strong>6
<strong>解释：</strong>有 6 个子序列满足该条件。（nums 中可以有重复数字）
[3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,3,3,4,6,7], target = 12
<strong>输出：</strong>61
<strong>解释：</strong>共有 63 个非空子序列，其中 2 个不满足条件（[6,7], [7]）
有效序列总数为（63 - 2 = 61）
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= target &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 枚举贡献 + 二分查找**

由于题目中描述的是子序列，并且涉及到最小元素与最大元素的和，因此我们可以先对数组 `nums` 进行排序。

然后我们枚举最小元素 $nums[i]$，对于每个 $nums[i]$，我们可以在 $nums[i + 1]$ 到 $nums[n - 1]$ 中找到最大元素 $nums[j]$，使得 $nums[i] + nums[j] \leq target$，此时满足条件的子序列数目为 $2^{j - i}$，其中 $2^{j - i}$ 表示从 $nums[i + 1]$ 到 $nums[j]$ 的所有子序列的数目。我们将所有的子序列数目累加即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numSubseq(self, nums: List[int], target: int) -> int:
        mod = 10**9 + 7
        nums.sort()
        n = len(nums)
        f = [1] + [0] * n
        for i in range(1, n + 1):
            f[i] = f[i - 1] * 2 % mod
        ans = 0
        for i, x in enumerate(nums):
            if x * 2 > target:
                break
            j = bisect_right(nums, target - x, i + 1) - 1
            ans = (ans + f[j - i]) % mod
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        final int mod = (int) 1e9 + 7;
        int n = nums.length;
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            f[i] = (f[i - 1] * 2) % mod;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] * 2L > target) {
                break;
            }
            int j = search(nums, target - nums[i], i + 1) - 1;
            ans = (ans + f[j - i]) % mod;
        }
        return ans;
    }

    private int search(int[] nums, int x, int left) {
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numSubseq(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        const int mod = 1e9 + 7;
        int n = nums.size();
        int f[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            f[i] = (f[i - 1] * 2) % mod;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] * 2L > target) {
                break;
            }
            int j = upper_bound(nums.begin() + i + 1, nums.end(), target - nums[i]) - nums.begin() - 1;
            ans = (ans + f[j - i]) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func numSubseq(nums []int, target int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	f := make([]int, n+1)
	f[0] = 1
	const mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		f[i] = f[i-1] * 2 % mod
	}
	for i, x := range nums {
		if x*2 > target {
			break
		}
		j := sort.SearchInts(nums[i+1:], target-x+1) + i
		ans = (ans + f[j-i]) % mod
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
