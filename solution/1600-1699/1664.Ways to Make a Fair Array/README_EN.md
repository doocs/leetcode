# [1664. Ways to Make a Fair Array](https://leetcode.com/problems/ways-to-make-a-fair-array)

[中文文档](/solution/1600-1699/1664.Ways%20to%20Make%20a%20Fair%20Array/README.md)

## Description

<p>You are given an integer array&nbsp;<code>nums</code>. You can choose <strong>exactly one</strong> index (<strong>0-indexed</strong>) and remove the element. Notice that the index of the elements may change after the removal.</p>

<p>For example, if <code>nums = [6,1,7,4,1]</code>:</p>

<ul>
	<li>Choosing to remove index <code>1</code> results in <code>nums = [6,7,4,1]</code>.</li>
	<li>Choosing to remove index <code>2</code> results in <code>nums = [6,1,4,1]</code>.</li>
	<li>Choosing to remove index <code>4</code> results in <code>nums = [6,1,7,4]</code>.</li>
</ul>

<p>An array is <strong>fair</strong> if the sum of the odd-indexed values equals the sum of the even-indexed values.</p>

<p>Return the <em><strong>number</strong> of indices that you could choose such that after the removal, </em><code>nums</code><em> </em><em>is <strong>fair</strong>. </em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,6,4]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
Remove index 0: [1,6,4] -&gt; Even sum: 1 + 4 = 5. Odd sum: 6. Not fair.
Remove index 1: [2,6,4] -&gt; Even sum: 2 + 4 = 6. Odd sum: 6. Fair.
Remove index 2: [2,1,4] -&gt; Even sum: 2 + 4 = 6. Odd sum: 1. Not fair.
Remove index 3: [2,1,6] -&gt; Even sum: 2 + 6 = 8. Odd sum: 1. Not fair.
There is 1 index that you can remove to make nums fair.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong>&nbsp;You can remove any index and the remaining array is fair.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 0
<strong>Explanation:</strong>&nbsp;You cannot make a fair array after removing any index.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def waysToMakeFair(self, nums: List[int]) -> int:
        s1, s2 = sum(nums[::2]), sum(nums[1::2])
        ans = t1 = t2 = 0
        for i, v in enumerate(nums):
            ans += i % 2 == 0 and t2 + s1 - t1 - v == t1 + s2 - t2
            ans += i % 2 == 1 and t2 + s1 - t1 == t1 + s2 - t2 - v
            t1 += v if i % 2 == 0 else 0
            t2 += v if i % 2 == 1 else 0
        return ans
```

### **Java**

```java
class Solution {
    public int waysToMakeFair(int[] nums) {
        int s1 = 0, s2 = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            s1 += i % 2 == 0 ? nums[i] : 0;
            s2 += i % 2 == 1 ? nums[i] : 0;
        }
        int t1 = 0, t2 = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            ans += i % 2 == 0 && t2 + s1 - t1 - v == t1 + s2 - t2 ? 1 : 0;
            ans += i % 2 == 1 && t2 + s1 - t1 == t1 + s2 - t2 - v ? 1 : 0;
            t1 += i % 2 == 0 ? v : 0;
            t2 += i % 2 == 1 ? v : 0;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int waysToMakeFair(vector<int>& nums) {
        int s1 = 0, s2 = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            s1 += i % 2 == 0 ? nums[i] : 0;
            s2 += i % 2 == 1 ? nums[i] : 0;
        }
        int t1 = 0, t2 = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            ans += i % 2 == 0 && t2 + s1 - t1 - v == t1 + s2 - t2;
            ans += i % 2 == 1 && t2 + s1 - t1 == t1 + s2 - t2 - v;
            t1 += i % 2 == 0 ? v : 0;
            t2 += i % 2 == 1 ? v : 0;
        }
        return ans;
    }
};
```

### **Go**

```go
func waysToMakeFair(nums []int) (ans int) {
	var s1, s2, t1, t2 int
	for i, v := range nums {
		if i%2 == 0 {
			s1 += v
		} else {
			s2 += v
		}
	}
	for i, v := range nums {
		if i%2 == 0 && t2+s1-t1-v == t1+s2-t2 {
			ans++
		}
		if i%2 == 1 && t2+s1-t1 == t1+s2-t2-v {
			ans++
		}
		if i%2 == 0 {
			t1 += v
		} else {
			t2 += v
		}
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var waysToMakeFair = function (nums) {
    let [s1, s2, t1, t2] = [0, 0, 0, 0];
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        if (i % 2 == 0) {
            s1 += nums[i];
        } else {
            s2 += nums[i];
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const v = nums[i];
        ans += i % 2 == 0 && t2 + s1 - t1 - v == t1 + s2 - t2;
        ans += i % 2 == 1 && t2 + s1 - t1 == t1 + s2 - t2 - v;
        t1 += i % 2 == 0 ? v : 0;
        t2 += i % 2 == 1 ? v : 0;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
