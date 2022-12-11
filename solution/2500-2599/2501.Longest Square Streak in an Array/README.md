# [2501. 数组中最长的方波](https://leetcode.cn/problems/longest-square-streak-in-an-array)

[English Version](/solution/2500-2599/2501.Longest%20Square%20Streak%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 。如果 <code>nums</code> 的子序列满足下述条件，则认为该子序列是一个 <strong>方波</strong> ：</p>

<ul>
	<li>子序列的长度至少为 <code>2</code> ，并且</li>
	<li>将子序列从小到大排序 <strong>之后</strong> ，除第一个元素外，每个元素都是前一个元素的 <strong>平方</strong> 。</li>
</ul>

<p>返回<em> </em><code>nums</code><em> </em>中 <strong>最长方波</strong> 的长度，如果不存在 <strong>方波</strong><em> </em>则返回<em> </em><code>-1</code> 。</p>

<p><strong>子序列</strong> 也是一个数组，可以由另一个数组删除一些或不删除元素且不改变剩余元素的顺序得到。</p>

<p>&nbsp;</p>

<p><strong>示例 1 ：</strong></p>

<pre><strong>输入：</strong>nums = [4,3,6,16,8,2]
<strong>输出：</strong>3
<strong>解释：</strong>选出子序列 [4,16,2] 。排序后，得到 [2,4,16] 。
- 4 = 2 * 2.
- 16 = 4 * 4.
因此，[4,16,2] 是一个方波.
可以证明长度为 4 的子序列都不是方波。
</pre>

<p><strong>示例 2 ：</strong></p>

<pre><strong>输入：</strong>nums = [2,3,5,6,7]
<strong>输出：</strong>-1
<strong>解释：</strong>nums 不存在方波，所以返回 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 枚举**

我们先用哈希表记录数组中的所有元素，然后枚举数组中的每个元素作为子序列的第一个元素，将该元素不断平方，并判断平方后的结果是否在哈希表中，如果在，则将平方后的结果作为下一个元素，继续判断，直到平方后的结果不在哈希表中，此时判断子序列的长度是否大于 $1$，如果是，则更新答案。

时间复杂度 $O(n \times \log \log M)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度，而 $M$ 为数组 `nums` 中的最大元素。

**方法二：记忆化搜索**

与方法一类似，我们先用哈希表记录数组中的所有元素。然后设计一个函数 $dfs(x)$，表示以 $x$ 为第一个元素的方波的长度。那么答案就是 $max(dfs(x))$，其中 $x$ 为数组 `nums` 中的元素。

函数 $dfs(x)$ 的计算过程如下：

-   如果 $x$ 不在哈希表中，则返回 $0$。
-   否则，返回 $1 + dfs(x^2)$。

过程中我们可以使用记忆化搜索，即使用哈希表记录函数 $dfs(x)$ 的值，避免重复计算。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestSquareStreak(self, nums: List[int]) -> int:
        s = set(nums)
        ans = -1
        for v in nums:
            t = 0
            while v in s:
                v *= v
                t += 1
            if t > 1:
                ans = max(ans, t)
        return ans
```

```python
class Solution:
    def longestSquareStreak(self, nums: List[int]) -> int:
        @cache
        def dfs(x):
            if x not in s:
                return 0
            return 1 + dfs(x * x)

        s = set(nums)
        ans = max(dfs(x) for x in nums)
        return -1 if ans < 2 else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestSquareStreak(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int v : nums) {
            s.add(v);
        }
        int ans = -1;
        for (int v : nums) {
            int t = 0;
            while (s.contains(v)) {
                v *= v;
                ++t;
            }
            if (t > 1) {
                ans = Math.max(ans, t);
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    private Map<Integer, Integer> f = new HashMap<>();
    private Set<Integer> s = new HashSet<>();

    public int longestSquareStreak(int[] nums) {
        for (int v : nums) {
            s.add(v);
        }
        int ans = 0;
        for (int v : nums) {
            ans = Math.max(ans, dfs(v));
        }
        return ans < 2 ? -1 : ans;
    }

    private int dfs(int x) {
        if (!s.contains(x)) {
            return 0;
        }
        if (f.containsKey(x)) {
            return f.get(x);
        }
        int ans = 1 + dfs(x * x);
        f.put(x, ans);
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestSquareStreak(vector<int>& nums) {
        unordered_set<long long> s(nums.begin(), nums.end());
        int ans = -1;
        for (int& v : nums) {
            int t = 0;
            long long x = v;
            while (s.count(x)) {
                x *= x;
                ++t;
            }
            if (t > 1) ans = max(ans, t);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int longestSquareStreak(vector<int>& nums) {
        unordered_set<long long> s(nums.begin(), nums.end());
        int ans = 0;
        unordered_map<int, int> f;
        function<int(int)> dfs = [&](int x) -> int {
            if (!s.count(x)) return 0;
            if (f.count(x)) return f[x];
            long long t = 1ll * x * x;
            if (t > INT_MAX) return 1;
            f[x] = 1 + dfs(x * x);
            return f[x];
        };
        for (int& v : nums) ans = max(ans, dfs(v));
        return ans < 2 ? -1 : ans;
    }
};
```

### **Go**

```go
func longestSquareStreak(nums []int) int {
	s := map[int]bool{}
	for _, v := range nums {
		s[v] = true
	}
	ans := -1
	for _, v := range nums {
		t := 0
		for s[v] {
			v *= v
			t++
		}
		if t > 1 && t > ans {
			ans = t
		}
	}
	return ans
}
```

```go
func longestSquareStreak(nums []int) (ans int) {
	s := map[int]bool{}
	for _, v := range nums {
		s[v] = true
	}
	f := map[int]int{}
	var dfs func(int) int
	dfs = func(x int) int {
		if !s[x] {
			return 0
		}
		if v, ok := f[x]; ok {
			return v
		}
		f[x] = 1 + dfs(x*x)
		return f[x]
	}
	for _, v := range nums {
		if t := dfs(v); ans < t {
			ans = t
		}
	}
	if ans < 2 {
		return -1
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
