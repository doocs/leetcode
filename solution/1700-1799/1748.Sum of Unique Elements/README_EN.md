# [1748. Sum of Unique Elements](https://leetcode.com/problems/sum-of-unique-elements)

[中文文档](/solution/1700-1799/1748.Sum%20of%20Unique%20Elements/README.md)

## Description

<p>You are given an integer array <code>nums</code>. The unique elements of an array are the elements that appear <strong>exactly once</strong> in the array.</p>

<p>Return <em>the <strong>sum</strong> of all the unique elements of </em><code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The unique elements are [1,3], and the sum is 4.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no unique elements, and the sum is 0.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> 15
<strong>Explanation:</strong> The unique elements are [1,2,3,4,5], and the sum is 15.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sumOfUnique(self, nums: List[int]) -> int:
        counter = Counter(nums)
        return sum(num for num, cnt in counter.items() if cnt == 1)
```

### **Java**

```java
class Solution {
    public int sumOfUnique(int[] nums) {
        int[] counter = new int[101];
        for (int num : nums) {
            ++counter[num];
        }
        int ans = 0;
        for (int i = 0; i < 101; ++i) {
            if (counter[i] == 1) {
                ans += i;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int sumOfUnique(vector<int>& nums) {
        vector<int> counter(101);
        for (int num : nums) ++ counter[num];
        int ans = 0;
        for (int i = 0; i < 101; ++i)
            if (counter[i] == 1)
                ans += i;
        return ans;
    }
};
```

### **Go**

```go
func sumOfUnique(nums []int) int {
	counter := make([]int, 101)
	for _, num := range nums {
		counter[num]++
	}
	ans := 0
	for i := 0; i < 101; i++ {
		if counter[i] == 1 {
			ans += i
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
