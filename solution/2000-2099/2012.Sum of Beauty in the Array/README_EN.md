# [2012. Sum of Beauty in the Array](https://leetcode.com/problems/sum-of-beauty-in-the-array)

[中文文档](/solution/2000-2099/2012.Sum%20of%20Beauty%20in%20the%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. For each index <code>i</code> (<code>1 &lt;= i &lt;= nums.length - 2</code>) the <strong>beauty</strong> of <code>nums[i]</code> equals:</p>

<ul>
	<li><code>2</code>, if <code>nums[j] &lt; nums[i] &lt; nums[k]</code>, for <strong>all</strong> <code>0 &lt;= j &lt; i</code> and for <strong>all</strong> <code>i &lt; k &lt;= nums.length - 1</code>.</li>
	<li><code>1</code>, if <code>nums[i - 1] &lt; nums[i] &lt; nums[i + 1]</code>, and the previous condition is not satisfied.</li>
	<li><code>0</code>, if none of the previous conditions holds.</li>
</ul>

<p>Return<em> the <strong>sum of beauty</strong> of all </em><code>nums[i]</code><em> where </em><code>1 &lt;= i &lt;= nums.length - 2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> For each index i in the range 1 &lt;= i &lt;= 1:
- The beauty of nums[1] equals 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,6,4]
<strong>Output:</strong> 1
<strong>Explanation:</strong> For each index i in the range 1 &lt;= i &lt;= 2:
- The beauty of nums[1] equals 1.
- The beauty of nums[2] equals 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> For each index i in the range 1 &lt;= i &lt;= 1:
- The beauty of nums[1] equals 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sumOfBeauties(self, nums: List[int]) -> int:
        n = len(nums)
        lmx = [0] * n
        for i in range(1, n):
            lmx[i] = max(lmx[i - 1], nums[i - 1])
        rmi = [100001] * n
        for i in range(n - 2, -1, -1):
            rmi[i] = min(rmi[i + 1], nums[i + 1])
        ans = 0
        for i in range(1, n - 1):
            if lmx[i] < nums[i] < rmi[i]:
                ans += 2
            elif nums[i - 1] < nums[i] < nums[i + 1]:
                ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int sumOfBeauties(int[] nums) {
        int n = nums.length;
        int[] lmx = new int[n];
        int[] rmi = new int[n];
        rmi[n - 1] = 100001;
        for (int i = 1; i < n; ++i) {
            lmx[i] = Math.max(lmx[i - 1], nums[i - 1]);
        }
        for (int i = n - 2; i >= 0; --i) {
            rmi[i] = Math.min(rmi[i + 1], nums[i + 1]);
        }
        int ans = 0;
        for (int i = 1; i < n - 1; ++i) {
            if (lmx[i] < nums[i] && nums[i] < rmi[i]) {
                ans += 2;
            } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
                ans += 1;
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function sumOfBeauties(nums: number[]): number {
    let n = nums.length;
    let prefix = new Array(n),
        postfix = new Array(n);
    prefix[0] = nums[0];
    postfix[n - 1] = nums[n - 1];
    for (let i = 1, j = n - 2; i < n; ++i, --j) {
        prefix[i] = Math.max(nums[i], prefix[i - 1]);
        postfix[j] = Math.min(nums[j], postfix[j + 1]);
    }
    let ans = 0;
    for (let i = 1; i < n - 1; ++i) {
        if (prefix[i - 1] < nums[i] && nums[i] < postfix[i + 1]) {
            ans += 2;
        } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
            ans += 1;
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int sumOfBeauties(vector<int>& nums) {
        int n = nums.size();
        vector<int> lmx(n);
        vector<int> rmi(n, 100001);
        for (int i = 1; i < n; ++i) lmx[i] = max(lmx[i - 1], nums[i - 1]);
        for (int i = n - 2; i >= 0; --i) rmi[i] = min(rmi[i + 1], nums[i + 1]);
        int ans = 0;
        for (int i = 1; i < n - 1; ++i) {
            if (lmx[i] < nums[i] && nums[i] < rmi[i])
                ans += 2;
            else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1])
                ans += 1;
        }
        return ans;
    }
};
```

### **Go**

```go
func sumOfBeauties(nums []int) int {
	n := len(nums)
	lmx := make([]int, n)
	rmi := make([]int, n)
	rmi[n-1] = 100001
	for i := 1; i < n; i++ {
		lmx[i] = max(lmx[i-1], nums[i-1])
	}
	for i := n - 2; i >= 0; i-- {
		rmi[i] = min(rmi[i+1], nums[i+1])
	}
	ans := 0
	for i := 1; i < n-1; i++ {
		if lmx[i] < nums[i] && nums[i] < rmi[i] {
			ans += 2
		} else if nums[i-1] < nums[i] && nums[i] < nums[i+1] {
			ans += 1
		}
	}
	return ans
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

### **...**

```

```

<!-- tabs:end -->
