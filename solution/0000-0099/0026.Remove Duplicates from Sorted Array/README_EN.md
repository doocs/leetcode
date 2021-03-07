# [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array)

[中文文档](/solution/0000-0099/0026.Remove%20Duplicates%20from%20Sorted%20Array/README.md)

## Description

<p>Given a sorted array <em>nums</em>, remove the duplicates <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>in-place</strong></a> such that each element appear only <em>once</em> and return the new length.</p>

<p>Do not allocate extra space for another array, you must do this by <strong>modifying the input array <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in-place</a></strong> with O(1) extra memory.</p>

<p><strong>Example 1:</strong></p>

<pre>

Given <em>nums</em> = <strong>[1,1,2]</strong>,



Your function should return length = <strong><code>2</code></strong>, with the first two elements of <em><code>nums</code></em> being <strong><code>1</code></strong> and <strong><code>2</code></strong> respectively.



It doesn&#39;t matter what you leave beyond the returned length.</pre>

<p><strong>Example 2:</strong></p>

<pre>

Given <em>nums</em> = <strong>[0,0,1,1,1,2,2,3,3,4]</strong>,



Your function should return length = <strong><code>5</code></strong>, with the first five elements of <em><code>nums</code></em> being modified to&nbsp;<strong><code>0</code></strong>, <strong><code>1</code></strong>, <strong><code>2</code></strong>, <strong><code>3</code></strong>, and&nbsp;<strong><code>4</code></strong> respectively.



It doesn&#39;t matter what values are set beyond&nbsp;the returned length.

</pre>

<p><strong>Clarification:</strong></p>

<p>Confused why the returned value is an integer but your answer is an array?</p>

<p>Note that the input array is passed in by <strong>reference</strong>, which means modification to the input array will be known to the caller as well.</p>

<p>Internally you can think of this:</p>

<pre>

// <strong>nums</strong> is passed in by reference. (i.e., without making a copy)

int len = removeDuplicates(nums);



// any modification to <strong>nums</strong> in your function would be known by the caller.

// using the length returned by your function, it prints the first <strong>len</strong> elements.

for (int i = 0; i &lt; len; i++) {

&nbsp; &nbsp; print(nums[i]);

}</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        cnt, n = 0, len(nums)
        for i in range(1, n):
            if nums[i] == nums[i - 1]:
                cnt += 1
            else:
                nums[i - cnt] = nums[i]
        return n - cnt
```

### **Java**

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int cnt = 0, n = nums.length;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == nums[i - 1]) ++cnt;
            else nums[i - cnt] = nums[i];
        }
        return n - cnt;
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
  let cnt = 0;
  const n = nums.length;
  for (let i = 1; i < n; ++i) {
    if (nums[i] == nums[i - 1]) ++cnt;
    else nums[i - cnt] = nums[i];
  }
  return n - cnt;
};
```

### **Go**

```go
func removeDuplicates(nums []int) int {
    cnt := 0
    n := len(nums)
    for i := 1; i < n; i++ {
        if nums[i] == nums[i - 1] {
            cnt++
        } else {
            nums[i - cnt] = nums[i]
        }
    }
    return n - cnt
}
```

### **...**

```

```

<!-- tabs:end -->
