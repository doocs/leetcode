# [2293. Min Max Game](https://leetcode.com/problems/min-max-game)

[中文文档](/solution/2200-2299/2293.Min%20Max%20Game/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> whose length is a power of <code>2</code>.</p>

<p>Apply the following algorithm on <code>nums</code>:</p>

<ol>
	<li>Let <code>n</code> be the length of <code>nums</code>. If <code>n == 1</code>, <strong>end</strong> the process. Otherwise, <strong>create</strong> a new <strong>0-indexed</strong> integer array <code>newNums</code> of length <code>n / 2</code>.</li>
	<li>For every <strong>even</strong> index <code>i</code> where <code>0 &lt;= i &lt; n / 2</code>, <strong>assign</strong> the value of <code>newNums[i]</code> as <code>min(nums[2 * i], nums[2 * i + 1])</code>.</li>
	<li>For every <strong>odd</strong> index <code>i</code> where <code>0 &lt;= i &lt; n / 2</code>, <strong>assign</strong> the value of <code>newNums[i]</code> as <code>max(nums[2 * i], nums[2 * i + 1])</code>.</li>
	<li><strong>Replace</strong> the array <code>nums</code> with <code>newNums</code>.</li>
	<li><strong>Repeat</strong> the entire process starting from step 1.</li>
</ol>

<p>Return <em>the last number that remains in </em><code>nums</code><em> after applying the algorithm.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2293.Min%20Max%20Game/images/example1drawio-1.png" style="width: 500px; height: 240px;" />
<pre>
<strong>Input:</strong> nums = [1,3,5,2,4,8,2,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The following arrays are the results of applying the algorithm repeatedly.
First: nums = [1,5,4,2]
Second: nums = [1,4]
Third: nums = [1]
1 is the last remaining number, so we return 1.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 3 is already the last remaining number, so we return 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1024</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums.length</code> is a power of <code>2</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minMaxGame(self, nums: List[int]) -> int:
        n = len(nums)
        if n == 1:
            return nums[0]
        t = []
        for i in range(n >> 1):
            v = (
                max(nums[i << 1], nums[i << 1 | 1])
                if i & 1
                else min(nums[i << 1], nums[i << 1 | 1])
            )
            t.append(v)
        return self.minMaxGame(t)
```

### **Java**

```java
class Solution {
    public int minMaxGame(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] t = new int[n >> 1];
        for (int i = 0; i < t.length; ++i) {
            int a = nums[i << 1], b = nums[i << 1 | 1];
            t[i] = (i & 1) == 1 ? Math.max(a, b) : Math.min(a, b);
        }
        return minMaxGame(t);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minMaxGame(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return nums[0];
        vector<int> t(n >> 1);
        for (int i = 0; i < t.size(); ++i) {
            int a = nums[i << 1], b = nums[i << 1 | 1];
            t[i] = (i & 1) == 1 ? max(a, b) : min(a, b);
        }
        return minMaxGame(t);
    }
};
```

### **Go**

```go
func minMaxGame(nums []int) int {
	n := len(nums)
	if n == 1 {
		return nums[0]
	}
	var t []int
	for i := 0; i < n>>1; i++ {
		a, b := nums[i<<1], nums[i<<1|1]
		if (i & 1) == 1 {
			t = append(t, max(a, b))
		} else {
			t = append(t, min(a, b))
		}
	}
	return minMaxGame(t)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function minMaxGame(nums: number[]): number {
    while (nums.length > 1) {
        let n = nums.length;
        let tmp = [];
        for (let i = 0; i < n; i += 2) {
            if (i % 4 == 2) {
                tmp.push(Math.max(nums[i], nums[i + 1]));
            } else {
                tmp.push(Math.min(nums[i], nums[i + 1]));
            }
        }
        nums = tmp;
    }
    return nums[0];
}
```

### **...**

```

```

<!-- tabs:end -->
