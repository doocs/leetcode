# [1313. Decompress Run-Length Encoded List](https://leetcode.com/problems/decompress-run-length-encoded-list)

[中文文档](/solution/1300-1399/1313.Decompress%20Run-Length%20Encoded%20List/README.md)

## Description

<p>We are given a list <code>nums</code> of integers representing a list compressed with run-length encoding.</p>

<p>Consider each adjacent pair&nbsp;of elements <code>[freq, val] = [nums[2*i], nums[2*i+1]]</code>&nbsp;(with <code>i &gt;= 0</code>).&nbsp; For each such pair, there are <code>freq</code> elements with value <code>val</code> concatenated in a sublist. Concatenate all the sublists from left to right to generate the decompressed list.</p>

<p>Return the decompressed list.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> [2,4,4,4]
<strong>Explanation:</strong> The first pair [1,2] means we have freq = 1 and val = 2 so we generate the array [2].
The second pair [3,4] means we have freq = 3 and val = 4 so we generate [4,4,4].
At the end the concatenation [2] + [4,4,4] is [2,4,4,4].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2,3]
<strong>Output:</strong> [1,3,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums.length % 2 == 0</code></li>
	<li><code><font face="monospace">1 &lt;= nums[i] &lt;= 100</font></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def decompressRLElist(self, nums: List[int]) -> List[int]:
        res = []
        for i in range(1, len(nums), 2):
            res.extend([nums[i]] * nums[i - 1])
        return res
```

### **Java**

```java
class Solution {
    public int[] decompressRLElist(int[] nums) {
        int n = 0;
        for (int i = 0; i < nums.length; i += 2) {
            n += nums[i];
        }
        int[] res = new int[n];
        for (int i = 1, k = 0; i < nums.length; i += 2) {
            for (int j = 0; j < nums[i - 1]; ++j) {
                res[k++] = nums[i];
            }
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function decompressRLElist(nums: number[]): number[] {
    let n = nums.length >> 1;
    let ans = [];
    for (let i = 0; i < n; i++) {
        let freq = nums[2 * i],
            val = nums[2 * i + 1];
        ans.push(...new Array(freq).fill(val));
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> decompressRLElist(vector<int>& nums) {
        vector<int> res;
        for (int i = 1; i < nums.size(); i += 2) {
            for (int j = 0; j < nums[i - 1]; ++j) {
                res.push_back(nums[i]);
            }
        }
        return res;
    }
};
```

### **Go**

```go
func decompressRLElist(nums []int) []int {
	var res []int
	for i := 1; i < len(nums); i += 2 {
		for j := 0; j < nums[i-1]; j++ {
			res = append(res, nums[i])
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
