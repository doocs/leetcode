# [2091. Removing Minimum and Maximum From Array](https://leetcode.com/problems/removing-minimum-and-maximum-from-array)

[中文文档](/solution/2000-2099/2091.Removing%20Minimum%20and%20Maximum%20From%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of <strong>distinct</strong> integers <code>nums</code>.</p>

<p>There is an element in <code>nums</code> that has the <strong>lowest</strong> value and an element that has the <strong>highest</strong> value. We call them the <strong>minimum</strong> and <strong>maximum</strong> respectively. Your goal is to remove <strong>both</strong> these elements from the array.</p>

<p>A <strong>deletion</strong> is defined as either removing an element from the <strong>front</strong> of the array or removing an element from the <strong>back</strong> of the array.</p>

<p>Return <em>the <strong>minimum</strong> number of deletions it would take to remove <strong>both</strong> the minimum and maximum element from the array.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,<u><strong>10</strong></u>,7,5,4,<u><strong>1</strong></u>,8,6]
<strong>Output:</strong> 5
<strong>Explanation:</strong> 
The minimum element in the array is nums[5], which is 1.
The maximum element in the array is nums[1], which is 10.
We can remove both the minimum and maximum by removing 2 elements from the front and 3 elements from the back.
This results in 2 + 3 = 5 deletions, which is the minimum number possible.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,<u><strong>-4</strong></u>,<u><strong>19</strong></u>,1,8,-2,-3,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
The minimum element in the array is nums[1], which is -4.
The maximum element in the array is nums[2], which is 19.
We can remove both the minimum and maximum by removing 3 elements from the front.
This results in only 3 deletions, which is the minimum number possible.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [<u><strong>101</strong></u>]
<strong>Output:</strong> 1
<strong>Explanation:</strong>  
There is only one element in the array, which makes it both the minimum and maximum element.
We can remove it with 1 deletion.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li>The integers in <code>nums</code> are <strong>distinct</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumDeletions(self, nums: List[int]) -> int:
        mi = mx = 0
        for i, num in enumerate(nums):
            if num < nums[mi]:
                mi = i
            if num > nums[mx]:
                mx = i
        if mi > mx:
            mi, mx = mx, mi
        return min(mx + 1, len(nums) - mi, mi + 1 + len(nums) - mx)
```

### **Java**

```java
class Solution {
    public int minimumDeletions(int[] nums) {
        int mi = 0, mx = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < nums[mi]) {
                mi = i;
            }
            if (nums[i] > nums[mx]) {
                mx = i;
            }
        }
        if (mi > mx) {
            int t = mx;
            mx = mi;
            mi = t;
        }
        return Math.min(Math.min(mx + 1, n - mi), mi + 1 + n - mx);
    }
}
```

### **TypeScript**

```ts
function minimumDeletions(nums: number[]): number {
    const n = nums.length;
    if (n == 1) return 1;
    let i = nums.indexOf(Math.min(...nums));
    let j = nums.indexOf(Math.max(...nums));
    let left = Math.min(i, j);
    let right = Math.max(i, j);
    return Math.min(left + 1 + n - right, right + 1, n - left);
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumDeletions(vector<int>& nums) {
        int mi = 0, mx = 0, n = nums.size();
        for (int i = 0; i < n; ++i) {
            if (nums[i] < nums[mi]) mi = i;
            if (nums[i] > nums[mx]) mx = i;
        }
        if (mi > mx) {
            int t = mi;
            mi = mx;
            mx = t;
        }
        return min(min(mx + 1, n - mi), mi + 1 + n - mx);
    }
};
```

### **Go**

```go
func minimumDeletions(nums []int) int {
	mi, mx, n := 0, 0, len(nums)
	for i, num := range nums {
		if num < nums[mi] {
			mi = i
		}
		if num > nums[mx] {
			mx = i
		}
	}
	if mi > mx {
		mi, mx = mx, mi
	}
	return min(min(mx+1, n-mi), mi+1+n-mx)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
