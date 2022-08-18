# [1668. Maximum Repeating Substring](https://leetcode.com/problems/maximum-repeating-substring)

[中文文档](/solution/1600-1699/1668.Maximum%20Repeating%20Substring/README.md)

## Description

<p>For a string <code>sequence</code>, a string <code>word</code> is <strong><code>k</code>-repeating</strong> if <code>word</code> concatenated <code>k</code> times is a substring of <code>sequence</code>. The <code>word</code>&#39;s <strong>maximum <code>k</code>-repeating value</strong> is the highest value <code>k</code> where <code>word</code> is <code>k</code>-repeating in <code>sequence</code>. If <code>word</code> is not a substring of <code>sequence</code>, <code>word</code>&#39;s maximum <code>k</code>-repeating value is <code>0</code>.</p>

<p>Given strings <code>sequence</code> and <code>word</code>, return <em>the <strong>maximum <code>k</code>-repeating value</strong> of <code>word</code> in <code>sequence</code></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> sequence = &quot;ababc&quot;, word = &quot;ab&quot;
<strong>Output:</strong> 2
<strong>Explanation: </strong>&quot;abab&quot; is a substring in &quot;<u>abab</u>c&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> sequence = &quot;ababc&quot;, word = &quot;ba&quot;
<strong>Output:</strong> 1
<strong>Explanation: </strong>&quot;ba&quot; is a substring in &quot;a<u>ba</u>bc&quot;. &quot;baba&quot; is not a substring in &quot;ababc&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> sequence = &quot;ababc&quot;, word = &quot;ac&quot;
<strong>Output:</strong> 0
<strong>Explanation: </strong>&quot;ac&quot; is not a substring in &quot;ababc&quot;. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sequence.length &lt;= 100</code></li>
	<li><code>1 &lt;= word.length &lt;= 100</code></li>
	<li><code>sequence</code> and <code>word</code>&nbsp;contains only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        int n = nums.size();
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int target = sum - x;
        int res = -1;
        int l = 0;
        int r = 0;
        sum = 0;
        while (r < n) {
            sum += nums[r++];
            while (sum > target && l < n) {
                sum -= nums[l++];
            }
            if (sum == target) {
                res = max(res, r - l);
            }
        }

        if (res == -1) {
            return res;
        }
        return n - res;
    }
};
```

### **TypeScript**

```ts
function minOperations(nums: number[], x: number): number {
    const n = nums.length;
    const target = nums.reduce((r, v) => r + v) - x;

    let l = 0;
    let r = 0;
    let sum = 0;
    let max = -1;
    while (r < n) {
        sum += nums[r++];
        while (sum > target && l < r) {
            sum -= nums[l++];
        }

        if (sum === target) {
            max = Math.max(max, r - l);
        }
    }

    if (max === -1) {
        return max;
    }
    return n - max;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_operations(nums: Vec<i32>, x: i32) -> i32 {
        let n = nums.len();
        let target = nums.iter().sum::<i32>() - x;


        let (mut l, mut r) = (0, 0);
        let (mut sum, mut max) = (0, -1);
        while r < n {
            sum += nums[r];
            r += 1;
            while sum > target && l < r {
                sum -= nums[l];
                l += 1;
            }


            if sum == target {
                max = max.max((r - l) as i32);
            }
        }


        if max == -1 {
            return max;
        }
        return n as i32 - max;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
