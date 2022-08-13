# [2161. Partition Array According to Given Pivot](https://leetcode.com/problems/partition-array-according-to-given-pivot)

[中文文档](/solution/2100-2199/2161.Partition%20Array%20According%20to%20Given%20Pivot/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>pivot</code>. Rearrange <code>nums</code> such that the following conditions are satisfied:</p>

<ul>
	<li>Every element less than <code>pivot</code> appears <strong>before</strong> every element greater than <code>pivot</code>.</li>
	<li>Every element equal to <code>pivot</code> appears <strong>in between</strong> the elements less than and greater than <code>pivot</code>.</li>
	<li>The <strong>relative order</strong> of the elements less than <code>pivot</code> and the elements greater than <code>pivot</code> is maintained.
	<ul>
		<li>More formally, consider every <code>p<sub>i</sub></code>, <code>p<sub>j</sub></code> where <code>p<sub>i</sub></code> is the new position of the <code>i<sup>th</sup></code> element and <code>p<sub>j</sub></code> is the new position of the <code>j<sup>th</sup></code> element. For elements less than <code>pivot</code>, if <code>i &lt; j</code> and <code>nums[i] &lt; pivot</code> and <code>nums[j] &lt; pivot</code>, then <code>p<sub>i</sub> &lt; p<sub>j</sub></code>. Similarly for elements greater than <code>pivot</code>, if <code>i &lt; j</code> and <code>nums[i] &gt; pivot</code> and <code>nums[j] &gt; pivot</code>, then <code>p<sub>i</sub> &lt; p<sub>j</sub></code>.</li>
	</ul>
	</li>
</ul>

<p>Return <code>nums</code><em> after the rearrangement.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,12,5,10,14,3,10], pivot = 10
<strong>Output:</strong> [9,5,3,10,10,12,14]
<strong>Explanation:</strong> 
The elements 9, 5, and 3 are less than the pivot so they are on the left side of the array.
The elements 12 and 14 are greater than the pivot so they are on the right side of the array.
The relative ordering of the elements less than and greater than pivot is also maintained. [9, 5, 3] and [12, 14] are the respective orderings.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-3,4,3,2], pivot = 2
<strong>Output:</strong> [-3,2,4,3]
<strong>Explanation:</strong> 
The element -3 is less than the pivot so it is on the left side of the array.
The elements 4 and 3 are greater than the pivot so they are on the right side of the array.
The relative ordering of the elements less than and greater than pivot is also maintained. [-3] and [4, 3] are the respective orderings.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>pivot</code> equals to an element of <code>nums</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def pivotArray(self, nums: List[int], pivot: int) -> List[int]:
        a, b, c = [], [], []
        for x in nums:
            if x < pivot:
                a.append(x)
            elif x == pivot:
                b.append(x)
            else:
                c.append(x)
        return a + b + c
```

### **Java**

```java
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] ans = new int[n];
        int k = 0;
        for (int x : nums) {
            if (x < pivot) {
                ans[k++] = x;
            }
        }
        for (int x : nums) {
            if (x == pivot) {
                ans[k++] = x;
            }
        }
        for (int x : nums) {
            if (x > pivot) {
                ans[k++] = x;
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
    vector<int> pivotArray(vector<int>& nums, int pivot) {
        vector<int> ans;
        for (int& x : nums)
            if (x < pivot) ans.push_back(x);
        for (int& x : nums)
            if (x == pivot) ans.push_back(x);
        for (int& x : nums)
            if (x > pivot) ans.push_back(x);
        return ans;
    }
};
```

### **Go**

```go
func pivotArray(nums []int, pivot int) []int {
	var ans []int
	for _, x := range nums {
		if x < pivot {
			ans = append(ans, x)
		}
	}
	for _, x := range nums {
		if x == pivot {
			ans = append(ans, x)
		}
	}
	for _, x := range nums {
		if x > pivot {
			ans = append(ans, x)
		}
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
