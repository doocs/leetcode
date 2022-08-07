# [2367. 算术三元组的数目](https://leetcode.cn/problems/number-of-arithmetic-triplets)

[English Version](/solution/2300-2399/2367.Number%20of%20Arithmetic%20Triplets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、<strong>严格递增</strong> 的整数数组 <code>nums</code> 和一个正整数 <code>diff</code> 。如果满足下述全部条件，则三元组 <code>(i, j, k)</code> 就是一个 <strong>算术三元组</strong> ：</p>

<ul>
	<li><code>i &lt; j &lt; k</code> ，</li>
	<li><code>nums[j] - nums[i] == diff</code> 且</li>
	<li><code>nums[k] - nums[j] == diff</code></li>
</ul>

<p>返回不同 <strong>算术三元组</strong> 的数目<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [0,1,4,6,7,10], diff = 3
<strong>输出：</strong>2
<strong>解释：</strong>
(1, 2, 4) 是算术三元组：7 - 4 == 3 且 4 - 1 == 3 。
(2, 4, 5) 是算术三元组：10 - 7 == 3 且 7 - 4 == 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [4,5,6,7,8,9], diff = 2
<strong>输出：</strong>2
<strong>解释：</strong>
(0, 2, 4) 是算术三元组：8 - 6 == 2 且 6 - 4 == 2 。
(1, 3, 5) 是算术三元组：9 - 7 == 2 且 7 - 5 == 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 200</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 200</code></li>
	<li><code>1 &lt;= diff &lt;= 50</code></li>
	<li><code>nums</code> <strong>严格</strong> 递增</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力枚举**

直接暴力枚举 $i$, $j$, $k$，统计合法的三元组数目。

时间复杂度 $O(n^3)$，空间复杂度 $O(1)$。

**方法二：哈希表**

由于 $nums$ 严格递增，那么对于 $nums$ 中的每个元素 $v$，判断 $v+diff$, $v+diff+diff$ 是否也在 $nums$ 中，若是，累加三元组数目。这里用哈希表实现元素的快速查找。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def arithmeticTriplets(self, nums: List[int], diff: int) -> int:
        ans = 0
        n = len(nums)
        for i in range(n):
            for j in range(i + 1, n):
                for k in range(j + 1, n):
                    if nums[j] - nums[i] == nums[k] - nums[j] == diff:
                        ans += 1
        return ans
```

```python
class Solution:
    def arithmeticTriplets(self, nums: List[int], diff: int) -> int:
        return sum(b - a == diff and c - b == diff for a, b, c in combinations(nums, 3))
```

```python
class Solution:
    def arithmeticTriplets(self, nums: List[int], diff: int) -> int:
        s = set(nums)
        return sum(v + diff in s and v + diff + diff in s for v in nums)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    if (nums[j] - nums[i] == diff && nums[k] - nums[j] == diff) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        boolean[] vis = new boolean[310];
        for (int v : nums) {
            vis[v] = true;
        }
        int ans = 0;
        for (int v : nums) {
            if (vis[v + diff] && vis[v + diff + diff]) {
                ++ans;
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
    int arithmeticTriplets(vector<int>& nums, int diff) {
        int ans = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    if (nums[j] - nums[i] == diff && nums[k] - nums[j] == diff) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int arithmeticTriplets(vector<int>& nums, int diff) {
        vector<bool> vis(310);
        for (int v : nums) vis[v] = true;
        int ans = 0;
        for (int v : nums) ans += vis[v + diff] && vis[v + diff + diff];
        return ans;
    }
};
```

### **Go**

```go
func arithmeticTriplets(nums []int, diff int) int {
	ans := 0
	n := len(nums)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			for k := j + 1; k < n; k++ {
				if nums[j]-nums[i] == diff && nums[k]-nums[j] == diff {
					ans++
				}
			}
		}
	}
	return ans
}
```

```go
func arithmeticTriplets(nums []int, diff int) int {
	vis := make([]bool, 310)
	for _, v := range nums {
		vis[v] = true
	}
	ans := 0
	for _, v := range nums {
		if vis[v+diff] && vis[v+diff+diff] {
			ans++
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function arithmeticTriplets(nums: number[], diff: number): number {
    let res = 0;
    const n = nums.length;
    for (let i = 0; i < n - 2; i++) {
        for (let j = i + 1; j < n - 1; j++) {
            for (let k = j + 1; k < n; k++) {
                if (nums[k] - nums[j] > diff) {
                    break;
                }
                if (nums[j] - nums[i] === diff && nums[k] - nums[j] === diff) {
                    res++;
                }
            }
        }
    }
    return res;
}
```

```ts
function arithmeticTriplets(nums: number[], diff: number): number {
    let vis = new Array(310).fill(false);
    for (const v of nums) {
        vis[v] = true;
    }
    let ans = 0;
    for (const v of nums) {
        if (vis[v + diff] && vis[v + diff + diff]) {
            ++ans;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
