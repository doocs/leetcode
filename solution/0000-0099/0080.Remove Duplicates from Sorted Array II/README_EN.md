# [80. Remove Duplicates from Sorted Array II](https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii)

[中文文档](/solution/0000-0099/0080.Remove%20Duplicates%20from%20Sorted%20Array%20II/README.md)

## Description

<p>Given an integer array <code>nums</code> sorted in <strong>non-decreasing order</strong>, remove some duplicates <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>in-place</strong></a> such that each unique element appears <strong>at most twice</strong>. The <strong>relative order</strong> of the elements should be kept the <strong>same</strong>.</p>

<p>Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the <strong>first part</strong> of the array <code>nums</code>. More formally, if there are <code>k</code> elements after removing the duplicates, then the first <code>k</code> elements of <code>nums</code>&nbsp;should hold the final result. It does not matter what you leave beyond the first&nbsp;<code>k</code>&nbsp;elements.</p>

<p>Return <code>k</code><em> after placing the final result in the first </em><code>k</code><em> slots of </em><code>nums</code>.</p>

<p>Do <strong>not</strong> allocate extra space for another array. You must do this by <strong>modifying the input array <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in-place</a></strong> with O(1) extra memory.</p>

<p><strong>Custom Judge:</strong></p>

<p>The judge will test your solution with the following code:</p>

<pre>
int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i &lt; k; i++) {
    assert nums[i] == expectedNums[i];
}
</pre>

<p>If all assertions pass, then your solution will be <strong>accepted</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,2,2,3]
<strong>Output:</strong> 5, nums = [1,1,2,2,3,_]
<strong>Explanation:</strong> Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,1,1,1,1,2,3,3]
<strong>Output:</strong> 7, nums = [0,0,1,1,2,3,3,_,_]
<strong>Explanation:</strong> Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> is sorted in <strong>non-decreasing</strong> order.</li>
</ul>

## Solutions

**Solution 1: Single Pass**

We use a variable $k$ to record the current length of the array that has been processed. Initially, $k=0$, representing an empty array.

Then we traverse the array from left to right. For each element $x$ we traverse, if $k < 2$ or $x \neq nums[k-2]$, we put $x$ in the position of $nums[k]$, and then increment $k$ by $1$. Otherwise, $x$ is the same as $nums[k-2]$, we directly skip this element. Continue to traverse until the entire array is traversed.

In this way, when the traversal ends, the first $k$ elements in $nums$ are the answer we want, and $k$ is the length of the answer.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array.

Supplement:

The original problem requires that the same number appears at most $2$ times. We can extend it to keep at most $k$ identical numbers.

-   Since the same number can be kept at most $k$ times, we can directly keep the first $k$ elements of the original array;
-   For the later numbers, the premise of being able to keep them is: the current number $x$ compares with the $k$th element from the end of the previously kept numbers. If they are different, keep it, otherwise skip it.

Similar problems:

-   [26. Remove Duplicates from Sorted Array](/solution/0000-0099/0026.Remove%20Duplicates%20from%20Sorted%20Array/README_EN.md)

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        k = 0
        for x in nums:
            if k < 2 or x != nums[k - 2]:
                nums[k] = x
                k += 1
        return k
```

### **Java**

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 0;
        for (int x : nums) {
            if (k < 2 || x != nums[k - 2]) {
                nums[k++] = x;
            }
        }
        return k;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        int k = 0;
        for (int x : nums) {
            if (k < 2 || x != nums[k - 2]) {
                nums[k++] = x;
            }
        }
        return k;
    }
};
```

### **Go**

```go
func removeDuplicates(nums []int) int {
	k := 0
	for _, x := range nums {
		if k < 2 || x != nums[k-2] {
			nums[k] = x
			k++
		}
	}
	return k
}
```

### **TypeScript**

```ts
function removeDuplicates(nums: number[]): number {
    let k = 0;
    for (const x of nums) {
        if (k < 2 || x !== nums[k - 2]) {
            nums[k++] = x;
        }
    }
    return k;
}
```

### **C#**

```cs
public class Solution {
    public int RemoveDuplicates(int[] nums) {
        int k = 0;
        foreach (int x in nums) {
            if (k < 2 || x != nums[k - 2]) {
                nums[k++] = x;
            }
        }
        return k;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var removeDuplicates = function (nums) {
    let k = 0;
    for (const x of nums) {
        if (k < 2 || x !== nums[k - 2]) {
            nums[k++] = x;
        }
    }
    return k;
};
```

### **Rust**

```rust
impl Solution {
    pub fn remove_duplicates(nums: &mut Vec<i32>) -> i32 {
        let mut k = 0;
        for i in 0..nums.len() {
            if k < 2 || nums[i] != nums[k - 2] {
                nums[k] = nums[i];
                k += 1;
            }
        }
        k as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
