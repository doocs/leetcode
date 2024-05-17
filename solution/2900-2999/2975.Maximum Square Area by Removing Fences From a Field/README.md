---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2975.Maximum%20Square%20Area%20by%20Removing%20Fences%20From%20a%20Field/README.md
rating: 1873
source: 第 377 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 枚举
---

<!-- problem:start -->

# [2975. 移除栅栏得到的正方形田地的最大面积](https://leetcode.cn/problems/maximum-square-area-by-removing-fences-from-a-field)

[English Version](/solution/2900-2999/2975.Maximum%20Square%20Area%20by%20Removing%20Fences%20From%20a%20Field/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一个大型的 <code>(m - 1) x (n - 1)</code> 矩形田地，其两个对角分别是 <code>(1, 1)</code> 和 <code>(m, n)</code> ，田地内部有一些水平栅栏和垂直栅栏，分别由数组 <code>hFences</code> 和 <code>vFences</code> 给出。</p>

<p>水平栅栏为坐标 <code>(hFences[i], 1)</code> 到 <code>(hFences[i], n)</code>，垂直栅栏为坐标 <code>(1, vFences[i])</code> 到 <code>(m, vFences[i])</code> 。</p>

<p>返回通过<strong> 移除 </strong>一些栅栏（<strong>可能不移除</strong>）所能形成的最大面积的<strong> 正方形 </strong>田地的面积，或者如果无法形成正方形田地则返回 <code>-1</code>。</p>

<p>由于答案可能很大，所以请返回结果对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 后的值。</p>

<p><strong>注意：</strong>田地外围两个水平栅栏（坐标 <code>(1, 1)</code> 到 <code>(1, n)</code> 和坐标 <code>(m, 1)</code> 到 <code>(m, n)</code> ）以及两个垂直栅栏（坐标 <code>(1, 1)</code> 到 <code>(m, 1)</code> 和坐标 <code>(1, n)</code> 到 <code>(m, n)</code> ）所包围。这些栅栏<strong> 不能</strong> 被移除。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2975.Maximum%20Square%20Area%20by%20Removing%20Fences%20From%20a%20Field/images/screenshot-from-2023-11-05-22-40-25.png" /></p>

<pre>
<strong>输入：</strong>m = 4, n = 3, hFences = [2,3], vFences = [2]
<strong>输出：</strong>4
<strong>解释：</strong>移除位于 2 的水平栅栏和位于 2 的垂直栅栏将得到一个面积为 4 的正方形田地。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2975.Maximum%20Square%20Area%20by%20Removing%20Fences%20From%20a%20Field/images/maxsquareareaexample1.png" style="width: 285px; height: 242px;" /></p>

<pre>
<strong>输入：</strong>m = 6, n = 7, hFences = [2], vFences = [4]
<strong>输出：</strong>-1
<strong>解释：</strong>可以证明无法通过移除栅栏形成正方形田地。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= m, n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= hFences.length, vFences.length &lt;= 600</code></li>
	<li><code>1 &lt; hFences[i] &lt; m</code></li>
	<li><code>1 &lt; vFences[i] &lt; n</code></li>
	<li><code>hFences</code> 和 <code>vFences</code> 中的元素是唯一的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以枚举 $hFences$ 中的任意两条水平栅栏 $a$ 和 $b$，计算 $a$ 和 $b$ 之间的距离 $d$，记录在哈希表 $hs$ 中，然后枚举 $vFences$ 中的任意两条垂直栅栏 $c$ 和 $d$，计算 $c$ 和 $d$ 之间的距离 $d$，记录在哈希表 $vs$ 中，最后遍历哈希表 $hs$，如果 $hs$ 中的某个距离 $d$ 在哈希表 $vs$ 中也存在，那么说明存在一个正方形田地，其边长为 $d$，面积为 $d^2$，我们只需要取最大的 $d$，求 $d^2 \bmod 10^9 + 7$ 即可。

时间复杂度 $O(h^2 + v^2)$，空间复杂度 $O(h^2 + v^2)$。其中 $h$ 和 $v$ 分别是 $hFences$ 和 $vFences$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maximizeSquareArea(
        self, m: int, n: int, hFences: List[int], vFences: List[int]
    ) -> int:
        def f(nums: List[int], k: int) -> Set[int]:
            nums.extend([1, k])
            nums.sort()
            return {b - a for a, b in combinations(nums, 2)}

        mod = 10**9 + 7
        hs = f(hFences, m)
        vs = f(vFences, n)
        ans = max(hs & vs, default=0)
        return ans**2 % mod if ans else -1
```

```java
class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        Set<Integer> hs = f(hFences, m);
        Set<Integer> vs = f(vFences, n);
        hs.retainAll(vs);
        int ans = -1;
        final int mod = (int) 1e9 + 7;
        for (int x : hs) {
            ans = Math.max(ans, x);
        }
        return ans > 0 ? (int) (1L * ans * ans % mod) : -1;
    }

    private Set<Integer> f(int[] nums, int k) {
        int n = nums.length;
        nums = Arrays.copyOf(nums, n + 2);
        nums[n] = 1;
        nums[n + 1] = k;
        Arrays.sort(nums);
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                s.add(nums[i] - nums[j]);
            }
        }
        return s;
    }
}
```

```cpp
class Solution {
public:
    int maximizeSquareArea(int m, int n, vector<int>& hFences, vector<int>& vFences) {
        auto f = [](vector<int>& nums, int k) {
            nums.push_back(k);
            nums.push_back(1);
            sort(nums.begin(), nums.end());
            unordered_set<int> s;
            for (int i = 0; i < nums.size(); ++i) {
                for (int j = 0; j < i; ++j) {
                    s.insert(nums[i] - nums[j]);
                }
            }
            return s;
        };
        auto hs = f(hFences, m);
        auto vs = f(vFences, n);
        int ans = 0;
        for (int h : hs) {
            if (vs.count(h)) {
                ans = max(ans, h);
            }
        }
        const int mod = 1e9 + 7;
        return ans > 0 ? 1LL * ans * ans % mod : -1;
    }
};
```

```go
func maximizeSquareArea(m int, n int, hFences []int, vFences []int) int {
	f := func(nums []int, k int) map[int]bool {
		nums = append(nums, 1, k)
		sort.Ints(nums)
		s := map[int]bool{}
		for i := 0; i < len(nums); i++ {
			for j := 0; j < i; j++ {
				s[nums[i]-nums[j]] = true
			}
		}
		return s
	}
	hs := f(hFences, m)
	vs := f(vFences, n)
	ans := 0
	for h := range hs {
		if vs[h] {
			ans = max(ans, h)
		}
	}
	if ans > 0 {
		return ans * ans % (1e9 + 7)
	}
	return -1
}
```

```ts
function maximizeSquareArea(m: number, n: number, hFences: number[], vFences: number[]): number {
    const f = (nums: number[], k: number): Set<number> => {
        nums.push(1, k);
        nums.sort((a, b) => a - b);
        const s: Set<number> = new Set();
        for (let i = 0; i < nums.length; ++i) {
            for (let j = 0; j < i; ++j) {
                s.add(nums[i] - nums[j]);
            }
        }
        return s;
    };
    const hs = f(hFences, m);
    const vs = f(vFences, n);
    let ans = 0;
    for (const h of hs) {
        if (vs.has(h)) {
            ans = Math.max(ans, h);
        }
    }
    return ans ? Number(BigInt(ans) ** 2n % 1000000007n) : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
