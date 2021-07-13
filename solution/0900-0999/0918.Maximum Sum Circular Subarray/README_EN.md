# [918. Maximum Sum Circular Subarray](https://leetcode.com/problems/maximum-sum-circular-subarray)

[中文文档](/solution/0900-0999/0918.Maximum%20Sum%20Circular%20Subarray/README.md)

## Description

<p>Given a <strong>circular&nbsp;array</strong>&nbsp;<strong>C</strong> of integers represented by&nbsp;<code>A</code>, find the maximum possible sum of a non-empty subarray of <strong>C</strong>.</p>



<p>Here, a&nbsp;<em>circular&nbsp;array</em> means the end of the array connects to the beginning of the array.&nbsp; (Formally, <code>C[i] = A[i]</code> when <code>0 &lt;= i &lt; A.length</code>, and <code>C[i+A.length] = C[i]</code>&nbsp;when&nbsp;<code>i &gt;= 0</code>.)</p>



<p>Also, a subarray may only include each element of the fixed buffer <code>A</code> at most once.&nbsp; (Formally, for a subarray <code>C[i], C[i+1], ..., C[j]</code>, there does not exist <code>i &lt;= k1, k2 &lt;= j</code> with <code>k1 % A.length&nbsp;= k2 % A.length</code>.)</p>



<p>&nbsp;</p>



<div>

<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-1-1">[1,-2,3,-2]</span>

<strong>Output: </strong><span id="example-output-1">3

<strong>Explanation: </strong>Subarray [3] has maximum sum 3</span>

</pre>



<div>

<p><strong>Example 2:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-2-1">[5,-3,5]</span>

<strong>Output: </strong><span id="example-output-2">10

</span><span id="example-output-3"><strong>Explanation:</strong>&nbsp;</span><span id="example-output-1">Subarray [5,5] has maximum sum </span><span>5 + 5 = 10</span>

</pre>



<div>

<p><strong>Example 3:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-3-1">[3,-1,2,-1]</span>

<strong>Output: </strong><span id="example-output-3">4

<strong>Explanation:</strong>&nbsp;</span><span id="example-output-1">Subarray [2,-1,3] has maximum sum </span><span>2 + (-1) + 3 = 4</span>

</pre>



<div>

<p><strong>Example 4:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-4-1">[3,-2,2,-3]</span>

<strong>Output: </strong><span id="example-output-4">3

</span><span id="example-output-3"><strong>Explanation:</strong>&nbsp;</span><span id="example-output-1">Subarray [3] and [3,-2,2] both have maximum sum </span><span>3</span>

</pre>



<p><strong>Example 5:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-5-1">[-2,-3,-1]</span>

<strong>Output: </strong><span id="example-output-5">-1

</span><span id="example-output-3"><strong>Explanation:</strong>&nbsp;</span><span id="example-output-1">Subarray [-1] has maximum sum -1</span>

</pre>



<p>&nbsp;</p>



<p><strong>Note: </strong></p>



<ol>
	<li><code>-30000 &lt;= A[i] &lt;= 30000</code></li>
	<li><code>1 &lt;= A.length &lt;= 30000</code></li>
</ol>

</div>

</div>

</div>

</div>



## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSubarraySumCircular(self, nums: List[int]) -> int:
        s1 = s2 = f1 = f2 = nums[0]
        for num in nums[1:]:
            f1 = num + max(f1, 0)
            f2 = num + min(f2, 0)
            s1 = max(s1, f1)
            s2 = min(s2, f2)
        return s1 if s1 <= 0 else max(s1, sum(nums) - s2)
```

### **Java**

```java
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int s1 = nums[0], s2 = nums[0], f1 = nums[0], f2 = nums[0], total = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            total += nums[i];
            f1 = nums[i] + Math.max(f1, 0);
            f2 = nums[i] + Math.min(f2, 0);
            s1 = Math.max(s1, f1);
            s2 = Math.min(s2, f2);
        }
        return s1 > 0 ? Math.max(s1, total - s2) : s1;
    }
}
```

### **TypeScript**

```ts
function maxSubarraySumCircular(nums: number[]): number {
    let pre1 = nums[0], pre2 = nums[0];
    let ans1 = nums[0], ans2 = nums[0];
    let sum = nums[0];
    
    for (let i = 1; i < nums.length; ++i) {
        let cur = nums[i];
        sum += cur;
        pre1 = Math.max(pre1 + cur, cur);
        ans1 = Math.max(pre1, ans1);

        pre2 = Math.min(pre2 + cur, cur);
        ans2 = Math.min(pre2, ans2);
    }
    return ans1 > 0 ? Math.max(ans1, sum - ans2) : ans1;
};
```

### **C++**

```cpp
class Solution {
public:
    int maxSubarraySumCircular(vector<int>& nums) {
        int s1 = nums[0], s2 = nums[0], f1 = nums[0], f2 = nums[0], total = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            total += nums[i];
            f1 = nums[i] + max(f1, 0);
            f2 = nums[i] + min(f2, 0);
            s1 = max(s1, f1);
            s2 = min(s2, f2);
        }
        return s1 > 0 ? max(s1, total - s2) : s1;
    }
};
```

### **Go**

```go
func maxSubarraySumCircular(nums []int) int {
	s1, s2, f1, f2, total := nums[0], nums[0], nums[0], nums[0], nums[0]
	for i := 1; i < len(nums); i++ {
		total += nums[i]
		f1 = nums[i] + max(f1, 0)
		f2 = nums[i] + min(f2, 0)
		s1 = max(s1, f1)
		s2 = min(s2, f2)
	}
	if s1 <= 0 {
		return s1
	}
	return max(s1, total-s2)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
