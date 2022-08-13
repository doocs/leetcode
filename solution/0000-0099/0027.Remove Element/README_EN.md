# [27. Remove Element](https://leetcode.com/problems/remove-element)

[中文文档](/solution/0000-0099/0027.Remove%20Element/README.md)

## Description

<p>Given an integer array <code>nums</code> and an integer <code>val</code>, remove all occurrences of <code>val</code> in <code>nums</code> <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>in-place</strong></a>. The relative order of the elements may be changed.</p>

<p>Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the <strong>first part</strong> of the array <code>nums</code>. More formally, if there are <code>k</code> elements after removing the duplicates, then the first <code>k</code> elements of <code>nums</code> should hold the final result. It does not matter what you leave beyond the first <code>k</code> elements.</p>

<p>Return <code>k</code><em> after placing the final result in the first </em><code>k</code><em> slots of </em><code>nums</code>.</p>

<p>Do <strong>not</strong> allocate extra space for another array. You must do this by <strong>modifying the input array <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in-place</a></strong> with O(1) extra memory.</p>

<p><strong>Custom Judge:</strong></p>

<p>The judge will test your solution with the following code:</p>

<pre>
int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i &lt; actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
</pre>

<p>If all assertions pass, then your solution will be <strong>accepted</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,2,3], val = 3
<strong>Output:</strong> 2, nums = [2,2,_,_]
<strong>Explanation:</strong> Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2,2,3,0,4,2], val = 2
<strong>Output:</strong> 5, nums = [0,1,4,0,3,_,_,_]
<strong>Explanation:</strong> Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k (hence they are underscores).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 50</code></li>
	<li><code>0 &lt;= val &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        cnt, n = 0, len(nums)
        for i in range(n):
            if nums[i] == val:
                cnt += 1
            else:
                nums[i - cnt] = nums[i]
        return n - cnt
```

### **Java**

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int cnt = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == val) ++cnt;
            else nums[i - cnt] = nums[i];
        }
        return n - cnt;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        int cnt = 0, n = nums.size();
        for (int i = 0; i < n; ++i) {
            if (nums[i] == val)
                ++cnt;
            else
                nums[i - cnt] = nums[i];
        }
        return n - cnt;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} val
 * @return {number}
 */
var removeElement = function (nums, val) {
    let cnt = 0;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        if (nums[i] == val) ++cnt;
        else nums[i - cnt] = nums[i];
    }
    return n - cnt;
};
```

### **Go**

```go
func removeElement(nums []int, val int) int {
    cnt, n := 0, len(nums)
    for i := 0; i < n; i++ {
        if (nums[i] == val) {
            cnt++
        } else {
            nums[i - cnt] = nums[i]
        }
    }
    return n - cnt
}
```

### **Rust**

```rust
impl Solution {
    pub fn remove_element(nums: &mut Vec<i32>, val: i32) -> i32 {
        let mut len = 0;
        for i in 0..nums.len() {
            if nums[i] != val {
                nums[len] = nums[i];
                len += 1;
            }
        }
        len as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
