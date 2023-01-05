# [2524. Maximum Frequency Score of a Subarray](https://leetcode.com/problems/maximum-frequency-score-of-a-subarray)

[中文文档](/solution/2500-2599/2524.Maximum%20Frequency%20Score%20of%20a%20Subarray/README.md)

## Description

<p>You are given an integer array <code>nums</code> and a <strong>positive</strong> integer <code>k</code>.</p>

<p>The <strong>frequency score</strong> of an array is the sum of the <strong>distinct</strong> values in the array raised to the power of their <strong>frequencies</strong>, taking the sum <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<ul>
	<li>For example, the frequency score of the array <code>[5,4,5,7,4,4]</code> is <code>(4<sup>3</sup> + 5<sup>2</sup> + 7<sup>1</sup>) modulo (10<sup>9</sup> + 7) = 96</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> frequency score of a <strong>subarray</strong> of size </em><code>k</code><em> in </em><code>nums</code>. You should maximize the value under the modulo and not the actual value.</p>

<p>A <strong>subarray</strong> is a contiguous part of an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,2,1,2], k = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong> The subarray [2,1,2] has a frequency score equal to 5. It can be shown that it is the maximum frequency score we can have.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1,1], k = 4
<strong>Output:</strong> 1
<strong>Explanation:</strong> All the subarrays of length 4 have a frequency score equal to 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxFrequencyScore(self, nums: List[int], k: int) -> int:
        mod = 10**9 + 7
        cnt = Counter(nums[:k])
        ans = cur = sum(pow(k, v, mod) for k, v in cnt.items()) % mod
        i = k
        while i < len(nums):
            a, b = nums[i - k], nums[i]
            if a != b:
                cur += (b - 1) * pow(b, cnt[b], mod) if cnt[b] else b
                cur -= (a - 1) * pow(a, cnt[a] - 1, mod) if cnt[a] > 1 else a
                cur %= mod
                cnt[b] += 1
                cnt[a] -= 1
                ans = max(ans, cur)
            i += 1
        return ans
```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
