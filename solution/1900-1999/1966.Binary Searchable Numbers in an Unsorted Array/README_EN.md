# [1966. Binary Searchable Numbers in an Unsorted Array](https://leetcode.com/problems/binary-searchable-numbers-in-an-unsorted-array)

[中文文档](/solution/1900-1999/1966.Binary%20Searchable%20Numbers%20in%20an%20Unsorted%20Array/README.md)

## Description

<p>Consider a function that implements an algorithm <strong>similar</strong> to <a href="https://leetcode.com/explore/learn/card/binary-search/" target="_blank">Binary Search</a>. The function has two input parameters: <code>sequence</code> is a sequence of integers, and <code>target</code> is an integer value. The purpose of the function is to find if the <code>target</code> exists in the <code>sequence</code>.</p>

<p>The pseudocode of the function is as follows:</p>

<pre>
func(sequence, target)
  while sequence is not empty
    <strong>randomly</strong> choose an element from sequence as the pivot
    if pivot = target, return <strong>true</strong>
    else if pivot &lt; target, remove pivot and all elements to its left from the sequence
    else, remove pivot and all elements to its right from the sequence
  end while
  return <strong>false</strong>
</pre>

<p>When the <code>sequence</code> is sorted, the function works correctly for <strong>all</strong> values. When the <code>sequence</code> is not sorted, the function does not work for all values, but may still work for <strong>some</strong> values.</p>

<p>Given an integer array <code>nums</code>, representing the <code>sequence</code>, that contains <strong>unique</strong> numbers and <strong>may or may not be sorted</strong>, return <em>the number of values that are <strong>guaranteed</strong> to be found using the function, for <strong>every possible</strong> pivot selection</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [7]
<strong>Output:</strong> 1
<strong>Explanation</strong>: 
Searching for value 7 is guaranteed to be found.
Since the sequence has only one element, 7 will be chosen as the pivot. Because the pivot equals the target, the function will return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,5,2]
<strong>Output:</strong> 1
<strong>Explanation</strong>: 
Searching for value -1 is guaranteed to be found.
If -1 was chosen as the pivot, the function would return true.
If 5 was chosen as the pivot, 5 and 2 would be removed. In the next loop, the sequence would have only -1 and the function would return true.
If 2 was chosen as the pivot, 2 would be removed. In the next loop, the sequence would have -1 and 5. No matter which number was chosen as the next pivot, the function would find -1 and return true.

Searching for value 5 is NOT guaranteed to be found.
If 2 was chosen as the pivot, -1, 5 and 2 would be removed. The sequence would be empty and the function would return false.

Searching for value 2 is NOT guaranteed to be found.
If 5 was chosen as the pivot, 5 and 2 would be removed. In the next loop, the sequence would have only -1 and the function would return false.

Because only -1 is guaranteed to be found, you should return 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li>All the values of <code>nums</code> are <strong>unique</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow-up:</strong> If <code>nums</code> has <strong>duplicates</strong>, would you modify your algorithm? If so, how?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def binarySearchableNumbers(self, nums: List[int]) -> int:
        n = len(nums)
        ok = [1] * n
        mx, mi = -1000000, 1000000
        for i, x in enumerate(nums):
            if x < mx:
                ok[i] = 0
            else:
                mx = x
        for i in range(n - 1, -1, -1):
            if nums[i] > mi:
                ok[i] = 0
            else:
                mi = nums[i]
        return sum(ok)
```

### **Java**

```java
class Solution {
    public int binarySearchableNumbers(int[] nums) {
        int n = nums.length;
        int[] ok = new int[n];
        Arrays.fill(ok, 1);
        int mx = -1000000, mi = 1000000;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < mx) {
                ok[i] = 0;
            }
            mx = Math.max(mx, nums[i]);
        }
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (nums[i] > mi) {
                ok[i] = 0;
            }
            mi = Math.min(mi, nums[i]);
            ans += ok[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int binarySearchableNumbers(vector<int>& nums) {
        int n = nums.size();
        vector<int> ok(n, 1);
        int mx = -1000000, mi = 1000000;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < mx) {
                ok[i] = 0;
            }
            mx = max(mx, nums[i]);
        }
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (nums[i] > mi) {
                ok[i] = 0;
            }
            mi = min(mi, nums[i]);
            ans += ok[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func binarySearchableNumbers(nums []int) (ans int) {
	n := len(nums)
	ok := make([]int, n)
	for i := range ok {
		ok[i] = 1
	}
	mx, mi := -1000000, 1000000
	for i, x := range nums {
		if x < mx {
			ok[i] = 0
		} else {
			mx = x
		}
	}
	for i := n - 1; i >= 0; i-- {
		if nums[i] > mi {
			ok[i] = 0
		} else {
			mi = nums[i]
		}
		ans += ok[i]
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
