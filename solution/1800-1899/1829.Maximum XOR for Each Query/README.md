# [1829. 每个查询的最大异或值](https://leetcode.cn/problems/maximum-xor-for-each-query)

[English Version](/solution/1800-1899/1829.Maximum%20XOR%20for%20Each%20Query/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>有序</strong> 数组 <code>nums</code> ，它由 <code>n</code> 个非负整数组成，同时给你一个整数 <code>maximumBit</code> 。你需要执行以下查询 <code>n</code> 次：</p>

<ol>
	<li>找到一个非负整数 <code>k < 2<sup>maximumBit</sup></code> ，使得 <code>nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1] XOR k</code> 的结果 <strong>最大化</strong> 。<code>k</code> 是第 <code>i</code> 个查询的答案。</li>
	<li>从当前数组 <code>nums</code> 删除 <strong>最后</strong> 一个元素。</li>
</ol>

<p>请你返回一个数组 <code>answer</code> ，其中<em> </em><code>answer[i]</code>是第 <code>i</code> 个查询的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [0,1,1,3], maximumBit = 2
<b>输出：</b>[0,3,2,3]
<b>解释：</b>查询的答案如下：
第一个查询：nums = [0,1,1,3]，k = 0，因为 0 XOR 1 XOR 1 XOR 3 XOR 0 = 3 。
第二个查询：nums = [0,1,1]，k = 3，因为 0 XOR 1 XOR 1 XOR 3 = 3 。
第三个查询：nums = [0,1]，k = 2，因为 0 XOR 1 XOR 2 = 3 。
第四个查询：nums = [0]，k = 3，因为 0 XOR 3 = 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,3,4,7], maximumBit = 3
<b>输出：</b>[5,2,6,5]
<b>解释：</b>查询的答案如下：
第一个查询：nums = [2,3,4,7]，k = 5，因为 2 XOR 3 XOR 4 XOR 7 XOR 5 = 7。
第二个查询：nums = [2,3,4]，k = 2，因为 2 XOR 3 XOR 4 XOR 2 = 7 。
第三个查询：nums = [2,3]，k = 6，因为 2 XOR 3 XOR 6 = 7 。
第四个查询：nums = [2]，k = 5，因为 2 XOR 5 = 7 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [0,1,2,2,5,7], maximumBit = 3
<b>输出：</b>[4,3,6,4,6,7]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= maximumBit <= 20</code></li>
	<li><code>0 <= nums[i] < 2<sup>maximumBit</sup></code></li>
	<li><code>nums</code>​​​ 中的数字已经按 <strong>升序</strong> 排好序。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算 + 枚举**

我们先预处理出数组 `nums` 的异或和 $xs$，即 $xs=nums[0] \oplus nums[1] \oplus \cdots \oplus nums[n-1]$。

接下来，我们从后往前枚举数组 `nums` 中的每个元素 $x$，当前的异或和为 $xs$，我们需要找到一个数 $k$，使得 $xs \oplus k$ 的值尽可能大，并且 $k \lt 2^{maximumBit}$。

也即是说，我们从 $xs$ 的第 $maximumBit - 1$ 位开始，往低位枚举，如果 $xs$ 的某一位为 $0$，那么我们就将 $k$ 的对应位设置为 $1$，否则我们就将 $k$ 的对应位设置为 $0$。这样，最终得到的 $k$ 就是每一次查询的答案。然后，我们将 $xs$ 更新为 $xs \oplus x$，继续枚举下一个元素。

时间复杂度 $O(n \times m)$，其中 $n$ 和 $m$ 分别是数组 `nums` 和 `maximumBit` 的值。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

**方法二：枚举优化**

与方法一类似，我们先预处理出数组 `nums` 的异或和 $xs$，即 $xs=nums[0] \oplus nums[1] \oplus \cdots \oplus nums[n-1]$。

接下来，我们算出 $2^{maximumBit} - 1$，即 $2^{maximumBit}$ 减去 $1$，记为 $mask$。然后，我们从后往前枚举数组 `nums` 中的每个元素 $x$，当前的异或和为 $xs$，那么 $k=xs \oplus mask$ 就是每一次查询的答案。然后，我们将 $xs$ 更新为 $xs \oplus x$，继续枚举下一个元素。

时间复杂度 $O(n)$，其中 $n$ 是数组 `nums` 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
