# [2592. Maximize Greatness of an Array](https://leetcode.com/problems/maximize-greatness-of-an-array)

[中文文档](/solution/2500-2599/2592.Maximize%20Greatness%20of%20an%20Array/README.md)

## Description

<p>You are given a 0-indexed integer array <code>nums</code>. You are allowed to permute <code>nums</code> into a new array <code>perm</code> of your choosing.</p>

<p>We define the <strong>greatness</strong> of <code>nums</code> be the number of indices <code>0 &lt;= i &lt; nums.length</code> for which <code>perm[i] &gt; nums[i]</code>.</p>

<p>Return <em>the <strong>maximum</strong> possible greatness you can achieve after permuting</em> <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,2,1,3,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> One of the optimal rearrangements is perm = [2,5,1,3,3,1,1].
At indices = 0, 1, 3, and 4, perm[i] &gt; nums[i]. Hence, we return 4.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can prove the optimal perm is [2,3,4,1].
At indices = 0, 1, and 2, perm[i] &gt; nums[i]. Hence, we return 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximizeGreatness(self, nums: List[int]) -> int:
        nums.sort()
        i = 0
        for x in nums:
            i += x > nums[i]
        return i
```

### **Java**

```java
class Solution {
    public int maximizeGreatness(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        for (int x : nums) {
            if (x > nums[i]) {
                ++i;
            }
        }
        return i;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximizeGreatness(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int i = 0;
        for (int x : nums) {
            i += x > nums[i];
        }
        return i;
    }
};
```

### **Go**

```go
func maximizeGreatness(nums []int) int {
	sort.Ints(nums)
	i := 0
	for _, x := range nums {
		if x > nums[i] {
			i++
		}
	}
	return i
}
```

### **...**

```

```

<!-- tabs:end -->
