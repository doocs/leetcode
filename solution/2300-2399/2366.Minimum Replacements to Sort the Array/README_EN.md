# [2366. Minimum Replacements to Sort the Array](https://leetcode.com/problems/minimum-replacements-to-sort-the-array)

[中文文档](/solution/2300-2399/2366.Minimum%20Replacements%20to%20Sort%20the%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. In one operation you can replace any element of the array with <strong>any two</strong> elements that <strong>sum</strong> to it.</p>

<ul>
	<li>For example, consider <code>nums = [5,6,7]</code>. In one operation, we can replace <code>nums[1]</code> with <code>2</code> and <code>4</code> and convert <code>nums</code> to <code>[5,2,4,7]</code>.</li>
</ul>

<p>Return <em>the minimum number of operations to make an array that is sorted in <strong>non-decreasing</strong> order</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,9,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Here are the steps to sort the array in non-decreasing order:
- From [3,9,3], replace the 9 with 3 and 6 so the array becomes [3,3,6,3]
- From [3,3,6,3], replace the 6 with 3 and 3 so the array becomes [3,3,3,3,3]
There are 2 steps to sort the array in non-decreasing order. Therefore, we return 2.

</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The array is already in non-decreasing order. Therefore, we return 0. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumReplacement(self, nums: List[int]) -> int:
        ans, n = 0, len(nums)
        mi = nums[-1]
        for i in range(n - 2, -1, -1):
            v = nums[i]
            if v <= mi:
                mi = v
                continue
            k = (v + mi - 1) // mi
            ans += k - 1
            mi = v // k
        return ans
```

### **Java**

```java
class Solution {
    public long minimumReplacement(int[] nums) {
        long ans = 0;
        int n = nums.length;
        int mi = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            int v = nums[i];
            if (v <= mi) {
                mi = v;
                continue;
            }
            int k = (v + mi - 1) / mi;
            ans += k - 1;
            mi = v / k;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long minimumReplacement(vector<int>& nums) {
        long long ans = 0;
        int n = nums.size();
        int mi = nums[n - 1];
        for (int i = n - 2; ~i; --i) {
            int v = nums[i];
            if (v <= mi) {
                mi = v;
                continue;
            }
            int k = (v + mi - 1) / mi;
            ans += k - 1;
            mi = v / k;
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumReplacement(nums []int) int64 {
	var ans int64
	n := len(nums)
	mi := nums[n-1]
	for i := n - 2; i >= 0; i-- {
		v := nums[i]
		if v <= mi {
			mi = v
			continue
		}
		k := (v + mi - 1) / mi
		ans += int64(k - 1)
		mi = v / k
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
