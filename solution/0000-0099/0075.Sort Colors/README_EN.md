# [75. Sort Colors](https://leetcode.com/problems/sort-colors)

[中文文档](/solution/0000-0099/0075.Sort%20Colors/README.md)

## Description

<p>Given an array <code>nums</code> with <code>n</code> objects colored red, white, or blue, sort them <strong><a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in-place</a> </strong>so that objects of the same color are adjacent, with the colors in the order red, white, and blue.</p>

<p>We will use the integers <code>0</code>, <code>1</code>, and <code>2</code> to represent the color red, white, and blue, respectively.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [2,0,2,1,1,0]
<strong>Output:</strong> [0,0,1,1,2,2]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,0,1]
<strong>Output:</strong> [0,1,2]
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [0]
<strong>Output:</strong> [0]
</pre><p><strong>Example 4:</strong></p>
<pre><strong>Input:</strong> nums = [1]
<strong>Output:</strong> [1]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>nums[i]</code> is <code>0</code>, <code>1</code>, or <code>2</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>Could you solve this problem without using the library&#39;s sort function?</li>
	<li>Could you come up with a one-pass algorithm using only <code>O(1)</code> constant space?</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sortColors(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        i, j = -1, len(nums)
        cur = 0
        while cur < j:
            if nums[cur] == 0:
                i += 1
                nums[cur], nums[i] = nums[i], nums[cur]
                cur += 1
            elif nums[cur] == 1:
                cur += 1
            else:
                j -= 1
                nums[cur], nums[j] = nums[j], nums[cur]
```

### **Java**

```java
class Solution {
    public void sortColors(int[] nums) {
        int i = -1, j = nums.length;
        int cur = 0;
        while (cur < j) {
            if (nums[cur] == 0) {
                swap(nums, cur++, ++i);
            } else if (nums[cur] == 1) {
                ++cur;
            } else {
                swap(nums, cur, --j);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

### **TypeScript**

```ts
/**
 Do not return anything, modify nums in-place instead.
 */
function sortColors(nums: number[]): void {
    let n = nums.length;
    if (n < 2) return;
    let p0 = 0,
        p2 = n - 1;
    let p1 = 0;
    while (p1 <= p2) {
        if (nums[p1] == 0) {
            [nums[p0], nums[p1]] = [nums[p1], nums[p0]];
            p0++;
            p1++;
        } else if (nums[p1] == 1) {
            p1++;
        } else {
            [nums[p1], nums[p2]] = [nums[p2], nums[p1]];
            p2--;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    void sortColors(vector<int>& nums) {
        int i = -1, j = nums.size(), cur = 0;
        while (cur < j) {
            if (nums[cur] == 0) {
                swap(nums[++i], nums[cur++]);
            } else if (nums[cur] == 1) {
                ++cur;
            } else {
                swap(nums[cur], nums[--j]);
            }
        }
    }
};
```

### **Go**

```go
func sortColors(nums []int) {
	i, j, cur := -1, len(nums), 0
	for cur < j {
		if nums[cur] == 0 {
			i++
			nums[cur], nums[i] = nums[i], nums[cur]
			cur++
		} else if nums[cur] == 1 {
			cur++
		} else {
			j--
			nums[cur], nums[j] = nums[j], nums[cur]
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
