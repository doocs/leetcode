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

### **...**

```

```

<!-- tabs:end -->
