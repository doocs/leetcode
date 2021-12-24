# [2044. Count Number of Maximum Bitwise-OR Subsets](https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets)

[中文文档](/solution/2000-2099/2044.Count%20Number%20of%20Maximum%20Bitwise-OR%20Subsets/README.md)

## Description

<p>Given an integer array <code>nums</code>, find the <strong>maximum</strong> possible <strong>bitwise OR</strong> of a subset of <code>nums</code> and return <em>the <strong>number of different non-empty subsets</strong> with the maximum bitwise OR</em>.</p>

<p>An array <code>a</code> is a <strong>subset</strong> of an array <code>b</code> if <code>a</code> can be obtained from <code>b</code> by deleting some (possibly zero) elements of <code>b</code>. Two subsets are considered <strong>different</strong> if the indices of the elements chosen are different.</p>

<p>The bitwise OR of an array <code>a</code> is equal to <code>a[0] <strong>OR</strong> a[1] <strong>OR</strong> ... <strong>OR</strong> a[a.length - 1]</code> (<strong>0-indexed</strong>).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
- [3]
- [3,1]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2]
<strong>Output:</strong> 7
<strong>Explanation:</strong> All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 2<sup>3</sup> - 1 = 7 total subsets.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,5]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The maximum possible bitwise OR of a subset is 7. There are 6 subsets with a bitwise OR of 7:
- [3,5]
- [3,1,5]
- [3,2,5]
- [3,2,1,5]
- [2,5]
- [2,1,5]</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 16</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countMaxOrSubsets(self, nums: List[int]) -> int:
        mx = ans = 0
        for x in nums:
            mx |= x

        def dfs(i, t):
            nonlocal mx, ans
            if i == len(nums):
                if t == mx:
                    ans += 1
                return
            dfs(i + 1, t)
            dfs(i + 1, t | nums[i])

        dfs(0, 0)
        return ans
```

### **Java**

```java
class Solution {
    private int mx;
    private int ans;
    private int[] nums;

    public int countMaxOrSubsets(int[] nums) {
        mx = 0;
        for (int x : nums) {
            mx |= x;
        }
        this.nums = nums;
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int t) {
        if (i == nums.length) {
            if (t == mx) {
                ++ans;
            }
            return;
        }
        dfs(i + 1, t);
        dfs(i + 1, t | nums[i]);
    }
}
```

### **TypeScript**

```ts
function countMaxOrSubsets(nums: number[]): number {
    let n = nums.length;
    let max = 0;
    for (let i = 0; i < n; i++) {
        max |= nums[i];
    }
    let ans = 0;
    function dfs(pre: number, depth: number): void {
        if (depth == n) {
            if (pre == max) ++ans;
            return;
        }
        dfs(pre, depth + 1);
        dfs(pre | nums[depth], depth + 1);
    }
    dfs(0, 0);
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int mx;
    int ans;
    vector<int> nums;

    int countMaxOrSubsets(vector<int>& nums) {
        this->nums = nums;
        mx = 0;
        ans = 0;
        for (int x : nums) mx |= x;
        dfs(0, 0);
        return ans;
    }

    void dfs(int i, int t) {
        if (i == nums.size())
        {
            if (t == mx) ++ans;
            return;
        }
        dfs(i + 1, t);
        dfs(i + 1, t | nums[i]);
    }
};
```

### **Go**

```go
func countMaxOrSubsets(nums []int) int {
	mx, ans := 0, 0
	for _, x := range nums {
		mx |= x
	}

	var dfs func(i, t int)
	dfs = func(i, t int) {
		if i == len(nums) {
			if t == mx {
				ans++
			}
			return
		}
		dfs(i+1, t)
		dfs(i+1, t|nums[i])
	}

	dfs(0, 0)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
