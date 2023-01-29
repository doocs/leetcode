# [2317. Maximum XOR After Operations](https://leetcode.com/problems/maximum-xor-after-operations)

[中文文档](/solution/2300-2399/2317.Maximum%20XOR%20After%20Operations/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. In one operation, select <strong>any</strong> non-negative integer <code>x</code> and an index <code>i</code>, then <strong>update</strong> <code>nums[i]</code> to be equal to <code>nums[i] AND (nums[i] XOR x)</code>.</p>

<p>Note that <code>AND</code> is the bitwise AND operation and <code>XOR</code> is the bitwise XOR operation.</p>

<p>Return <em>the <strong>maximum</strong> possible bitwise XOR of all elements of </em><code>nums</code><em> after applying the operation <strong>any number</strong> of times</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,4,6]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Apply the operation with x = 4 and i = 3, num[3] = 6 AND (6 XOR 4) = 6 AND 2 = 2.
Now, nums = [3, 2, 4, 2] and the bitwise XOR of all the elements = 3 XOR 2 XOR 4 XOR 2 = 7.
It can be shown that 7 is the maximum possible bitwise XOR.
Note that other operations may be used to achieve a bitwise XOR of 7.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,9,2]
<strong>Output:</strong> 11
<strong>Explanation:</strong> Apply the operation zero times.
The bitwise XOR of all the elements = 1 XOR 2 XOR 3 XOR 9 XOR 2 = 11.
It can be shown that 11 is the maximum possible bitwise XOR.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumXOR(self, nums: List[int]) -> int:
        return reduce(or_, nums)
```

### **Java**

```java
class Solution {
    public int maximumXOR(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans |= x;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumXOR(vector<int>& nums) {
        int ans = 0;
        for (int& x : nums) {
            ans |= x;
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumXOR(nums []int) (ans int) {
	for _, x := range nums {
		ans |= x
	}
	return
}
```

### **TypeScript**

```ts
function maximumXOR(nums: number[]): number {
    let ans = 0;
    for (const x of nums) {
        ans |= x;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
