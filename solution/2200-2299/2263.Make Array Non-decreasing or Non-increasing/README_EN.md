# [2263. Make Array Non-decreasing or Non-increasing](https://leetcode.com/problems/make-array-non-decreasing-or-non-increasing)

[中文文档](/solution/2200-2299/2263.Make%20Array%20Non-decreasing%20or%20Non-increasing/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. In one operation, you can:</p>

<ul>
	<li>Choose an index <code>i</code> in the range <code>0 &lt;= i &lt; nums.length</code></li>
	<li>Set <code>nums[i]</code> to <code>nums[i] + 1</code> <strong>or</strong> <code>nums[i] - 1</code></li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of operations to make </em><code>nums</code><em> <strong>non-decreasing</strong> or <strong>non-increasing</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,4,5,0]
<strong>Output:</strong> 4
<strong>Explanation:</strong>
One possible way to turn nums into non-increasing order is to:
- Add 1 to nums[1] once so that it becomes 3.
- Subtract 1 from nums[2] once so it becomes 3.
- Subtract 1 from nums[3] twice so it becomes 3.
After doing the 4 operations, nums becomes [3,3,3,3,0] which is in non-increasing order.
Note that it is also possible to turn nums into [4,4,4,4,0] in 4 operations.
It can be proven that 4 is the minimum number of operations needed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,3,4]
<strong>Output:</strong> 0
<strong>Explanation:</strong> nums is already in non-decreasing order, so no operations are needed and we return 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0]
<strong>Output:</strong> 0
<strong>Explanation:</strong> nums is already in non-decreasing order, so no operations are needed and we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Can you solve it in <code>O(n*log(n))</code> time complexity?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
