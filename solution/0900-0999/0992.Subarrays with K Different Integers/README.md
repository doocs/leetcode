# [992. K 个不同整数的子数组](https://leetcode.cn/problems/subarrays-with-k-different-integers)

[English Version](/solution/0900-0999/0992.Subarrays%20with%20K%20Different%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数数组 <code>nums</code>和一个整数 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">k</span></span></font></font>&nbsp;，返回 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">num</span></span></font></font>&nbsp;中 「<strong>好子数组」</strong><em>&nbsp;</em>的数目。</p>

<p>如果 <code>nums</code>&nbsp;的某个子数组中不同整数的个数恰好为 <code>k</code>，则称 <code>nums</code>&nbsp;的这个连续、不一定不同的子数组为 <strong>「</strong><strong>好子数组 」</strong>。</p>

<ul>
	<li>例如，<code>[1,2,3,1,2]</code> 中有&nbsp;<code>3</code>&nbsp;个不同的整数：<code>1</code>，<code>2</code>，以及&nbsp;<code>3</code>。</li>
</ul>

<p><strong>子数组</strong> 是数组的 <strong>连续</strong> 部分。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2,3], k = 2
<strong>输出：</strong>7
<strong>解释：</strong>恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,3,4], k = 3
<strong>输出：</strong>3
<strong>解释：</strong>恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= nums.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

我们遍历数组 $nums$，对于每个 $i$，我们需要找到最小的 $j_1$，使得 $nums[j_1], nums[j_1 + 1], \dots, nums[i]$ 中不同的整数个数小于等于 $k$，以及最小的 $j_2$，使得 $nums[j_2], nums[j_2 + 1], \dots, nums[i]$ 中不同的整数个数小于等于 $k-1$。那么 $j_2 - j_1$ 就是以 $i$ 结尾的满足条件的子数组的个数。

在实现上，我们定义一个函数 $f(k)$，表示 $nums$ 中每个位置 $i$ 对应的最小的 $j$，使得 $nums[j], nums[j + 1], \dots, nums[i]$ 中不同的整数个数小于等于 $k$。该函数可以通过双指针实现，具体实现见代码。

然后我们调用 $f(k)$ 和 $f(k-1)$，计算出每个位置对应的 $j_1$ 和 $j_2$，然后计算出以每个位置 $i$ 结尾的满足条件的子数组的个数，最后求和即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def subarraysWithKDistinct(self, nums: List[int], k: int) -> int:
        def f(k):
            pos = [0] * len(nums)
            cnt = Counter()
            j = 0
            for i, x in enumerate(nums):
                cnt[x] += 1
                while len(cnt) > k:
                    cnt[nums[j]] -= 1
                    if cnt[nums[j]] == 0:
                        cnt.pop(nums[j])
                    j += 1
                pos[i] = j
            return pos

        return sum(a - b for a, b in zip(f(k - 1), f(k)))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int[] left = f(nums, k);
        int[] right = f(nums, k - 1);
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            ans += right[i] - left[i];
        }
        return ans;
    }

    private int[] f(int[] nums, int k) {
        int n = nums.length;
        int[] cnt = new int[n + 1];
        int[] pos = new int[n];
        int s = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++s;
            }
            for (; s > k; ++j) {
                if (--cnt[nums[j]] == 0) {
                    --s;
                }
            }
            pos[i] = j;
        }
        return pos;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int subarraysWithKDistinct(vector<int>& nums, int k) {
        vector<int> left = f(nums, k);
        vector<int> right = f(nums, k - 1);
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            ans += right[i] - left[i];
        }
        return ans;
    }

    vector<int> f(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> pos(n);
        int cnt[n + 1];
        memset(cnt, 0, sizeof(cnt));
        int s = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++s;
            }
            for (; s > k; ++j) {
                if (--cnt[nums[j]] == 0) {
                    --s;
                }
            }
            pos[i] = j;
        }
        return pos;
    }
};
```

### **Go**

```go
func subarraysWithKDistinct(nums []int, k int) (ans int) {
	f := func(k int) []int {
		n := len(nums)
		pos := make([]int, n)
		cnt := make([]int, n+1)
		s, j := 0, 0
		for i, x := range nums {
			cnt[x]++
			if cnt[x] == 1 {
				s++
			}
			for ; s > k; j++ {
				cnt[nums[j]]--
				if cnt[nums[j]] == 0 {
					s--
				}
			}
			pos[i] = j
		}
		return pos
	}
	left, right := f(k), f(k-1)
	for i := range left {
		ans += right[i] - left[i]
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
