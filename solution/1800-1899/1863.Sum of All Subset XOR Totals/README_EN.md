# [1863. Sum of All Subset XOR Totals](https://leetcode.com/problems/sum-of-all-subset-xor-totals)

[中文文档](/solution/1800-1899/1863.Sum%20of%20All%20Subset%20XOR%20Totals/README.md)

## Description

<p>The <strong>XOR total</strong> of an array is defined as the bitwise <code>XOR</code> of<strong> all its elements</strong>, or <code>0</code> if the array is<strong> empty</strong>.</p>

<ul>
	<li>For example, the <strong>XOR total</strong> of the array <code>[2,5,6]</code> is <code>2 XOR 5 XOR 6 = 1</code>.</li>
</ul>

<p>Given an array <code>nums</code>, return <em>the <strong>sum</strong> of all <strong>XOR totals</strong> for every <strong>subset</strong> of </em><code>nums</code>.&nbsp;</p>

<p><strong>Note:</strong> Subsets with the <strong>same</strong> elements should be counted <strong>multiple</strong> times.</p>

<p>An array <code>a</code> is a <strong>subset</strong> of an array <code>b</code> if <code>a</code> can be obtained from <code>b</code> by deleting some (possibly zero) elements of <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3]
<strong>Output:</strong> 6
<strong>Explanation: </strong>The 4 subsets of [1,3] are:
- The empty subset has an XOR total of 0.
- [1] has an XOR total of 1.
- [3] has an XOR total of 3.
- [1,3] has an XOR total of 1 XOR 3 = 2.
0 + 1 + 3 + 2 = 6
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,1,6]
<strong>Output:</strong> 28
<strong>Explanation: </strong>The 8 subsets of [5,1,6] are:
- The empty subset has an XOR total of 0.
- [5] has an XOR total of 5.
- [1] has an XOR total of 1.
- [6] has an XOR total of 6.
- [5,1] has an XOR total of 5 XOR 1 = 4.
- [5,6] has an XOR total of 5 XOR 6 = 3.
- [1,6] has an XOR total of 1 XOR 6 = 7.
- [5,1,6] has an XOR total of 5 XOR 1 XOR 6 = 2.
0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,6,7,8]
<strong>Output:</strong> 480
<strong>Explanation:</strong> The sum of all XOR totals for every subset is 480.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 12</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 20</code></li>
</ul>

## Solutions

**Solution 1: Binary Enumeration**

We can use binary enumeration to enumerate all subsets, and then calculate the XOR sum of each subset.

Specifically, we enumerate $i$ in the range $[0, 2^n)$, where $n$ is the length of the array $nums$. If the $j$th bit of the binary representation of $i$ is $1$, it means that the $j$th element of $nums$ is in the current subset; if the $j$th bit is $0$, it means that the $j$th element of $nums$ is not in the current subset. We can get the XOR sum of the current subset according to the binary representation of $i$, and add it to the answer.

The time complexity is $O(n \times 2^n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

**Solution 2: DFS (Depth-First Search)**

We can also use depth-first search to enumerate all subsets, and then calculate the XOR sum of each subset.

We design a function $dfs(i, s)$, where $i$ represents the current search to the $i$th element of the array $nums$, and $s$ represents the XOR sum of the current subset. Initially, $i=0$, $s=0$. During the search, we have two choices each time:

-   Add the $i$th element of $nums$ to the current subset, i.e., $dfs(i+1, s \oplus nums[i])$;
-   Do not add the $i$th element of $nums$ to the current subset, i.e., $dfs(i+1, s)$.

When we have searched all elements of the array $nums$, i.e., $i=n$, the XOR sum of the current subset is $s$, and we can add it to the answer.

The time complexity is $O(2^n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def subsetXORSum(self, nums: List[int]) -> int:
        ans, n = 0, len(nums)
        for i in range(1 << n):
            s = 0
            for j in range(n):
                if i >> j & 1:
                    s ^= nums[j]
            ans += s
        return ans
```

```python
class Solution:
    def subsetXORSum(self, nums: List[int]) -> int:
        def dfs(i: int, s: int):
            nonlocal ans
            if i >= len(nums):
                ans += s
                return
            dfs(i + 1, s)
            dfs(i + 1, s ^ nums[i])

        ans = 0
        dfs(0, 0)
        return ans
```

### **Java**

```java
class Solution {
    public int subsetXORSum(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < 1 << n; ++i) {
            int s = 0;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    s ^= nums[j];
                }
            }
            ans += s;
        }
        return ans;
    }
}
```

```java
class Solution {
    private int ans;
    private int[] nums;

    public int subsetXORSum(int[] nums) {
        this.nums = nums;
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int s) {
        if (i >= nums.length) {
            ans += s;
            return;
        }
        dfs(i + 1, s);
        dfs(i + 1, s ^ nums[i]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int subsetXORSum(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < 1 << n; ++i) {
            int s = 0;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    s ^= nums[j];
                }
            }
            ans += s;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int subsetXORSum(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        function<void(int, int)> dfs = [&](int i, int s) {
            if (i >= n) {
                ans += s;
                return;
            }
            dfs(i + 1, s);
            dfs(i + 1, s ^ nums[i]);
        };
        dfs(0, 0);
        return ans;
    }
};
```

### **Go**

```go
func subsetXORSum(nums []int) (ans int) {
	n := len(nums)
	for i := 0; i < 1<<n; i++ {
		s := 0
		for j, x := range nums {
			if i>>j&1 == 1 {
				s ^= x
			}
		}
		ans += s
	}
	return
}
```

```go
func subsetXORSum(nums []int) (ans int) {
	n := len(nums)
	var dfs func(int, int)
	dfs = func(i, s int) {
		if i >= n {
			ans += s
			return
		}
		dfs(i+1, s)
		dfs(i+1, s^nums[i])
	}
	dfs(0, 0)
	return
}
```

### **TypeScript**

```ts
function subsetXORSum(nums: number[]): number {
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i < 1 << n; ++i) {
        let s = 0;
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                s ^= nums[j];
            }
        }
        ans += s;
    }
    return ans;
}
```

```ts
function subsetXORSum(nums: number[]): number {
    let ans = 0;
    const n = nums.length;
    const dfs = (i: number, s: number) => {
        if (i >= n) {
            ans += s;
            return;
        }
        dfs(i + 1, s);
        dfs(i + 1, s ^ nums[i]);
    };
    dfs(0, 0);
    return ans;
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var subsetXORSum = function (nums) {
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i < 1 << n; ++i) {
        let s = 0;
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                s ^= nums[j];
            }
        }
        ans += s;
    }
    return ans;
};
```

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var subsetXORSum = function (nums) {
    let ans = 0;
    const n = nums.length;
    const dfs = (i, s) => {
        if (i >= n) {
            ans += s;
            return;
        }
        dfs(i + 1, s);
        dfs(i + 1, s ^ nums[i]);
    };
    dfs(0, 0);
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
