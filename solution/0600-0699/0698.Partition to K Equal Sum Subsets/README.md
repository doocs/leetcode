# [698. 划分为 k 个相等的子集](https://leetcode.cn/problems/partition-to-k-equal-sum-subsets)

[English Version](/solution/0600-0699/0698.Partition%20to%20K%20Equal%20Sum%20Subsets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组&nbsp;&nbsp;<code>nums</code> 和一个正整数 <code>k</code>，找出是否有可能把这个数组分成 <code>k</code> 个非空子集，其总和都相等。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> nums = [4, 3, 2, 3, 5, 2, 1], k = 4
<strong>输出：</strong> True
<strong>说明：</strong> 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,4], k = 3
<strong>输出:</strong> false</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= len(nums) &lt;= 16</code></li>
	<li><code>0 &lt; nums[i] &lt; 10000</code></li>
	<li>每个元素的频率在 <code>[1,4]</code> 范围内</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS + 剪枝**

解法和 [473. 火柴拼正方形](/solution/0400-0499/0473.Matchsticks%20to%20Square/README.md) 相同。

**方法二：状态压缩 + 记忆化搜索**

记当前数字被划分的情况为 $state$。对于第 $i$ 个数，若 $state \ \& \ (1<<i)=0$，说明第 $i$ 个数字未被划分。我们的目标是从全部数字中凑出 $k$ 个和为 $s$ 的子集。

记当前子集的和为 $t$。在未划分第 $i$ 个数字时：

-   若 $t+nums[i]>s$，说明第 $i$ 个数字不能被添加到当前子集中，由于我们对 $nums$ 数组进行升序排列，因此从 $nums$ 从第 $i$ 个数字开始的所有数字都不能被添加到当前子集，直接返回 $false$。
-   否则，将第 $i$ 个数字添加到当前子集中，状态变为 $state \ |\ (1<<i)$，继续对未划分的数字进行搜索。

注：若 $t+nums[i]==s$，说明恰好可以得到一个和为 $s$ 的子集，下一步将 $t$ 归零（可以通过 $(t+nums[i]) \%s$ 实现），并继续划分下一个子集。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canPartitionKSubsets(self, nums: List[int], k: int) -> bool:
        s = sum(nums)
        target, m = divmod(s, k)
        if m != 0:
            return False

        cur = [0] * k
        n = len(nums)

        def dfs(i: int) -> bool:
            if i == n:
                return True
            for j in range(k):
                if j > 0 and cur[j - 1] == cur[j]:
                    continue
                cur[j] += nums[i]
                if cur[j] <= target and dfs(i + 1):
                    return True
                cur[j] -= nums[i]
            return False

        nums.sort(reverse=True)
        return dfs(0)
```

```python
class Solution:
    def canPartitionKSubsets(self, nums: List[int], k: int) -> bool:
        @cache
        def dfs(state, t):
            if state == (1 << len(nums)) - 1:
                return True
            for i, v in enumerate(nums):
                if (state & (1 << i)):
                    continue
                if t + v > s:
                    break
                if dfs(state | (1 << i), (t + v) % s):
                    return True
            return False

        s, mod = divmod(sum(nums), k)
        nums.sort()
        if mod:
            return False
        return dfs(0, 0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = (int) Arrays.stream(nums).sum();
		if (sum % k != 0) {
			return false;
		}

		Arrays.sort(nums);
		int low = 0, high = nums.length - 1;
		while (low < high) {
			int temp = nums[low];
			nums[low] = nums[high];
			nums[high] = temp;
			low++;
			high--;
		}
		return dfs(nums, new int[k], 0, sum / k);
	}

	private boolean dfs(int[] nums, int[] cur, int i, int target) {
		if (i == nums.length) {
			return true;
		}
		for (int j = 0; j < cur.length; j++) {
            if (j > 0 && cur[j - 1] == cur[j]) {
                continue;
            }
			cur[j] += nums[i];
			if (cur[j] <= target && dfs(nums, cur, i + 1, target)) {
				return true;
			}
			cur[j] -= nums[i];
		}
		return false;
	}
}
```

### **Go**

```go
func canPartitionKSubsets(nums []int, k int) bool {
	sum := 0
	for _, num := range nums {
		sum += num
	}
	if sum%k != 0 {
		return false
	}

	var (
		target = sum / k
		cur    = make([]int, k)
		n      = len(nums)
	)

	var dfs func(i int) bool
	dfs = func(i int) bool {
		if i == n {
			return true
		}
		for j := 0; j < k; j++ {
			if j > 0 && cur[j-1] == cur[j] {
				continue
			}
			cur[j] += nums[i]
			if cur[j] <= target && dfs(i+1) {
				return true
			}
			cur[j] -= nums[i]
		}
		return false
	}

	sort.Sort(sort.Reverse(sort.IntSlice(nums)))
	return dfs(0)
}
```

### **C++**

```cpp
class Solution {
public:
    bool canPartitionKSubsets(vector<int>& nums, int k) {
        int sum = accumulate(nums.begin(), nums.end(), 0);
        if (sum % k != 0) return false;

        int target = sum / k;
        int n = nums.size();
        vector<int> cur(k, 0);

        function<bool(int)> dfs;
        dfs = [&](int i) {
            if (i == n) return true;
            for (int j = 0; j < k; ++j) {
                if (j > 0 && cur[j - 1] == cur[j]) continue;
                cur[j] += nums[i];
                if (cur[j] <= target && dfs(i + 1)) return true;
                cur[j] -= nums[i];
            }
            return false;
        };

        sort(nums.begin(), nums.end(), greater<int>());
        return dfs(0);
    }
};
```

### **...**

```

```

<!-- tabs:end -->
