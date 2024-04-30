# [2143. 在两个数组的区间中选取数字 🔒](https://leetcode.cn/problems/choose-numbers-from-two-arrays-in-range)

[English Version](/solution/2100-2199/2143.Choose%20Numbers%20From%20Two%20Arrays%20in%20Range/README_EN.md)

<!-- tags:数组,动态规划 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个 <strong>下标从 0 开始</strong>，长度为 <code>n</code> 的整数数组 <code>nums1</code> 和 <code>nums2</code>。</p>

<p>如果一个区间 <code>[l, r]</code> （<strong>包含左右端点</strong>，<code>0 &lt;= l &lt;= r &lt; n</code>）满足下列条件，那么这个区间就是 <strong>平衡</strong> 的：</p>

<ul>
	<li>对每个在区间 <code>[l, r]</code> 范围内的 <code>i</code>，你需要选取&nbsp;<code>nums1[i]</code> 或者&nbsp;<code>nums2[i]</code>；</li>
	<li>从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等。（如果你没有从某个数组中选取任何数字，那么数字和被视为 <code>0</code>）。</li>
</ul>

<p>如果两个 <strong>平衡</strong> 的区间 <code>[l<sub>1</sub>, r<sub>1</sub>]</code> 和 <code>[l<sub>2</sub>, r<sub>2</sub>]</code> 满足下列条件之一，那么它们就是 <strong>不同</strong> 的：</p>

<ul>
	<li><code>l<sub>1</sub> != l<sub>2</sub></code></li>
	<li><code>r<sub>1</sub> != r<sub>2</sub></code></li>
	<li>两个区间中的数字选取情况不同（也就是说，存在至少一个 <code>i</code>，使得在第一个区间中，<code>nums1[i]</code> 被选中, 而在第二个区间中，<code>nums2[i]</code> 被选中，或者相反的情况）。</li>
</ul>

<p>请返回 <strong>不同</strong> 的平衡的区间数目。由于答案可能很大，请返回答案 <strong>模 </strong><code>10<sup>9</sup>+7</code> 的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [1,2,5], nums2 = [2,6,3]
<strong>输出:</strong> 3
<strong>解释:</strong> 平衡的区间有:
- [0, 1], 我们选取 nums2[0] 和 nums2[1]。
  从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等: 2 = 2.
- [0, 2], 我们选取 nums1[0], nums2[1] 和 nums1[2]。
  从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等: 1 + 5 = 6。
- [0, 2], 我们选取 nums1[0], nums1[1] 和 nums2[2]。
  从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等: 1 + 2 = 3。
注意第二个区间和第三个区间是不同的。
因为在第二个平衡的区间中，我们选取了 nums2[1]，但是在第三个平衡的区间中，我们选取了 nums1[1]。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> nums1 = [0,1], nums2 = [1,0]
<strong>输出:</strong> 4
<strong>解释:</strong> 平衡的区间有:
- [0, 0], 我们选取 nums1[0]。
  从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等: 0 = 0。
- [1, 1], 我们选取 nums2[1]。
  从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等: 0 = 0。
- [0, 1], 我们选取 nums1[0] 和 nums2[1]。
  从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等: 0 = 0。
- [0, 1], 我们选取 nums2[0] 和 nums1[1]。
  从 <code>nums1</code> 中选取的数字和与从 <code>nums2</code> 中选取的数字和相等: 1 = 1。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 100</code></li>
</ul>

## 解法

### 方法一：动态规划

我们定义 $f[i][j]$ 表示以第 $i$ 个元素结尾，且从 $nums1$ 中选取的数字和与从 $nums2$ 中选取的数字和之差为 $j$ 的平衡区间的个数。由于差值可能为负数，因此，我们统一将 $j$ 加上 $s_2 = \sum_{k=0}^{n-1}nums2[k]$，这样就可以保证 $j$ 为非负整数。

考虑 $f[i][j]$，我们可以单独将第 $i$ 个元素视为一个区间，那么 $f[i][nums1[i] + s_2]$ 和 $f[i][-nums2[i] + s_2]$ 都会增加 $1$。此外，如果 $i \gt 0$，我们也可以将第 $i$ 个元素添加到前面的某个区间中，我们在 $[0, s_1 + s_2]$ 范围内枚举 $j$，如果 $j \geq a$，那么 $f[i][j]$ 会增加 $f[i - 1][j - a]$，如果 $j + b \leq s_1 + s_2$，那么 $f[i][j]$ 会增加 $f[i - 1][j + b]$。

答案为 $\sum_{i=0}^{n-1}f[i][s_2]$。

时间复杂度 $O(n \times M)$，空间复杂度 $O(n \times M)$。其中 $n$ 和 $M$ 分别为数组 $nums1$ 的长度以及数字和的最大值。

<!-- tabs:start -->

```python
class Solution:
    def countSubranges(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums1)
        s1, s2 = sum(nums1), sum(nums2)
        f = [[0] * (s1 + s2 + 1) for _ in range(n)]
        ans = 0
        mod = 10**9 + 7
        for i, (a, b) in enumerate(zip(nums1, nums2)):
            f[i][a + s2] += 1
            f[i][-b + s2] += 1
            if i:
                for j in range(s1 + s2 + 1):
                    if j >= a:
                        f[i][j] = (f[i][j] + f[i - 1][j - a]) % mod
                    if j + b < s1 + s2 + 1:
                        f[i][j] = (f[i][j] + f[i - 1][j + b]) % mod
            ans = (ans + f[i][s2]) % mod
        return ans
```

```java
class Solution {
    public int countSubranges(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int s1 = Arrays.stream(nums1).sum();
        int s2 = Arrays.stream(nums2).sum();
        int[][] f = new int[n][s1 + s2 + 1];
        int ans = 0;
        final int mod = (int) 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            int a = nums1[i], b = nums2[i];
            f[i][a + s2]++;
            f[i][-b + s2]++;
            if (i > 0) {
                for (int j = 0; j <= s1 + s2; ++j) {
                    if (j >= a) {
                        f[i][j] = (f[i][j] + f[i - 1][j - a]) % mod;
                    }
                    if (j + b <= s1 + s2) {
                        f[i][j] = (f[i][j] + f[i - 1][j + b]) % mod;
                    }
                }
            }
            ans = (ans + f[i][s2]) % mod;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countSubranges(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        int s1 = accumulate(nums1.begin(), nums1.end(), 0);
        int s2 = accumulate(nums2.begin(), nums2.end(), 0);
        int f[n][s1 + s2 + 1];
        memset(f, 0, sizeof(f));
        int ans = 0;
        const int mod = 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            int a = nums1[i], b = nums2[i];
            f[i][a + s2]++;
            f[i][-b + s2]++;
            if (i) {
                for (int j = 0; j <= s1 + s2; ++j) {
                    if (j >= a) {
                        f[i][j] = (f[i][j] + f[i - 1][j - a]) % mod;
                    }
                    if (j + b <= s1 + s2) {
                        f[i][j] = (f[i][j] + f[i - 1][j + b]) % mod;
                    }
                }
            }
            ans = (ans + f[i][s2]) % mod;
        }
        return ans;
    }
};
```

```go
func countSubranges(nums1 []int, nums2 []int) (ans int) {
	n := len(nums1)
	s1, s2 := sum(nums1), sum(nums2)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, s1+s2+1)
	}
	const mod int = 1e9 + 7
	for i, a := range nums1 {
		b := nums2[i]
		f[i][a+s2]++
		f[i][-b+s2]++
		if i > 0 {
			for j := 0; j <= s1+s2; j++ {
				if j >= a {
					f[i][j] = (f[i][j] + f[i-1][j-a]) % mod
				}
				if j+b <= s1+s2 {
					f[i][j] = (f[i][j] + f[i-1][j+b]) % mod
				}
			}
		}
		ans = (ans + f[i][s2]) % mod
	}
	return
}

func sum(nums []int) (ans int) {
	for _, x := range nums {
		ans += x
	}
	return
}
```

```ts
function countSubranges(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const s1 = nums1.reduce((a, b) => a + b, 0);
    const s2 = nums2.reduce((a, b) => a + b, 0);
    const f: number[][] = Array(n)
        .fill(0)
        .map(() => Array(s1 + s2 + 1).fill(0));
    const mod = 1e9 + 7;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const [a, b] = [nums1[i], nums2[i]];
        f[i][a + s2]++;
        f[i][-b + s2]++;
        if (i) {
            for (let j = 0; j <= s1 + s2; ++j) {
                if (j >= a) {
                    f[i][j] = (f[i][j] + f[i - 1][j - a]) % mod;
                }
                if (j + b <= s1 + s2) {
                    f[i][j] = (f[i][j] + f[i - 1][j + b]) % mod;
                }
            }
        }
        ans = (ans + f[i][s2]) % mod;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
