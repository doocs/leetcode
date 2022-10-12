# [698. Partition to K Equal Sum Subsets](https://leetcode.com/problems/partition-to-k-equal-sum-subsets)

[中文文档](/solution/0600-0699/0698.Partition%20to%20K%20Equal%20Sum%20Subsets/README.md)

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <code>true</code> if it is possible to divide this array into <code>k</code> non-empty subsets whose sums are all equal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,2,3,5,2,1], k = 4
<strong>Output:</strong> true
<strong>Explanation:</strong> It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
</pre>

<p><strong class="example">Example 2:</strong></p>

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
