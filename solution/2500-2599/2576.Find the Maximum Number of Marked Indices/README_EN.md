# [2576. Find the Maximum Number of Marked Indices](https://leetcode.com/problems/find-the-maximum-number-of-marked-indices)

[中文文档](/solution/2500-2599/2576.Find%20the%20Maximum%20Number%20of%20Marked%20Indices/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>.</p>

<p>Initially, all of the indices are unmarked. You are allowed to make this operation any number of times:</p>

<ul>
	<li>Pick two <strong>different unmarked</strong> indices <code>i</code> and <code>j</code> such that <code>2 * nums[i] &lt;= nums[j]</code>, then mark <code>i</code> and <code>j</code>.</li>
</ul>

<p>Return <em>the maximum possible number of marked indices in <code>nums</code> using the above operation any number of times</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,5,2,4]
<strong>Output:</strong> 2
<strong>Explanation: </strong>In the first operation: pick i = 2 and j = 1, the operation is allowed because 2 * nums[2] &lt;= nums[1]. Then mark index 2 and 1.
It can be shown that there&#39;s no other valid operation so the answer is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,2,5,4]
<strong>Output:</strong> 4
<strong>Explanation: </strong>In the first operation: pick i = 3 and j = 0, the operation is allowed because 2 * nums[3] &lt;= nums[0]. Then mark index 3 and 0.
In the second operation: pick i = 1 and j = 2, the operation is allowed because 2 * nums[1] &lt;= nums[2]. Then mark index 1 and 2.
Since there is no other operation, the answer is 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,6,8]
<strong>Output:</strong> 0
<strong>Explanation: </strong>There is no valid operation to do, so the answer is 0.

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<style type="text/css">.spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px 0px; font-size:150%; font-weight: bold; color:#000000; background-color:cyan; outline:0; 
}
.spoiler {overflow:hidden;}
.spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s ease;-o-transition: all 0s ease;transition: margin 0s ease;}
.spoilerbutton[value="Show Message"] + .spoiler > div {margin-top:-500%;}
.spoilerbutton[value="Hide Message"] + .spoiler {padding:5px;}
</style>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxNumOfMarkedIndices(self, nums: List[int]) -> int:
        nums.sort()
        n = len(nums)
        i, j = 0, (n + 1) // 2
        ans = 0
        while j < n:
            while j < n and nums[i] * 2 > nums[j]:
                j += 1
            if j < n:
                ans += 2
            i, j = i + 1, j + 1
        return ans
```

### **Java**

```java
class Solution {
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0, j = (n + 1) / 2; j < n; ++i, ++j) {
            while (j < n && nums[i] * 2 > nums[j]) {
                ++j;
            }
            if (j < n) {
                ans += 2;
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
    int maxNumOfMarkedIndices(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int ans = 0;
        for (int i = 0, j = (n + 1) / 2; j < n; ++i, ++j) {
            while (j < n && nums[i] * 2 > nums[j]) {
                ++j;
            }
            if (j < n) {
                ans += 2;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxNumOfMarkedIndices(nums []int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	for i, j := 0, (n+1)/2; j < n; i, j = i+1, j+1 {
		for j < n && nums[i]*2 > nums[j] {
			j++
		}
		if j < n {
			ans += 2
		}
	}
	return
}
```

### **TypeScript**

```ts
function maxNumOfMarkedIndices(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;
    for (let i = 0, j = Math.floor((n + 1) / 2); j < n; ++i, ++j) {
        while (j < n && nums[i] * 2 > nums[j]) {
            ++j;
        }
        if (j < n) {
            ans += 2;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
