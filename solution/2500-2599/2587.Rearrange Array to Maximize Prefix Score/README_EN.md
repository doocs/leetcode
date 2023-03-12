# [2587. Rearrange Array to Maximize Prefix Score](https://leetcode.com/problems/rearrange-array-to-maximize-prefix-score)

[中文文档](/solution/2500-2599/2587.Rearrange%20Array%20to%20Maximize%20Prefix%20Score/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. You can rearrange the elements of <code>nums</code> to <strong>any order</strong> (including the given order).</p>

<p>Let <code>prefix</code> be the array containing the prefix sums of <code>nums</code> after rearranging it. In other words, <code>prefix[i]</code> is the sum of the elements from <code>0</code> to <code>i</code> in <code>nums</code> after rearranging it. The <strong>score</strong> of <code>nums</code> is the number of positive integers in the array <code>prefix</code>.</p>

<p>Return <em>the maximum score you can achieve</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,-1,0,1,-3,3,-3]
<strong>Output:</strong> 6
<strong>Explanation:</strong> We can rearrange the array into nums = [2,3,1,-1,-3,0,-3].
prefix = [2,5,6,5,2,2,-1], so the score is 6.
It can be shown that 6 is the maximum score we can obtain.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,-3,0]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Any rearrangement of the array will result in a score of 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxScore(self, nums: List[int]) -> int:
        nums.sort(reverse=True)
        s = 0
        for i, x in enumerate(nums):
            s += x
            if s <= 0:
                return i
        return len(nums)
```

### **Java**

```java
class Solution {
    public int maxScore(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long s = 0;
        for (int i = 0; i < n; ++i) {
            s += nums[n - i - 1];
            if (s <= 0) {
                return i;
            }
        }
        return n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxScore(vector<int>& nums) {
        sort(nums.rbegin(), nums.rend());
        long long s = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (s <= 0) {
                return i;
            }
        }
        return n;
    }
};
```

### **Go**

```go
func maxScore(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	s := 0
	for i := range nums {
		s += nums[n-i-1]
		if s <= 0 {
			return i
		}
	}
	return n
}
```

### **TypeScript**

```ts
function maxScore(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let s = 0;
    for (let i = 0; i < n; ++i) {
        s += nums[n - i - 1];
        if (s <= 0) {
            return i;
        }
    }
    return n;
}
```

### **...**

```

```

<!-- tabs:end -->
