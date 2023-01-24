# [1829. Maximum XOR for Each Query](https://leetcode.com/problems/maximum-xor-for-each-query)

[中文文档](/solution/1800-1899/1829.Maximum%20XOR%20for%20Each%20Query/README.md)

## Description

<p>You are given a <strong>sorted</strong> array <code>nums</code> of <code>n</code> non-negative integers and an integer <code>maximumBit</code>. You want to perform the following query <code>n</code> <strong>times</strong>:</p>

<ol>
	<li>Find a non-negative integer <code>k &lt; 2<sup>maximumBit</sup></code> such that <code>nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1] XOR k</code> is <strong>maximized</strong>. <code>k</code> is the answer to the <code>i<sup>th</sup></code> query.</li>
	<li>Remove the <strong>last </strong>element from the current array <code>nums</code>.</li>
</ol>

<p>Return <em>an array</em> <code>answer</code><em>, where </em><code>answer[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,1,3], maximumBit = 2
<strong>Output:</strong> [0,3,2,3]
<strong>Explanation</strong>: The queries are answered as follows:
1<sup>st</sup> query: nums = [0,1,1,3], k = 0 since 0 XOR 1 XOR 1 XOR 3 XOR 0 = 3.
2<sup>nd</sup> query: nums = [0,1,1], k = 3 since 0 XOR 1 XOR 1 XOR 3 = 3.
3<sup>rd</sup> query: nums = [0,1], k = 2 since 0 XOR 1 XOR 2 = 3.
4<sup>th</sup> query: nums = [0], k = 3 since 0 XOR 3 = 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,4,7], maximumBit = 3
<strong>Output:</strong> [5,2,6,5]
<strong>Explanation</strong>: The queries are answered as follows:
1<sup>st</sup> query: nums = [2,3,4,7], k = 5 since 2 XOR 3 XOR 4 XOR 7 XOR 5 = 7.
2<sup>nd</sup> query: nums = [2,3,4], k = 2 since 2 XOR 3 XOR 4 XOR 2 = 7.
3<sup>rd</sup> query: nums = [2,3], k = 6 since 2 XOR 3 XOR 6 = 7.
4<sup>th</sup> query: nums = [2], k = 5 since 2 XOR 5 = 7.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2,2,5,7], maximumBit = 3
<strong>Output:</strong> [4,3,6,4,6,7]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= maximumBit &lt;= 20</code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>maximumBit</sup></code></li>
	<li><code>nums</code>​​​ is sorted in <strong>ascending</strong> order.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getMaximumXor(self, nums: List[int], maximumBit: int) -> List[int]:
        ans = []
        xs = reduce(xor, nums)
        for x in nums[::-1]:
            k = 0
            for i in range(maximumBit - 1, -1, -1):
                if (xs >> i & 1) == 0:
                    k |= 1 << i
            ans.append(k)
            xs ^= x
        return ans
```

```python
class Solution:
    def getMaximumXor(self, nums: List[int], maximumBit: int) -> List[int]:
        ans = []
        xs = reduce(xor, nums)
        mask = (1 << maximumBit) - 1
        for x in nums[::-1]:
            k = xs ^ mask
            ans.append(k)
            xs ^= x
        return ans
```

### **Java**

```java
class Solution {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int n = nums.length;
        int xs = 0;
        for (int x : nums) {
            xs ^= x;
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int x = nums[n - i - 1];
            int k = 0;
            for (int j = maximumBit - 1; j >= 0; --j) {
                if (((xs >> j) & 1) == 0) {
                    k |= 1 << j;
                }
            }
            ans[i] = k;
            xs ^= x;
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int xs = 0;
        for (int x : nums) {
            xs ^= x;
        }
        int mask = (1 << maximumBit) - 1;
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int x = nums[n - i - 1];
            int k = xs ^ mask;
            ans[i] = k;
            xs ^= x;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> getMaximumXor(vector<int>& nums, int maximumBit) {
        int xs = 0;
        for (int& x : nums) {
            xs ^= x;
        }
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            int x = nums[n - i - 1];
            int k = 0;
            for (int j = maximumBit - 1; ~j; --j) {
                if ((xs >> j & 1) == 0) {
                    k |= 1 << j;
                }
            }
            ans[i] = k;
            xs ^= x;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> getMaximumXor(vector<int>& nums, int maximumBit) {
        int xs = 0;
        for (int& x : nums) {
            xs ^= x;
        }
        int mask = (1 << maximumBit) - 1;
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            int x = nums[n - i - 1];
            int k = xs ^ mask;
            ans[i] = k;
            xs ^= x;
        }
        return ans;
    }
};
```

### **Go**

```go
func getMaximumXor(nums []int, maximumBit int) (ans []int) {
	xs := 0
	for _, x := range nums {
		xs ^= x
	}
	for i := range nums {
		x := nums[len(nums)-i-1]
		k := 0
		for j := maximumBit - 1; j >= 0; j-- {
			if xs>>j&1 == 0 {
				k |= 1 << j
			}
		}
		ans = append(ans, k)
		xs ^= x
	}
	return
}
```

```go
func getMaximumXor(nums []int, maximumBit int) (ans []int) {
	xs := 0
	for _, x := range nums {
		xs ^= x
	}
	mask := (1 << maximumBit) - 1
	for i := range nums {
		x := nums[len(nums)-i-1]
		k := xs ^ mask
		ans = append(ans, k)
		xs ^= x
	}
	return
}
```

### **TypeScript**

```ts
function getMaximumXor(nums: number[], maximumBit: number): number[] {
    let xs = 0;
    for (const x of nums) {
        xs ^= x;
    }
    const n = nums.length;
    const ans = new Array(n);
    for (let i = 0; i < n; ++i) {
        const x = nums[n - i - 1];
        let k = 0;
        for (let j = maximumBit - 1; j >= 0; --j) {
            if (((xs >> j) & 1) == 0) {
                k |= 1 << j;
            }
        }
        ans[i] = k;
        xs ^= x;
    }
    return ans;
}
```

```ts
function getMaximumXor(nums: number[], maximumBit: number): number[] {
    let xs = 0;
    for (const x of nums) {
        xs ^= x;
    }
    const mask = (1 << maximumBit) - 1;
    const n = nums.length;
    const ans = new Array(n);
    for (let i = 0; i < n; ++i) {
        const x = nums[n - i - 1];
        let k = xs ^ mask;
        ans[i] = k;
        xs ^= x;
    }
    return ans;
}
```

### **C#**

```cs
public class Solution {
    public int[] GetMaximumXor(int[] nums, int maximumBit) {
        int xs = 0;
        foreach (int x in nums) {
            xs ^= x;
        }
        int n = nums.Length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int x = nums[n - i - 1];
            int k = 0;
            for (int j = maximumBit - 1; j >= 0; --j) {
                if ((xs >> j & 1) == 0) {
                    k |= 1 << j;
                }
            }
            ans[i] = k;
            xs ^= x;
        }
        return ans;
    }
}
```

```cs
public class Solution {
    public int[] GetMaximumXor(int[] nums, int maximumBit) {
        int xs = 0;
        foreach (int x in nums) {
            xs ^= x;
        }
        int mask = (1 << maximumBit) - 1;
        int n = nums.Length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int x = nums[n - i - 1];
            int k = xs ^ mask;
            ans[i] = k;
            xs ^= x;
        }
        return ans;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} maximumBit
 * @return {number[]}
 */
var getMaximumXor = function (nums, maximumBit) {
    let xs = 0;
    for (const x of nums) {
        xs ^= x;
    }
    const n = nums.length;
    const ans = new Array(n);
    for (let i = 0; i < n; ++i) {
        const x = nums[n - i - 1];
        let k = 0;
        for (let j = maximumBit - 1; j >= 0; --j) {
            if (((xs >> j) & 1) == 0) {
                k |= 1 << j;
            }
        }
        ans[i] = k;
        xs ^= x;
    }
    return ans;
};
```

```js
/**
 * @param {number[]} nums
 * @param {number} maximumBit
 * @return {number[]}
 */
var getMaximumXor = function (nums, maximumBit) {
    let xs = 0;
    for (const x of nums) {
        xs ^= x;
    }
    const mask = (1 << maximumBit) - 1;
    const n = nums.length;
    const ans = new Array(n);
    for (let i = 0; i < n; ++i) {
        const x = nums[n - i - 1];
        let k = xs ^ mask;
        ans[i] = k;
        xs ^= x;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
