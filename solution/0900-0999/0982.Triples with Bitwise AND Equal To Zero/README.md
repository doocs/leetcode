# [982. 按位与为零的三元组](https://leetcode.cn/problems/triples-with-bitwise-and-equal-to-zero)

[English Version](/solution/0900-0999/0982.Triples%20with%20Bitwise%20AND%20Equal%20To%20Zero/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，返回其中 <strong>按位与三元组</strong> 的数目。</p>

<p><strong>按位与三元组</strong> 是由下标 <code>(i, j, k)</code> 组成的三元组，并满足下述全部条件：</p>

<ul>
	<li><code>0 &lt;= i &lt; nums.length</code></li>
	<li><code>0 &lt;= j &lt; nums.length</code></li>
	<li><code>0 &lt;= k &lt; nums.length</code></li>
	<li><code>nums[i] &amp; nums[j] &amp; nums[k] == 0</code> ，其中 <code>&amp;</code> 表示按位与运算符。</li>
</ul>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,1,3]
<strong>输出：</strong>12
<strong>解释：</strong>可以选出如下 i, j, k 三元组：
(i=0, j=0, k=1) : 2 &amp; 2 &amp; 1
(i=0, j=1, k=0) : 2 &amp; 1 &amp; 2
(i=0, j=1, k=1) : 2 &amp; 1 &amp; 1
(i=0, j=1, k=2) : 2 &amp; 1 &amp; 3
(i=0, j=2, k=1) : 2 &amp; 3 &amp; 1
(i=1, j=0, k=0) : 1 &amp; 2 &amp; 2
(i=1, j=0, k=1) : 1 &amp; 2 &amp; 1
(i=1, j=0, k=2) : 1 &amp; 2 &amp; 3
(i=1, j=1, k=0) : 1 &amp; 1 &amp; 2
(i=1, j=2, k=0) : 1 &amp; 3 &amp; 2
(i=2, j=0, k=1) : 3 &amp; 2 &amp; 1
(i=2, j=1, k=0) : 3 &amp; 1 &amp; 2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,0,0]
<strong>输出：</strong>27
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>16</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举 + 计数**

我们可以先枚举任意两个数 $x$ 和 $y$，用哈希表或数组 $cnt$ 统计它们的按位与结果 $x \& y$ 出现的次数。

然后我们枚举 $x$ 和 $y$ 的按位与结果 $xy$，再枚举 $z$，如果 $xy \& z = 0$，则将 $cnt[xy]$ 的值加入答案。

最后返回答案即可。

时间复杂度 $O(n^2 + n \times M)$，空间复杂度 $O(M)$，其中 $n$ 是数组 $nums$ 的长度；而 $M$ 是数组 $nums$ 中的最大值，本题中 $M \leq 2^{16}$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countTriplets(self, nums: List[int]) -> int:
        cnt = Counter(x & y for x in nums for y in nums)
        return sum(v for xy, v in cnt.items() for z in nums if xy & z == 0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countTriplets(int[] nums) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int[] cnt = new int[mx + 1];
        for (int x : nums) {
            for (int y : nums) {
                cnt[x & y]++;
            }
        }
        int ans = 0;
        for (int xy = 0; xy <= mx; ++xy) {
            for (int z : nums) {
                if ((xy & z) == 0) {
                    ans += cnt[xy];
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
    int countTriplets(vector<int>& nums) {
        int mx = *max_element(nums.begin(), nums.end());
        int cnt[mx + 1];
        memset(cnt, 0, sizeof cnt);
        for (int& x : nums) {
            for (int& y : nums) {
                cnt[x & y]++;
            }
        }
        int ans = 0;
        for (int xy = 0; xy <= mx; ++xy) {
            for (int& z : nums) {
                if ((xy & z) == 0) {
                    ans += cnt[xy];
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countTriplets(nums []int) (ans int) {
	mx := 0
	for _, x := range nums {
		mx = max(mx, x)
	}
	cnt := make([]int, mx+1)
	for _, x := range nums {
		for _, y := range nums {
			cnt[x&y]++
		}
	}
	for xy := 0; xy <= mx; xy++ {
		for _, z := range nums {
			if xy&z == 0 {
				ans += cnt[xy]
			}
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
