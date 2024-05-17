---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2941.Maximum%20GCD-Sum%20of%20a%20Subarray/README.md
tags:
    - 数组
    - 数学
    - 二分查找
    - 数论
---

<!-- problem:start -->

# [2941. 子数组的最大 GCD-Sum 🔒](https://leetcode.cn/problems/maximum-gcd-sum-of-a-subarray)

[English Version](/solution/2900-2999/2941.Maximum%20GCD-Sum%20of%20a%20Subarray/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组 <code>nums</code> 和一个整数 <code>k</code>.</p>

<p>数组 <code>a</code> 的 <strong>gcd-sum</strong>&nbsp;计算方法如下：</p>

<ul>
	<li>设&nbsp;<code>s</code>&nbsp;为&nbsp;<code>a</code>&nbsp;的所有元素的和。</li>
	<li>设&nbsp;<code>g</code>&nbsp;为&nbsp;<code>a</code>&nbsp;的所有元素的 <strong>最大公约数</strong>。</li>
	<li><code>a</code> 的 gcd-sum&nbsp;等于 <code>s * g</code>.</li>
</ul>

<p>返回 <em><code>nums</code> 的至少包含 <code>k</code> 个元素的子数组的 <strong>最大 gcd-sum</strong>。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,4,4,4,2], k = 2
<b>输出：</b>48
<b>解释：</b>我们选择子数组 [4,4,4]，该数组的 gcd-sum 为 4 * (4 + 4 + 4) = 48。
可以证明我们无法选择任何其他 gcd-sum 大于 48 的子数组。</pre>

<p><b>示例 2：</b></p>

<pre>
<b>输入：</b>nums = [7,3,9,4], k = 1
<b>输出：</b>81
<b>解释：</b>我们选择子数组 [9]，该数组的 gcd-sum 为 9 * 9 = 81。
可以证明我们无法选择任何其他 gcd-sum 大于 81 的子数组。</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxGcdSum(self, nums: List[int], k: int) -> int:
        s = list(accumulate(nums, initial=0))
        f = []
        ans = 0
        for i, v in enumerate(nums):
            g = []
            for j, x in f:
                y = gcd(x, v)
                if not g or g[-1][1] != y:
                    g.append((j, y))
            f = g
            f.append((i, v))
            for j, x in f:
                if i - j + 1 >= k:
                    ans = max(ans, (s[i + 1] - s[j]) * x)
        return ans
```

#### Java

```java
class Solution {
    public long maxGcdSum(int[] nums, int k) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        List<int[]> f = new ArrayList<>();
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            List<int[]> g = new ArrayList<>();
            for (var e : f) {
                int j = e[0], x = e[1];
                int y = gcd(x, nums[i]);
                if (g.isEmpty() || g.get(g.size() - 1)[1] != y) {
                    g.add(new int[] {j, y});
                }
            }
            f = g;
            f.add(new int[] {i, nums[i]});
            for (var e : f) {
                int j = e[0], x = e[1];
                if (i - j + 1 >= k) {
                    ans = Math.max(ans, (s[i + 1] - s[j]) * x);
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxGcdSum(vector<int>& nums, int k) {
        int n = nums.size();
        long long s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        vector<pair<int, int>> f;
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            vector<pair<int, int>> g;
            for (auto [j, x] : f) {
                int y = gcd(x, nums[i]);
                if (g.empt() || g.back().second != y) {
                    g.emplace_back(j, y);
                }
            }
            f = move(g);
            f.emplace_back(i, nums[i]);
            for (auto [j, x] : f) {
                if (i - j + 1 >= k) {
                    ans = max(ans, (s[i + 1] - s[j]) * x);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxGcdSum(nums []int, k int) int64 {
	n := len(nums)
	s := make([]int64, n+1)
	s[0] = 0
	for i, x := range nums {
		s[i+1] = s[i] + int64(x)
	}
	type pair [2]int
	var f []pair
	var ans int64
	for i := 0; i < n; i++ {
		var g []pair
		for _, p := range f {
			j, x := p[0], p[1]
			y := int(gcd(int(x), nums[i]))
			if len(g) == 0 || g[len(g)-1][1] != y {
				g = append(g, pair{j, y})
			}
		}
		f = g
		f = append(f, pair{i, nums[i]})
		for _, p := range f {
			j, x := p[0], p[1]
			if i-j+1 >= k {
				ans = max(ans, (s[i+1]-s[j])*int64(x))
			}
		}
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

#### TypeScript

```ts
function maxGcdSum(nums: number[], k: number): number {
    const n: number = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++) {
        s[i] = s[i - 1] + nums[i - 1];
    }

    let f: [number, number][] = [];
    let ans: number = 0;

    for (let i = 0; i < n; ++i) {
        const g: [number, number][] = [];
        for (const [j, x] of f) {
            const y: number = gcd(x, nums[i]);
            if (g.length === 0 || g.at(-1)[1] !== y) {
                g.push([j, y]);
            }
        }
        f = g;
        f.push([i, nums[i]]);
        for (const [j, x] of f) {
            if (i - j + 1 >= k) {
                ans = Math.max(ans, (s[i + 1] - s[j]) * x);
            }
        }
    }

    return ans;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### TypeScript

```ts
function maxGcdSum(nums: number[], k: number): number {
    const n: number = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++) {
        s[i] = s[i - 1] + nums[i - 1];
    }

    let f: [number, number][] = [];
    let ans: number = 0;

    for (let i = 0; i < n; ++i) {
        const g: [number, number][] = [];
        for (const [j, x] of f) {
            const y: number = gcd(x, nums[i]);
            if (g.length === 0 || g.at(-1)[1] !== y) {
                g.push([j, y]);
            }
        }
        f = g;
        f.push([i, nums[i]]);
        for (const [j, x] of f) {
            if (i - j + 1 >= k) {
                ans = Math.max(ans, (s[i + 1] - s[j]) * x);
            }
        }
    }

    return ans;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
