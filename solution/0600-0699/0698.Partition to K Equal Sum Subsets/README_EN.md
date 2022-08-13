# [698. Partition to K Equal Sum Subsets](https://leetcode.com/problems/partition-to-k-equal-sum-subsets)

[中文文档](/solution/0600-0699/0698.Partition%20to%20K%20Equal%20Sum%20Subsets/README.md)

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <code>true</code> if it is possible to divide this array into <code>k</code> non-empty subsets whose sums are all equal.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,2,3,5,2,1], k = 4
<strong>Output:</strong> true
<strong>Explanation:</strong> It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], k = 3
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 16</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li>The frequency of each element is in the range <code>[1, 4]</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
