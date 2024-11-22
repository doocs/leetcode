---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2261.K%20Divisible%20Elements%20Subarrays/README.md
rating: 1724
source: 第 291 场周赛 Q3
tags:
    - 字典树
    - 数组
    - 哈希表
    - 枚举
    - 哈希函数
    - 滚动哈希
---

<!-- problem:start -->

# [2261. 含最多 K 个可整除元素的子数组](https://leetcode.cn/problems/k-divisible-elements-subarrays)

[English Version](/solution/2200-2299/2261.K%20Divisible%20Elements%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和两个整数 <code>k</code> 和 <code>p</code> ，找出并返回满足要求的不同的子数组数，要求子数组中最多 <code>k</code> 个可被 <code>p</code> 整除的元素。</p>

<p>如果满足下述条件之一，则认为数组 <code>nums1</code> 和 <code>nums2</code> 是 <strong>不同</strong> 数组：</p>

<ul>
	<li>两数组长度 <strong>不同</strong> ，或者</li>
	<li>存在 <strong>至少 </strong>一个下标 <code>i</code> 满足 <code>nums1[i] != nums2[i]</code> 。</li>
</ul>

<p><strong>子数组</strong> 定义为：数组中的连续元素组成的一个 <strong>非空</strong> 序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [<em><strong>2</strong></em>,3,3,<em><strong>2</strong></em>,<em><strong>2</strong></em>], k = 2, p = 2
<strong>输出：</strong>11
<strong>解释：</strong>
位于下标 0、3 和 4 的元素都可以被 p = 2 整除。
共计 11 个不同子数组都满足最多含 k = 2 个可以被 2 整除的元素：
[2]、[2,3]、[2,3,3]、[2,3,3,2]、[3]、[3,3]、[3,3,2]、[3,3,2,2]、[3,2]、[3,2,2] 和 [2,2] 。
注意，尽管子数组 [2] 和 [3] 在 nums 中出现不止一次，但统计时只计数一次。
子数组 [2,3,3,2,2] 不满足条件，因为其中有 3 个元素可以被 2 整除。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4], k = 4, p = 1
<strong>输出：</strong>10
<strong>解释：</strong>
nums 中的所有元素都可以被 p = 1 整除。
此外，nums 中的每个子数组都满足最多 4 个元素可以被 1 整除。
因为所有子数组互不相同，因此满足所有限制条件的子数组总数为 10 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i], p &lt;= 200</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<p>你可以设计并实现时间复杂度为 <code>O(n<sup>2</sup>)</code> 的算法解决此问题吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举 + 字符串哈希

我们可以枚举子数组的左端点 $i$，再在 $[i, n)$ 的范围内枚举子数组的右端点 $j$，在枚举右端点的过程中，我们通过双哈希的方式，将子数组的哈希值存入集合中，最后返回集合的大小即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countDistinct(self, nums: List[int], k: int, p: int) -> int:
        s = set()
        n = len(nums)
        base1, base2 = 131, 13331
        mod1, mod2 = 10**9 + 7, 10**9 + 9
        for i in range(n):
            h1 = h2 = cnt = 0
            for j in range(i, n):
                cnt += nums[j] % p == 0
                if cnt > k:
                    break
                h1 = (h1 * base1 + nums[j]) % mod1
                h2 = (h2 * base2 + nums[j]) % mod2
                s.add(h1 << 32 | h2)
        return len(s)
```

#### Java

```java
class Solution {
    public int countDistinct(int[] nums, int k, int p) {
        Set<Long> s = new HashSet<>();
        int n = nums.length;
        int base1 = 131, base2 = 13331;
        int mod1 = (int) 1e9 + 7, mod2 = (int) 1e9 + 9;
        for (int i = 0; i < n; ++i) {
            long h1 = 0, h2 = 0;
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                cnt += nums[j] % p == 0 ? 1 : 0;
                if (cnt > k) {
                    break;
                }
                h1 = (h1 * base1 + nums[j]) % mod1;
                h2 = (h2 * base2 + nums[j]) % mod2;
                s.add(h1 << 32 | h2);
            }
        }
        return s.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countDistinct(vector<int>& nums, int k, int p) {
        unordered_set<long long> s;
        int n = nums.size();
        int base1 = 131, base2 = 13331;
        int mod1 = 1e9 + 7, mod2 = 1e9 + 9;
        for (int i = 0; i < n; ++i) {
            long long h1 = 0, h2 = 0;
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                cnt += nums[j] % p == 0;
                if (cnt > k) {
                    break;
                }
                h1 = (h1 * base1 + nums[j]) % mod1;
                h2 = (h2 * base2 + nums[j]) % mod2;
                s.insert(h1 << 32 | h2);
            }
        }
        return s.size();
    }
};
```

#### Go

```go
func countDistinct(nums []int, k int, p int) int {
	s := map[int]bool{}
	base1, base2 := 131, 13331
	mod1, mod2 := 1000000007, 1000000009
	for i := range nums {
		h1, h2, cnt := 0, 0, 0
		for j := i; j < len(nums); j++ {
			if nums[j]%p == 0 {
				cnt++
				if cnt > k {
					break
				}
			}
			h1 = (h1*base1 + nums[j]) % mod1
			h2 = (h2*base2 + nums[j]) % mod2
			s[h1<<32|h2] = true
		}
	}
	return len(s)
}
```

#### TypeScript

```ts
function countDistinct(nums: number[], k: number, p: number): number {
    const s = new Set<bigint>();
    const [base1, base2] = [131, 13331];
    const [mod1, mod2] = [1000000007, 1000000009];
    for (let i = 0; i < nums.length; i++) {
        let [h1, h2, cnt] = [0, 0, 0];
        for (let j = i; j < nums.length; j++) {
            if (nums[j] % p === 0) {
                cnt++;
                if (cnt > k) {
                    break;
                }
            }
            h1 = (h1 * base1 + nums[j]) % mod1;
            h2 = (h2 * base2 + nums[j]) % mod2;
            s.add((BigInt(h1) << 32n) | BigInt(h2));
        }
    }
    return s.size;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countDistinct(self, nums: List[int], k: int, p: int) -> int:
        n = len(nums)
        s = set()
        for i in range(n):
            cnt = 0
            t = ""
            for x in nums[i:]:
                cnt += x % p == 0
                if cnt > k:
                    break
                t += str(x) + ","
                s.add(t)
        return len(s)
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
