---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1863.Sum%20of%20All%20Subset%20XOR%20Totals/README.md
rating: 1372
source: 第 241 场周赛 Q1
tags:
    - 位运算
    - 数组
    - 数学
    - 回溯
    - 组合数学
    - 枚举
---

# [1863. 找出所有子集的异或总和再求和](https://leetcode.cn/problems/sum-of-all-subset-xor-totals)

[English Version](/solution/1800-1899/1863.Sum%20of%20All%20Subset%20XOR%20Totals/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个数组的<strong> 异或总和</strong> 定义为数组中所有元素按位 <code>XOR</code> 的结果；如果数组为 <strong>空</strong> ，则异或总和为 <code>0</code> 。</p>

<ul>
	<li>例如，数组 <code>[2,5,6]</code> 的 <strong>异或总和</strong> 为 <code>2 XOR 5 XOR 6 = 1</code> 。</li>
</ul>

<p>给你一个数组 <code>nums</code> ，请你求出 <code>nums</code> 中每个 <strong>子集</strong> 的 <strong>异或总和</strong> ，计算并返回这些值相加之 <strong>和</strong> 。</p>

<p><strong>注意：</strong>在本题中，元素 <strong>相同</strong> 的不同子集应 <strong>多次</strong> 计数。</p>

<p>数组 <code>a</code> 是数组 <code>b</code> 的一个 <strong>子集</strong> 的前提条件是：从 <code>b</code> 删除几个（也可能不删除）元素能够得到 <code>a</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,3]
<strong>输出：</strong>6
<strong>解释：</strong>[1,3] 共有 4 个子集：
- 空子集的异或总和是 0 。
- [1] 的异或总和为 1 。
- [3] 的异或总和为 3 。
- [1,3] 的异或总和为 1 XOR 3 = 2 。
0 + 1 + 3 + 2 = 6
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [5,1,6]
<strong>输出：</strong>28
<strong>解释：</strong>[5,1,6] 共有 8 个子集：
- 空子集的异或总和是 0 。
- [5] 的异或总和为 5 。
- [1] 的异或总和为 1 。
- [6] 的异或总和为 6 。
- [5,1] 的异或总和为 5 XOR 1 = 4 。
- [5,6] 的异或总和为 5 XOR 6 = 3 。
- [1,6] 的异或总和为 1 XOR 6 = 7 。
- [5,1,6] 的异或总和为 5 XOR 1 XOR 6 = 2 。
0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [3,4,5,6,7,8]
<strong>输出：</strong>480
<strong>解释：</strong>每个子集的全部异或总和值之和为 480 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 12</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 20</code></li>
</ul>

## 解法

### 方法一：二进制枚举

我们可以用二进制枚举的方法，枚举出所有的子集，然后计算每个子集的异或总和。

具体地，我们在 $[0, 2^n)$ 的范围内枚举 $i$，其中 $n$ 是数组 $nums$ 的长度。如果 $i$ 的二进制表示的第 $j$ 位为 $1$，那么代表着 $nums$ 的第 $j$ 个元素在当前枚举的子集中；如果第 $j$ 位为 $0$，那么代表着 $nums$ 的第 $j$ 个元素不在当前枚举的子集中。我们可以根据 $i$ 的二进制表示，得到当前子集对应的异或总和，将其加到答案中即可。

时间复杂度 $O(n \times 2^n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

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

<!-- tabs:end -->

### 方法二：DFS

我们也可以使用深度优先搜索的方法，枚举出所有的子集，然后计算每个子集的异或总和。

我们设计一个函数 $dfs(i, s)$，其中 $i$ 表示当前搜索到数组 $nums$ 的第 $i$ 个元素，$s$ 表示当前子集的异或总和。初始时，$i=0$, $s=0$。在搜索的过程中，每次我们都有两种选择：

-   将 $nums$ 的第 $i$ 个元素加入当前子集，即 $dfs(i+1, s \oplus nums[i])$；
-   将 $nums$ 的第 $i$ 个元素不加入当前子集，即 $dfs(i+1, s)$。

当我们搜索完数组 $nums$ 的所有元素时，即 $i=n$ 时，当前子集的异或总和为 $s$，将其加到答案中即可。

时间复杂度 $O(2^n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
