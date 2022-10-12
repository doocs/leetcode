# [2369. Check if There is a Valid Partition For The Array](https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array)

[中文文档](/solution/2300-2399/2369.Check%20if%20There%20is%20a%20Valid%20Partition%20For%20The%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. You have to partition the array into one or more <strong>contiguous</strong> subarrays.</p>

<p>We call a partition of the array <strong>valid</strong> if each of the obtained subarrays satisfies <strong>one</strong> of the following conditions:</p>

<ol>
	<li>The subarray consists of <strong>exactly</strong> <code>2</code> equal elements. For example, the subarray <code>[2,2]</code> is good.</li>
	<li>The subarray consists of <strong>exactly</strong> <code>3</code> equal elements. For example, the subarray <code>[4,4,4]</code> is good.</li>
	<li>The subarray consists of <strong>exactly</strong> <code>3</code> consecutive increasing elements, that is, the difference between adjacent elements is <code>1</code>. For example, the subarray <code>[3,4,5]</code> is good, but the subarray <code>[1,3,5]</code> is not.</li>
</ol>

<p>Return <code>true</code><em> if the array has <strong>at least</strong> one valid partition</em>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,4,4,5,6]
<strong>Output:</strong> true
<strong>Explanation:</strong> The array can be partitioned into the subarrays [4,4] and [4,5,6].
This partition is valid, so we return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,2]
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no valid partition for this array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def validPartition(self, nums: List[int]) -> bool:
        @cache
        def dfs(i):
            if i == n:
                return True
            res = False
            if i < n - 1 and nums[i] == nums[i + 1]:
                res = res or dfs(i + 2)
            if i < n - 2 and nums[i] == nums[i + 1] and nums[i + 1] == nums[i + 2]:
                res = res or dfs(i + 3)
            if (
                i < n - 2
                and nums[i + 1] - nums[i] == 1
                and nums[i + 2] - nums[i + 1] == 1
            ):
                res = res or dfs(i + 3)
            return res

        n = len(nums)
        return dfs(0)
```

```python
class Solution:
    def validPartition(self, nums: List[int]) -> bool:
        n = len(nums)
        dp = [False] * (n + 1)
        dp[0] = True
        for i in range(2, n + 1):
            if nums[i - 1] == nums[i - 2]:
                dp[i] = dp[i] or dp[i - 2]
            if i > 2 and nums[i - 1] == nums[i - 2] == nums[i - 3]:
                dp[i] = dp[i] or dp[i - 3]
            if i > 2 and nums[i - 1] - nums[i - 2] == 1 and nums[i - 2] - nums[i - 3] == 1:
                dp[i] = dp[i] or dp[i - 3]
        return dp[-1]
```

### **Java**

```java
class Solution {
    private int n;
    private int[] f;
    private int[] nums;

    public boolean validPartition(int[] nums) {
        this.nums = nums;
        n = nums.length;
        f = new int[n];
        Arrays.fill(f, -1);
        return dfs(0);
    }

    private boolean dfs(int i) {
        if (i == n) {
            return true;
        }
        if (f[i] != -1) {
            return f[i] == 1;
        }
        boolean res = false;
        if (i < n - 1 && nums[i] == nums[i + 1]) {
            res = res || dfs(i + 2);
        }
        if (i < n - 2 && nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2]) {
            res = res || dfs(i + 3);
        }
        if (i < n - 2 && nums[i + 1] - nums[i] == 1 && nums[i + 2] - nums[i + 1] == 1) {
            res = res || dfs(i + 3);
        }
        f[i] = res ? 1 : 0;
        return res;
    }
}
```

```java
class Solution {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 2; i <= n; ++i) {
            if (nums[i - 1] == nums[i - 2]) {
                dp[i] = dp[i] || dp[i - 2];
            }
            if (i > 2 && nums[i - 1] == nums[i - 2] && nums[i - 2] == nums[i - 3]) {
                dp[i] = dp[i] || dp[i - 3];
            }
            if (i > 2 && nums[i - 1] - nums[i - 2] == 1 && nums[i - 2] - nums[i - 3] == 1) {
                dp[i] = dp[i] || dp[i - 3];
            }
        }
        return dp[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> f;
    vector<int> nums;
    int n;

    bool validPartition(vector<int>& nums) {
        n = nums.size();
        this->nums = nums;
        f.assign(n, -1);
        return dfs(0);
    }

    bool dfs(int i) {
        if (i == n) return true;
        if (f[i] != -1) return f[i] == 1;
        bool res = false;
        if (i < n - 1 && nums[i] == nums[i + 1]) res = res || dfs(i + 2);
        if (i < n - 2 && nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2]) res = res || dfs(i + 3);
        if (i < n - 2 && nums[i + 1] - nums[i] == 1 && nums[i + 2] - nums[i + 1] == 1) res = res || dfs(i + 3);
        f[i] = res ? 1 : 0;
        return res;
    }
};
```

```cpp
class Solution {
public:
    bool validPartition(vector<int>& nums) {
        int n = nums.size();
        vector<bool> dp(n + 1);
        dp[0] = true;
        for (int i = 2; i <= n; ++i)
        {
            if (nums[i - 1] == nums[i - 2]) dp[i] = dp[i] || dp[i - 2];
            if (i > 2 && nums[i - 1] == nums[i - 2] && nums[i - 2] == nums[i - 3]) dp[i] = dp[i] || dp[i - 3];
            if (i > 2 && nums[i - 1] - nums[i - 2] == 1 && nums[i - 2] - nums[i - 3] == 1) dp[i] = dp[i] || dp[i - 3];
        }
        return dp[n];
    }
};
```

### **Go**

```go
func validPartition(nums []int) bool {
	n := len(nums)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i == n {
			return true
		}
		if f[i] != -1 {
			return f[i] == 1
		}
		res := false
		f[i] = 0
		if i < n-1 && nums[i] == nums[i+1] {
			res = res || dfs(i+2)
		}
		if i < n-2 && nums[i] == nums[i+1] && nums[i+1] == nums[i+2] {
			res = res || dfs(i+3)
		}
		if i < n-2 && nums[i+1]-nums[i] == 1 && nums[i+2]-nums[i+1] == 1 {
			res = res || dfs(i+3)
		}
		if res {
			f[i] = 1
		}
		return res
	}
	return dfs(0)
}
```

```go
func validPartition(nums []int) bool {
	n := len(nums)
	dp := make([]bool, n+1)
	dp[0] = true
	for i := 2; i <= n; i++ {
		if nums[i-1] == nums[i-2] {
			dp[i] = dp[i] || dp[i-2]
		}
		if i > 2 && nums[i-1] == nums[i-2] && nums[i-2] == nums[i-3] {
			dp[i] = dp[i] || dp[i-3]
		}
		if i > 2 && nums[i-1]-nums[i-2] == 1 && nums[i-2]-nums[i-3] == 1 {
			dp[i] = dp[i] || dp[i-3]
		}
	}
	return dp[n]
}
```

### **TypeScript**

```ts
function validPartition(nums: number[]): boolean {
    const n = nums.length;
    const vis = new Array(n).fill(false);
    const queue = [0];
    while (queue.length !== 0) {
        const i = queue.shift() ?? 0;

        if (i === n) {
            return true;
        }

        if (!vis[i + 2] && i + 2 <= n && nums[i] === nums[i + 1]) {
            queue.push(i + 2);
            vis[i + 2] = true;
        }

        if (
            !vis[i + 3] &&
            i + 3 <= n &&
            ((nums[i] === nums[i + 1] && nums[i + 1] === nums[i + 2]) ||
                (nums[i] === nums[i + 1] - 1 &&
                    nums[i + 1] === nums[i + 2] - 1))
        ) {
            queue.push(i + 3);
            vis[i + 3] = true;
        }
    }
    return false;
}
```

```ts
function validPartition(nums: number[]): boolean {
    const n = nums.length;
    const dp = new Array(n + 1).fill(false);
    dp[0] = true;
    for (let i = 2; i <= n; ++i) {
        if (nums[i - 1] == nums[i - 2]) {
            dp[i] = dp[i] || dp[i - 2];
        }
        if (i > 2 && nums[i - 1] == nums[i - 2] && nums[i - 2] == nums[i - 3]) {
            dp[i] = dp[i] || dp[i - 3];
        }
        if (
            i > 2 &&
            nums[i - 1] - nums[i - 2] == 1 &&
            nums[i - 2] - nums[i - 3] == 1
        ) {
            dp[i] = dp[i] || dp[i - 3];
        }
    }
    return dp[n];
}
```

### **...**

```

```

<!-- tabs:end -->
