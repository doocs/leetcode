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

根据题意，我们需要将数组 `nums` 划分为 $k$ 个子集，且每个子集的和相等。因此，先累加 `nums` 中所有元素的和，如果不能被 $k$ 整除，说明无法划分为 $k$ 个子集，提前返回 `false`。

如果能被 $k$ 整除，不妨将每个子集期望的和记为 $s$，然后创建一个长度为 $k$ 的数组 `cur`，表示当前每个子集的和。

对数组 `nums` 进行降序排序（减少搜索次数），然后从第一个元素开始，依次尝试将其加入到 `cur` 的每个子集中。这里如果将 `nums[i]` 加入某个子集 `cur[j]` 后，子集的和超过 $s$，说明无法放入，可以直接跳过；另外，如果 `cur[j]` 与 `cur[j - 1]` 相等，意味着我们在 `cur[j - 1]` 的时候已经完成了搜索，也可以跳过当前的搜索。

如果能将所有元素都加入到 `cur` 中，说明可以划分为 $k$ 个子集，返回 `true`。

**方法二：状态压缩 + 记忆化搜索**

与方法一相同，我们依然先判断数组 `nums` 是否有可能被划分为 $k$ 个子集。如果不能被 $k$ 整除，直接返回 `false`。

我们记 $s$ 为每个子集期望的和，当前元素被划分的情况为 `state`。对于第 $i$ 个数，若 `((state >> i) & 1)` 等于 $0$，说明第 $i$ 个元素未被划分。

我们的目标是从全部元素中凑出 $k$ 个和为 $s$ 的子集。记当前子集的和为 $t$。在未划分第 $i$ 个元素时：

-   若 $t + nums[i] \gt s$，说明第 $i$ 个元素不能被添加到当前子集中，由于我们对 `nums` 数组进行升序排列，因此数组 `nums` 从位置 $i$ 开始的所有元素都不能被添加到当前子集，直接返回 `false`。
-   否则，将第 $i$ 个元素添加到当前子集中，状态变为 `state | (1 << i)`，然后继续对未划分的元素进行搜索。需要注意的是，若 $t + nums[i] = s$，说明恰好可以得到一个和为 $s$ 的子集，下一步将 $t$ 归零（可以通过 `(t + nums[i]) % s` 实现），并继续划分下一个子集。

为了避免重复搜索，我们使用一个长度为 $2^n$ 的数组 `f` 记录每个状态下的搜索结果。数组 `f` 有三个可能的值：

-   `0`：表示当前状态还未搜索过；
-   `-1`：表示当前状态下无法划分为 $k$ 个子集；
-   `1`：表示当前状态下可以划分为 $k$ 个子集。

时间复杂度 $O(n\times 2^n)$，其中 $n$ 表示数组 $nums$ 的长度。对于每个状态，我们需要遍历数组 `nums`，时间复杂度为 $O(n)$；状态总数为 $2^n$，因此总的时间复杂度为 $O(n\times 2^n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canPartitionKSubsets(self, nums: List[int], k: int) -> bool:
        def dfs(i):
            if i == len(nums):
                return True
            for j in range(k):
                if j and cur[j] == cur[j - 1]:
                    continue
                cur[j] += nums[i]
                if cur[j] <= s and dfs(i + 1):
                    return True
                cur[j] -= nums[i]
            return False

        s, mod = divmod(sum(nums), k)
        if mod:
            return False
        cur = [0] * k
        nums.sort(reverse=True)
        return dfs(0)
```

```python
class Solution:
    def canPartitionKSubsets(self, nums: List[int], k: int) -> bool:
        @cache
        def dfs(state, t):
            if state == mask:
                return True
            for i, v in enumerate(nums):
                if (state >> i) & 1:
                    continue
                if t + v > s:
                    break
                if dfs(state | 1 << i, (t + v) % s):
                    return True
            return False

        s, mod = divmod(sum(nums), k)
        if mod:
            return False
        nums.sort()
        mask = (1 << len(nums)) - 1
        return dfs(0, 0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] nums;
    private int[] cur;
    private int s;

    public boolean canPartitionKSubsets(int[] nums, int k) {
        for (int v : nums) {
            s += v;
        }
        if (s % k != 0) {
            return false;
        }
        s /= k;
        cur = new int[k];
        Arrays.sort(nums);
        this.nums = nums;
        return dfs(nums.length - 1);
    }

    private boolean dfs(int i) {
        if (i < 0) {
            return true;
        }
        for (int j = 0; j < cur.length; ++j) {
            if (j > 0 && cur[j] == cur[j - 1]) {
                continue;
            }
            cur[j] += nums[i];
            if (cur[j] <= s && dfs(i - 1)) {
                return true;
            }
            cur[j] -= nums[i];
        }
        return false;
    }
}
```

```java
class Solution {
    private int[] f;
    private int[] nums;
    private int n;
    private int s;

    public boolean canPartitionKSubsets(int[] nums, int k) {
        for (int v : nums) {
            s += v;
        }
        if (s % k != 0) {
            return false;
        }
        s /= k;
        Arrays.sort(nums);
        this.nums = nums;
        n = nums.length;
        f = new int[1 << n];
        return dfs(0, 0);
    }

    private boolean dfs(int state, int t) {
        if (state == (1 << n) - 1) {
            return true;
        }
        if (f[state] != 0) {
            return f[state] == 1;
        }
        for (int i = 0; i < n; ++i) {
            if (((state >> i) & 1) == 1) {
                continue;
            }
            if (t + nums[i] > s) {
                break;
            }
            if (dfs(state | 1 << i, (t + nums[i]) % s)) {
                f[state] = 1;
                return true;
            }
        }
        f[state] = -1;
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canPartitionKSubsets(vector<int>& nums, int k) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        if (s % k) {
            return false;
        }
        s /= k;
        int n = nums.size();
        vector<int> cur(k);
        function<bool(int)> dfs;
        dfs = [&](int i) {
            if (i == n) {
                return true;
            }
            for (int j = 0; j < k; ++j) {
                if (j && cur[j] == cur[j - 1]) {
                    continue;
                }
                cur[j] += nums[i];
                if (cur[j] <= s && dfs(i + 1)) {
                    return true;
                }
                cur[j] -= nums[i];
            }
            return false;
        };
        sort(nums.begin(), nums.end(), greater<int>());
        return dfs(0);
    }
};
```

```cpp
class Solution {
public:
    bool canPartitionKSubsets(vector<int>& nums, int k) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        if (s % k) {
            return false;
        }
        s /= k;
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int mask = (1 << n) - 1;
        vector<int> f(1 << n);
        function<bool(int, int)> dfs;
        dfs = [&](int state, int t) {
            if (state == mask) {
                return true;
            }
            if (f[state]) {
                return f[state] == 1;
            }
            for (int i = 0; i < n; ++i) {
                if (state >> i & 1) {
                    continue;
                }
                if (t + nums[i] > s) {
                    break;
                }
                if (dfs(state | 1 << i, (t + nums[i]) % s)) {
                    f[state] = 1;
                    return true;
                }
            }
            f[state] = -1;
            return false;
        };
        return dfs(0, 0);
    }
};
```

### **Go**

```go
func canPartitionKSubsets(nums []int, k int) bool {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s%k != 0 {
		return false
	}
	s /= k
	cur := make([]int, k)
	n := len(nums)

	var dfs func(int) bool
	dfs = func(i int) bool {
		if i == n {
			return true
		}
		for j := 0; j < k; j++ {
			if j > 0 && cur[j] == cur[j-1] {
				continue
			}
			cur[j] += nums[i]
			if cur[j] <= s && dfs(i+1) {
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

```go
func canPartitionKSubsets(nums []int, k int) bool {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s%k != 0 {
		return false
	}
	s /= k
	n := len(nums)
	f := make([]int, 1<<n)
	mask := (1 << n) - 1

	var dfs func(int, int) bool
	dfs = func(state, t int) bool {
		if state == mask {
			return true
		}
		if f[state] != 0 {
			return f[state] == 1
		}
		for i, v := range nums {
			if (state >> i & 1) == 1 {
				continue
			}
			if t+v > s {
				break
			}
			if dfs(state|1<<i, (t+v)%s) {
				f[state] = 1
				return true
			}
		}
		f[state] = -1
		return false
	}

	sort.Ints(nums)
	return dfs(0, 0)
}
```

### **...**

```

```

<!-- tabs:end -->
