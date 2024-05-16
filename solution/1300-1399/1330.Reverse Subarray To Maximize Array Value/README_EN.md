---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1330.Reverse%20Subarray%20To%20Maximize%20Array%20Value/README_EN.md
rating: 2481
source: Biweekly Contest 18 Q4
tags:
    - Greedy
    - Array
    - Math
---

<!-- problem:start -->

# [1330. Reverse Subarray To Maximize Array Value](https://leetcode.com/problems/reverse-subarray-to-maximize-array-value)

[中文文档](/solution/1300-1399/1330.Reverse%20Subarray%20To%20Maximize%20Array%20Value/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. The <em>value</em> of this array is defined as the sum of <code>|nums[i] - nums[i + 1]|</code> for all <code>0 &lt;= i &lt; nums.length - 1</code>.</p>

<p>You are allowed to select any subarray of the given array and reverse it. You can perform this operation <strong>only once</strong>.</p>

<p>Find maximum possible value of the final array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,1,5,4]
<strong>Output:</strong> 10
<b>Explanation: </b>By reversing the subarray [3,1,5] the array becomes [2,5,1,3,4] whose value is 10.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,9,24,2,1,10]
<strong>Output:</strong> 68
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def maxValueAfterReverse(self, nums: List[int]) -> int:
        ans = s = sum(abs(x - y) for x, y in pairwise(nums))
        for x, y in pairwise(nums):
            ans = max(ans, s + abs(nums[0] - y) - abs(x - y))
            ans = max(ans, s + abs(nums[-1] - x) - abs(x - y))
        for k1, k2 in pairwise((1, -1, -1, 1, 1)):
            mx, mi = -inf, inf
            for x, y in pairwise(nums):
                a = k1 * x + k2 * y
                b = abs(x - y)
                mx = max(mx, a - b)
                mi = min(mi, a + b)
            ans = max(ans, s + max(mx - mi, 0))
        return ans
```

```java
class Solution {
    public int maxValueAfterReverse(int[] nums) {
        int n = nums.length;
        int s = 0;
        for (int i = 0; i < n - 1; ++i) {
            s += Math.abs(nums[i] - nums[i + 1]);
        }
        int ans = s;
        for (int i = 0; i < n - 1; ++i) {
            ans = Math.max(
                ans, s + Math.abs(nums[0] - nums[i + 1]) - Math.abs(nums[i] - nums[i + 1]));
            ans = Math.max(
                ans, s + Math.abs(nums[n - 1] - nums[i]) - Math.abs(nums[i] - nums[i + 1]));
        }
        int[] dirs = {1, -1, -1, 1, 1};
        final int inf = 1 << 30;
        for (int k = 0; k < 4; ++k) {
            int k1 = dirs[k], k2 = dirs[k + 1];
            int mx = -inf, mi = inf;
            for (int i = 0; i < n - 1; ++i) {
                int a = k1 * nums[i] + k2 * nums[i + 1];
                int b = Math.abs(nums[i] - nums[i + 1]);
                mx = Math.max(mx, a - b);
                mi = Math.min(mi, a + b);
            }
            ans = Math.max(ans, s + Math.max(0, mx - mi));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxValueAfterReverse(vector<int>& nums) {
        int n = nums.size();
        int s = 0;
        for (int i = 0; i < n - 1; ++i) {
            s += abs(nums[i] - nums[i + 1]);
        }
        int ans = s;
        for (int i = 0; i < n - 1; ++i) {
            ans = max(ans, s + abs(nums[0] - nums[i + 1]) - abs(nums[i] - nums[i + 1]));
            ans = max(ans, s + abs(nums[n - 1] - nums[i]) - abs(nums[i] - nums[i + 1]));
        }
        int dirs[5] = {1, -1, -1, 1, 1};
        const int inf = 1 << 30;
        for (int k = 0; k < 4; ++k) {
            int k1 = dirs[k], k2 = dirs[k + 1];
            int mx = -inf, mi = inf;
            for (int i = 0; i < n - 1; ++i) {
                int a = k1 * nums[i] + k2 * nums[i + 1];
                int b = abs(nums[i] - nums[i + 1]);
                mx = max(mx, a - b);
                mi = min(mi, a + b);
            }
            ans = max(ans, s + max(0, mx - mi));
        }
        return ans;
    }
};
```

```go
func maxValueAfterReverse(nums []int) int {
	s, n := 0, len(nums)
	for i, x := range nums[:n-1] {
		y := nums[i+1]
		s += abs(x - y)
	}
	ans := s
	for i, x := range nums[:n-1] {
		y := nums[i+1]
		ans = max(ans, s+abs(nums[0]-y)-abs(x-y))
		ans = max(ans, s+abs(nums[n-1]-x)-abs(x-y))
	}
	dirs := [5]int{1, -1, -1, 1, 1}
	const inf = 1 << 30
	for k := 0; k < 4; k++ {
		k1, k2 := dirs[k], dirs[k+1]
		mx, mi := -inf, inf
		for i, x := range nums[:n-1] {
			y := nums[i+1]
			a := k1*x + k2*y
			b := abs(x - y)
			mx = max(mx, a-b)
			mi = min(mi, a+b)
		}
		ans = max(ans, s+max(mx-mi, 0))
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

```ts
function maxValueAfterReverse(nums: number[]): number {
    const n = nums.length;
    let s = 0;
    for (let i = 0; i < n - 1; ++i) {
        s += Math.abs(nums[i] - nums[i + 1]);
    }
    let ans = s;
    for (let i = 0; i < n - 1; ++i) {
        const d = Math.abs(nums[i] - nums[i + 1]);
        ans = Math.max(ans, s + Math.abs(nums[0] - nums[i + 1]) - d);
        ans = Math.max(ans, s + Math.abs(nums[n - 1] - nums[i]) - d);
    }
    const dirs = [1, -1, -1, 1, 1];
    const inf = 1 << 30;
    for (let k = 0; k < 4; ++k) {
        let mx = -inf;
        let mi = inf;
        for (let i = 0; i < n - 1; ++i) {
            const a = dirs[k] * nums[i] + dirs[k + 1] * nums[i + 1];
            const b = Math.abs(nums[i] - nums[i + 1]);
            mx = Math.max(mx, a - b);
            mi = Math.min(mi, a + b);
        }
        ans = Math.max(ans, s + Math.max(0, mx - mi));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
