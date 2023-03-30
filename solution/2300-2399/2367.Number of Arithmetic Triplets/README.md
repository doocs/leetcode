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

我们注意到，数组 $nums$ 的长度只有不超过 $200$，因此可以直接暴力枚举 $i$, $j$, $k$，判断是否满足条件，若满足，累加三元组数目。

时间复杂度 $O(n^3)$，其中 $n$ 为数组 $nums$ 的长度。空间复杂度 $O(1)$。

**方法二：数组或哈希表**

我们可以先将 $nums$ 中的元素存入哈希表或数组 $vis$ 中，然后枚举 $nums$ 中的每个元素 $x$，判断 $x+diff$, $x+diff+diff$ 是否也在 $vis$ 中，若是，累加三元组数目。

枚举结束后，返回答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def arithmeticTriplets(self, nums: List[int], diff: int) -> int:
        return sum(b - a == diff and c - b == diff for a, b, c in combinations(nums, 3))
```

```python
class Solution:
    def arithmeticTriplets(self, nums: List[int], diff: int) -> int:
        vis = set(nums)
        return sum(x + diff in vis and x + diff * 2 in vis for x in nums)
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
        boolean[] vis = new boolean[301];
        for (int x : nums) {
            vis[x] = true;
        }
        int ans = 0;
        for (int x : nums) {
            if (vis[x + diff] && vis[x + diff + diff]) {
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
        bitset<301> vis;
        for (int x : nums) {
            vis[x] = 1;
        }
        int ans = 0;
        for (int x : nums) {
            ans += vis[x + diff] && vis[x + diff + diff];
        }
        return ans;
    }
};
```

### **Go**

```go
func arithmeticTriplets(nums []int, diff int) (ans int) {
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
	return
}
```

```go
func arithmeticTriplets(nums []int, diff int) (ans int) {
	vis := [301]bool{}
	for _, x := range nums {
		vis[x] = true
	}
	for _, x := range nums {
		if vis[x+diff] && vis[x+diff+diff] {
			ans++
		}
	}
	return
}
```

### **TypeScript**

```ts
function arithmeticTriplets(nums: number[], diff: number): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; ++j) {
            for (let k = j + 1; k < n; ++k) {
                if (nums[j] - nums[i] === diff && nums[k] - nums[j] === diff) {
                    ++ans;
                }
            }
        }
    }
    return ans;
}
```

```ts
function arithmeticTriplets(nums: number[], diff: number): number {
    const vis: boolean[] = new Array(301).fill(false);
    for (const x of nums) {
        vis[x] = true;
    }
    let ans = 0;
    for (const x of nums) {
        if (vis[x + diff] && vis[x + diff + diff]) {
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
