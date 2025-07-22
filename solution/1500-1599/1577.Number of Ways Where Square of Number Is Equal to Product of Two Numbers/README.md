---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1577.Number%20of%20Ways%20Where%20Square%20of%20Number%20Is%20Equal%20to%20Product%20of%20Two%20Numbers/README.md
rating: 1593
source: 第 205 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 数学
    - 双指针
---

<!-- problem:start -->

# [1577. 数的平方等于两数乘积的方法数](https://leetcode.cn/problems/number-of-ways-where-square-of-number-is-equal-to-product-of-two-numbers)

[English Version](/solution/1500-1599/1577.Number%20of%20Ways%20Where%20Square%20of%20Number%20Is%20Equal%20to%20Product%20of%20Two%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组 <code>nums1</code> 和 <code>nums2</code> ，请你返回根据以下规则形成的三元组的数目（类型 1 和类型 2 ）：</p>

<ul>
	<li>类型 1：三元组 <code>(i, j, k)</code> ，如果 <code>nums1[i]<sup>2</sup>&nbsp;== nums2[j] * nums2[k]</code> 其中 <code>0 &lt;= i &lt; nums1.length</code> 且 <code>0 &lt;= j &lt; k &lt; nums2.length</code></li>
	<li>类型 2：三元组 <code>(i, j, k)</code> ，如果 <code>nums2[i]<sup>2</sup>&nbsp;== nums1[j] * nums1[k]</code> 其中 <code>0 &lt;= i &lt; nums2.length</code> 且 <code>0 &lt;= j &lt; k &lt; nums1.length</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums1 = [7,4], nums2 = [5,2,8,9]
<strong>输出：</strong>1
<strong>解释：</strong>类型 1：(1,1,2), nums1[1]^2 = nums2[1] * nums2[2] (4^2 = 2 * 8)</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums1 = [1,1], nums2 = [1,1,1]
<strong>输出：</strong>9
<strong>解释：</strong>所有三元组都符合题目要求，因为 1^2 = 1 * 1
类型 1：(0,0,1), (0,0,2), (0,1,2), (1,0,1), (1,0,2), (1,1,2), nums1[i]^2 = nums2[j] * nums2[k]
类型 2：(0,0,1), (1,0,1), (2,0,1), nums2[i]^2 = nums1[j] * nums1[k]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums1 = [7,7,8,3], nums2 = [1,2,9,7]
<strong>输出：</strong>2
<strong>解释：</strong>有两个符合题目要求的三元组
类型 1：(3,0,2), nums1[3]^2 = nums2[0] * nums2[2]
类型 2：(3,0,1), nums2[3]^2 = nums1[0] * nums1[1]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>nums1 = [4,7,9,11,23], nums2 = [3,5,1024,12,18]
<strong>输出：</strong>0
<strong>解释：</strong>不存在符合题目要求的三元组
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10^5</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 枚举

我们用哈希表 $\textit{cnt1}$ 统计 $\textit{nums1}$ 中每个数对 $(\textit{nums}[j], \textit{nums}[k])$ 出现的次数，其中 $0 \leq j \lt k < m$，其中 $m$ 为数组 $\textit{nums1}$ 的长度。用哈希表 $\textit{cnt2}$ 统计 $\textit{nums2}$ 中每个数对 $(\textit{nums}[j], \textit{nums}[k])$ 出现的次数，其中 $0 \leq j \lt k < n$，其中 $n$ 为数组 $\textit{nums2}$ 的长度。

接下来，我们枚举数组 $\textit{nums1}$ 中的每个数 $x$，计算 $\textit{cnt2}[x^2]$ 的值，即 $\textit{nums2}$ 中有多少对数 $(\textit{nums}[j], \textit{nums}[k])$ 满足 $\textit{nums}[j] \times \textit{nums}[k] = x^2$。同理，我们枚举数组 $\textit{nums2}$ 中的每个数 $x$，计算 $\textit{cnt1}[x^2]$ 的值，即 $\textit{nums1}$ 中有多少对数 $(\textit{nums}[j], \textit{nums}[k])$ 满足 $\textit{nums}[j] \times \textit{nums}[k] = x^2$，最后将两者相加返回即可。

时间复杂度 $O(m^2 + n^2 + m + n)$，空间复杂度 $O(m^2 + n^2)$。其中 $m$ 和 $n$ 分别为数组 $\textit{nums1}$ 和 $\textit{nums2}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numTriplets(self, nums1: List[int], nums2: List[int]) -> int:
        def count(nums: List[int]) -> Counter:
            cnt = Counter()
            for j in range(len(nums)):
                for k in range(j + 1, len(nums)):
                    cnt[nums[j] * nums[k]] += 1
            return cnt

        def cal(nums: List[int], cnt: Counter) -> int:
            return sum(cnt[x * x] for x in nums)

        cnt1 = count(nums1)
        cnt2 = count(nums2)
        return cal(nums1, cnt2) + cal(nums2, cnt1)
```

#### Java

```java
class Solution {
    public int numTriplets(int[] nums1, int[] nums2) {
        var cnt1 = count(nums1);
        var cnt2 = count(nums2);
        return cal(cnt1, nums2) + cal(cnt2, nums1);
    }

    private Map<Long, Integer> count(int[] nums) {
        Map<Long, Integer> cnt = new HashMap<>();
        int n = nums.length;
        for (int j = 0; j < n; ++j) {
            for (int k = j + 1; k < n; ++k) {
                long x = (long) nums[j] * nums[k];
                cnt.merge(x, 1, Integer::sum);
            }
        }
        return cnt;
    }

    private int cal(Map<Long, Integer> cnt, int[] nums) {
        int ans = 0;
        for (int x : nums) {
            long y = (long) x * x;
            ans += cnt.getOrDefault(y, 0);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numTriplets(vector<int>& nums1, vector<int>& nums2) {
        auto cnt1 = count(nums1);
        auto cnt2 = count(nums2);
        return cal(cnt1, nums2) + cal(cnt2, nums1);
    }

    unordered_map<long long, int> count(vector<int>& nums) {
        unordered_map<long long, int> cnt;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                cnt[(long long) nums[i] * nums[j]]++;
            }
        }
        return cnt;
    }

    int cal(unordered_map<long long, int>& cnt, vector<int>& nums) {
        int ans = 0;
        for (int x : nums) {
            ans += cnt[(long long) x * x];
        }
        return ans;
    }
};
```

#### Go

```go
func numTriplets(nums1 []int, nums2 []int) int {
	cnt1 := count(nums1)
	cnt2 := count(nums2)
	return cal(cnt1, nums2) + cal(cnt2, nums1)
}

func count(nums []int) map[int]int {
	cnt := map[int]int{}
	for j, x := range nums {
		for _, y := range nums[j+1:] {
			cnt[x*y]++
		}
	}
	return cnt
}

func cal(cnt map[int]int, nums []int) (ans int) {
	for _, x := range nums {
		ans += cnt[x*x]
	}
	return
}
```

#### TypeScript

```ts
function numTriplets(nums1: number[], nums2: number[]): number {
    const cnt1 = count(nums1);
    const cnt2 = count(nums2);
    return cal(cnt1, nums2) + cal(cnt2, nums1);
}

function count(nums: number[]): Map<number, number> {
    const cnt: Map<number, number> = new Map();
    for (let j = 0; j < nums.length; ++j) {
        for (let k = j + 1; k < nums.length; ++k) {
            const x = nums[j] * nums[k];
            cnt.set(x, (cnt.get(x) || 0) + 1);
        }
    }
    return cnt;
}

function cal(cnt: Map<number, number>, nums: number[]): number {
    return nums.reduce((acc, x) => acc + (cnt.get(x * x) || 0), 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：哈希表 + 枚举优化

我们用哈希表 $\textit{cnt1}$ 统计 $\textit{nums1}$ 中每个数出现的次数，用哈希表 $\textit{cnt2}$ 统计 $\textit{nums2}$ 中每个数出现的次数。

接下来，我们枚举数组 $\textit{nums1}$ 中的每个数 $x$，然后枚举 $\textit{cnt2}$ 中的每个数对 $(y, v1)$，其中 $y$ 为 $\textit{cnt2}$ 的键，$v1$ 为 $\textit{cnt2}$ 的值。我们计算 $z = x^2 / y$，如果 $y \times z = x^2$，此时如果 $y = z$，说明 $y$ 和 $z$ 是同一个数，那么 $v1 = v2$，从 $v1$ 个数中任选两个数的方案数为 $v1 \times (v1 - 1) = v1 \times (v2 - 1)$；如果 $y \neq z$，那么 $v1$ 个数中任选两个数的方案数为 $v1 \times v2$。最后将所有方案数相加并除以 $2$ 即可。这里除以 $2$ 是因为我们统计的是对数对 $(j, k)$ 的方案数，而实际上 $(j, k)$ 和 $(k, j)$ 是同一种方案。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m + n)$。其中 $m$ 和 $n$ 分别为数组 $\textit{nums1}$ 和 $\textit{nums2}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numTriplets(self, nums1: List[int], nums2: List[int]) -> int:
        def cal(nums: List[int], cnt: Counter) -> int:
            ans = 0
            for x in nums:
                for y, v1 in cnt.items():
                    z = x * x // y
                    if y * z == x * x:
                        v2 = cnt[z]
                        ans += v1 * (v2 - int(y == z))
            return ans // 2

        cnt1 = Counter(nums1)
        cnt2 = Counter(nums2)
        return cal(nums1, cnt2) + cal(nums2, cnt1)
```

#### Java

```java
class Solution {
    public int numTriplets(int[] nums1, int[] nums2) {
        var cnt1 = count(nums1);
        var cnt2 = count(nums2);
        return cal(cnt1, nums2) + cal(cnt2, nums1);
    }

    private Map<Integer, Integer> count(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        return cnt;
    }

    private int cal(Map<Integer, Integer> cnt, int[] nums) {
        long ans = 0;
        for (int x : nums) {
            for (var e : cnt.entrySet()) {
                int y = e.getKey(), v1 = e.getValue();
                int z = (int) (1L * x * x / y);
                if (y * z == x * x) {
                    int v2 = cnt.getOrDefault(z, 0);
                    ans += v1 * (y == z ? v2 - 1 : v2);
                }
            }
        }
        return (int) (ans / 2);
    }
}
```

#### Go

```go
func numTriplets(nums1 []int, nums2 []int) int {
	cnt1 := count(nums1)
	cnt2 := count(nums2)
	return cal(cnt1, nums2) + cal(cnt2, nums1)
}

func count(nums []int) map[int]int {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	return cnt
}

func cal(cnt map[int]int, nums []int) (ans int) {
	for _, x := range nums {
		for y, v1 := range cnt {
			z := x * x / y
			if y*z == x*x {
				if v2, ok := cnt[z]; ok {
					if y == z {
						v2--
					}
					ans += v1 * v2
				}
			}
		}
	}
	ans /= 2
	return
}
```

#### TypeScript

```ts
function numTriplets(nums1: number[], nums2: number[]): number {
    const cnt1 = count(nums1);
    const cnt2 = count(nums2);
    return cal(cnt1, nums2) + cal(cnt2, nums1);
}

function count(nums: number[]): Map<number, number> {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    return cnt;
}

function cal(cnt: Map<number, number>, nums: number[]): number {
    let ans: number = 0;
    for (const x of nums) {
        for (const [y, v1] of cnt) {
            const z = Math.floor((x * x) / y);
            if (y * z == x * x) {
                const v2 = cnt.get(z) || 0;
                ans += v1 * (y === z ? v2 - 1 : v2);
            }
        }
    }
    return ans / 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
