# [2568. 最小无法得到的或值](https://leetcode.cn/problems/minimum-impossible-or)

[English Version](/solution/2500-2599/2568.Minimum%20Impossible%20OR/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>如果存在一些整数满足&nbsp;<code>0 &lt;= index<sub>1</sub> &lt; index<sub>2</sub> &lt; ... &lt; index<sub>k</sub> &lt; nums.length</code>&nbsp;，得到&nbsp;<code>nums[index<sub>1</sub>] | nums[index<sub>2</sub>] | ... | nums[index<sub>k</sub>] = x</code>&nbsp;，那么我们说&nbsp;<code>x</code>&nbsp;是&nbsp;<strong>可表达的</strong>&nbsp;。换言之，如果一个整数能由&nbsp;<code>nums</code>&nbsp;的某个子序列的或运算得到，那么它就是可表达的。</p>

<p>请你返回 <code>nums</code>&nbsp;不可表达的 <strong>最小非零整数</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [2,1]
<b>输出：</b>4
<b>解释：</b>1 和 2 已经在数组中，因为 nums[0] | nums[1] = 2 | 1 = 3 ，所以 3 是可表达的。由于 4 是不可表达的，所以我们返回 4 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [5,3,2]
<b>输出：</b>1
<b>解释：</b>1 是最小不可表达的数字。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举 2 的幂**

我们从整数 $1$ 开始考虑，如果 $1$ 是可表达的，那么它必须出现在数组 `nums` 中；如果 $2$ 是可表达的，那么它必须出现在数组 `nums` 中；如果 $1$ 和 $2$ 都是可表达的，那么它们的或运算 $3$ 也是可表达的，以此类推。

因此，我们可以枚举 $2$ 的幂，如果当前枚举的 $2^i$ 不在数组 `nums` 中，那么 $2^i$ 就是不可表达的最小整数。

时间复杂度 $O(n + \log M)$，空间复杂度 $O(n)$。其中 $n$ 和 $M$ 分别是数组 `nums` 的长度和数组 `nums` 中的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minImpossibleOR(self, nums: List[int]) -> int:
        s = set(nums)
        return next(1 << i for i in range(32) if 1 << i not in s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minImpossibleOR(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        for (int i = 0;; ++i) {
            if (!s.contains(1 << i)) {
                return 1 << i;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minImpossibleOR(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        for (int i = 0;; ++i) {
            if (!s.count(1 << i)) {
                return 1 << i;
            }
        }
    }
};
```

### **Go**

```go
func minImpossibleOR(nums []int) int {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	for i := 0; ; i++ {
		if !s[1<<i] {
			return 1 << i
		}
	}
}
```

### **TypeScript**

```ts
function minImpossibleOR(nums: number[]): number {
    const s: Set<number> = new Set();
    for (const x of nums) {
        s.add(x);
    }
    for (let i = 0; ; ++i) {
        if (!s.has(1 << i)) {
            return 1 << i;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
