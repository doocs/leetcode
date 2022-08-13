# [2354. 优质数对的数目](https://leetcode.cn/problems/number-of-excellent-pairs)

[English Version](/solution/2300-2399/2354.Number%20of%20Excellent%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的正整数数组 <code>nums</code> 和一个正整数 <code>k</code> 。</p>

<p>如果满足下述条件，则数对 <code>(num1, num2)</code> 是 <strong>优质数对</strong> ：</p>

<ul>
	<li><code>num1</code> 和 <code>num2</code> <strong>都</strong> 在数组 <code>nums</code> 中存在。</li>
	<li><code>num1 OR num2</code> 和 <code>num1 AND num2</code> 的二进制表示中值为 <strong>1</strong> 的位数之和大于等于 <code>k</code> ，其中 <code>OR</code> 是按位 <strong>或</strong> 操作，而 <code>AND</code> 是按位 <strong>与</strong> 操作。</li>
</ul>

<p>返回 <strong>不同</strong> 优质数对的数目。</p>

<p>如果&nbsp;<code>a != c</code> 或者 <code>b != d</code> ，则认为 <code>(a, b)</code> 和 <code>(c, d)</code> 是不同的两个数对。例如，<code>(1, 2)</code> 和 <code>(2, 1)</code> 不同。</p>

<p><strong>注意：</strong>如果 <code>num1</code> 在数组中至少出现 <strong>一次</strong> ，则满足 <code>num1 == num2</code> 的数对 <code>(num1, num2)</code> 也可以是优质数对。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,1], k = 3
<strong>输出：</strong>5
<strong>解释：</strong>有如下几个优质数对：
- (3, 3)：(3 AND 3) 和 (3 OR 3) 的二进制表示都等于 (11) 。值为 1 的位数和等于 2 + 2 = 4 ，大于等于 k = 3 。
- (2, 3) 和 (3, 2)： (2 AND 3) 的二进制表示等于 (10) ，(2 OR 3) 的二进制表示等于 (11) 。值为 1 的位数和等于 1 + 2 = 3 。
- (1, 3) 和 (3, 1)： (1 AND 3) 的二进制表示等于 (01) ，(1 OR 3) 的二进制表示等于 (11) 。值为 1 的位数和等于 1 + 2 = 3 。
所以优质数对的数目是 5 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,1,1], k = 10
<strong>输出：</strong>0
<strong>解释：</strong>该数组中不存在优质数对。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 60</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countExcellentPairs(self, nums: List[int], k: int) -> int:
        s = set(nums)
        ans = 0
        cnt = Counter()
        for v in s:
            cnt[v.bit_count()] += 1
        for v in s:
            t = v.bit_count()
            for i, x in cnt.items():
                if t + i >= k:
                    ans += x
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long countExcellentPairs(int[] nums, int k) {
        Set<Integer> s = new HashSet<>();
        for (int v : nums) {
            s.add(v);
        }
        long ans = 0;
        int[] cnt = new int[32];
        for (int v : s) {
            int t = Integer.bitCount(v);
            ++cnt[t];
        }
        for (int v : s) {
            int t = Integer.bitCount(v);
            for (int i = 0; i < 32; ++i) {
                if (t + i >= k) {
                    ans += cnt[i];
                }
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
    long long countExcellentPairs(vector<int>& nums, int k) {
        unordered_set<int> s(nums.begin(), nums.end());
        vector<int> cnt(32);
        for (int v : s) ++cnt[__builtin_popcount(v)];
        long long ans = 0;
        for (int v : s) {
            int t = __builtin_popcount(v);
            for (int i = 0; i < 32; ++i) {
                if (t + i >= k) {
                    ans += cnt[i];
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countExcellentPairs(nums []int, k int) int64 {
	s := map[int]bool{}
	for _, v := range nums {
		s[v] = true
	}
	cnt := make([]int, 32)
	for v := range s {
		t := bits.OnesCount(uint(v))
		cnt[t]++
	}
	ans := 0
	for v := range s {
		t := bits.OnesCount(uint(v))
		for i, x := range cnt {
			if t+i >= k {
				ans += x
			}
		}
	}
	return int64(ans)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
