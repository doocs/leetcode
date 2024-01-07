# [10031. Smallest Missing Integer Greater Than Sequential Prefix Sum](https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum)

[中文文档](/solution/10000-10099/10031.Smallest%20Missing%20Integer%20Greater%20Than%20Sequential%20Prefix%20Sum/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of integers <code>nums</code>.</p>

<p>A prefix <code>nums[0..i]</code> is <strong>sequential</strong> if, for all <code>1 &lt;= j &lt;= i</code>, <code>nums[j] = nums[j - 1] + 1</code>. In particular, the prefix consisting only of <code>nums[0]</code> is <strong>sequential</strong>.</p>

<p>Return <em>the <strong>smallest</strong> integer</em> <code>x</code> <em>missing from</em> <code>nums</code> <em>such that</em> <code>x</code> <em>is greater than or equal to the sum of the <strong>longest</strong> sequential prefix.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,2,5]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The longest sequential prefix of nums is [1,2,3] with a sum of 6. 6 is not in the array, therefore 6 is the smallest missing integer greater than or equal to the sum of the longest sequential prefix.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,1,12,14,13]
<strong>Output:</strong> 15
<strong>Explanation:</strong> The longest sequential prefix of nums is [3,4,5] with a sum of 12. 12, 13, and 14 belong to the array while 15 does not. Therefore 15 is the smallest missing integer greater than or equal to the sum of the longest sequential prefix.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def missingInteger(self, nums: List[int]) -> int:
        s, n = nums[0], len(nums)
        j = 1
        while j < len(nums) and nums[j] == nums[j - 1] + 1:
            s += nums[j]
            j += 1
        vis = set(nums)
        for x in count(s):
            if x not in vis:
                return x
```

### **Java**

```java
class Solution {
    public int missingInteger(int[] nums) {
        int s = nums[0], j = 1;
        while (j < nums.length && nums[j] == nums[j - 1] + 1) {
            s += nums[j++];
        }
        boolean[] vis = new boolean[51];
        for (int x : nums) {
            vis[x] = true;
        }
        for (int x = s;; ++x) {
            if (x >= vis.length || !vis[x]) {
                return x;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int missingInteger(vector<int>& nums) {
        int s = nums[0], j = 1;
        while (j < nums.size() && nums[j] == nums[j - 1] + 1) {
            s += nums[j++];
        }
        bool vis[51]{};
        for (int x : nums) {
            vis[x] = true;
        }
        for (int x = s;; ++x) {
            if (x >= 51 || !vis[x]) {
                return x;
            }
        }
    }
};
```

### **Go**

```go
func missingInteger(nums []int) int {
	s, j := nums[0], 1
	for j < len(nums) && nums[j] == nums[j-1]+1 {
		s, j = s+nums[j], j+1
	}
	vis := [51]bool{}
	for _, x := range nums {
		vis[x] = true
	}
	for x := s; ; x++ {
		if x >= len(vis) || !vis[x] {
			return x
		}
	}
}
```

### **TypeScript**

```ts
function missingInteger(nums: number[]): number {
    let [s, j] = [nums[0], 1];
    const n = nums.length;
    while (j < n && nums[j] === nums[j - 1] + 1) {
        s += nums[j++];
    }
    const vis: boolean[] = Array(51).fill(false);
    for (const x of nums) {
        vis[x] = true;
    }
    for (let x = s; ; ++x) {
        if (x >= vis.length || !vis[x]) {
            return x;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
