---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3043.Find%20the%20Length%20of%20the%20Longest%20Common%20Prefix/README.md
rating: 1688
source: 第 385 场周赛 Q2
tags:
    - 字典树
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [3043. 最长公共前缀的长度](https://leetcode.cn/problems/find-the-length-of-the-longest-common-prefix)

[English Version](/solution/3000-3099/3043.Find%20the%20Length%20of%20the%20Longest%20Common%20Prefix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个 <strong>正整数 </strong>数组 <code>arr1</code> 和 <code>arr2</code> 。</p>

<p>正整数的 <strong>前缀 </strong>是其 <strong>最左边 </strong>的一位或多位数字组成的整数。例如，<code>123</code> 是整数 <code>12345</code> 的前缀，而 <code>234</code><strong> 不是 </strong>。</p>

<p>设若整数 <code>c</code> 是整数 <code>a</code> 和 <code>b</code> 的<strong> 公共前缀 </strong>，那么 <code>c</code> 需要同时是 <code>a</code> 和 <code>b</code> 的前缀。例如，<code>5655359</code> 和 <code>56554</code> 有公共前缀 <code>565</code> ，而 <code>1223</code> 和 <code>43456</code><strong> 没有 </strong>公共前缀。</p>

<p>你需要找出属于 <code>arr1</code> 的整数 <code>x</code> 和属于 <code>arr2</code> 的整数 <code>y</code> 组成的所有数对 <code>(x, y)</code> 之中最长的公共前缀的长度。</p>

<p>返回所有数对之中最长公共前缀的长度。如果它们之间不存在公共前缀，则返回 <code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr1 = [1,10,100], arr2 = [1000]
<strong>输出：</strong>3
<strong>解释：</strong>存在 3 个数对 (arr1[i], arr2[j]) ：
- (1, 1000) 的最长公共前缀是 1 。
- (10, 1000) 的最长公共前缀是 10 。
- (100, 1000) 的最长公共前缀是 100 。
最长的公共前缀是 100 ，长度为 3 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr1 = [1,2,3], arr2 = [4,4,4]
<strong>输出：</strong>0
<strong>解释：</strong>任何数对 (arr1[i], arr2[j]) 之中都不存在公共前缀，因此返回 0 。
请注意，同一个数组内元素之间的公共前缀不在考虑范围内。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= arr1[i], arr2[i] &lt;= 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以使用哈希表来存储 `arr1` 中的所有数字的前缀，然后遍历 `arr2` 中的所有数字 $x$，对于每个数字 $x$，我们从最高位开始逐渐减小，判断是否存在于哈希表中，如果存在，那么我们就找到了一个公共前缀，此时更新答案即可。

时间复杂度 $O(m \times \log M + n \times \log N)$，空间复杂度 $O(m \times \log M)$。其中 $m$ 和 $n$ 分别是 `arr1` 和 `arr2` 的长度，而 $M$ 和 $N$ 分别是 `arr1` 和 `arr2` 中的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestCommonPrefix(self, arr1: List[int], arr2: List[int]) -> int:
        s = set()
        for x in arr1:
            while x:
                s.add(x)
                x //= 10
        ans = 0
        for x in arr2:
            while x:
                if x in s:
                    ans = max(ans, len(str(x)))
                    break
                x //= 10
        return ans
```

#### Java

```java
class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> s = new HashSet<>();
        for (int x : arr1) {
            for (; x > 0; x /= 10) {
                s.add(x);
            }
        }
        int ans = 0;
        for (int x : arr2) {
            for (; x > 0; x /= 10) {
                if (s.contains(x)) {
                    ans = Math.max(ans, String.valueOf(x).length());
                    break;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestCommonPrefix(vector<int>& arr1, vector<int>& arr2) {
        unordered_set<int> s;
        for (int x : arr1) {
            for (; x; x /= 10) {
                s.insert(x);
            }
        }
        int ans = 0;
        for (int x : arr2) {
            for (; x; x /= 10) {
                if (s.count(x)) {
                    ans = max(ans, (int) log10(x) + 1);
                    break;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestCommonPrefix(arr1 []int, arr2 []int) (ans int) {
	s := map[int]bool{}
	for _, x := range arr1 {
		for ; x > 0; x /= 10 {
			s[x] = true
		}
	}
	for _, x := range arr2 {
		for ; x > 0; x /= 10 {
			if s[x] {
				ans = max(ans, int(math.Log10(float64(x)))+1)
				break
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function longestCommonPrefix(arr1: number[], arr2: number[]): number {
    const s: Set<number> = new Set<number>();
    for (let x of arr1) {
        for (; x; x = (x / 10) | 0) {
            s.add(x % 10);
        }
    }
    let ans: number = 0;
    for (let x of arr2) {
        for (; x; x = (x / 10) | 0) {
            if (s.has(x % 10)) {
                ans = Math.max(ans, Math.floor(Math.log10(x)) + 1);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
