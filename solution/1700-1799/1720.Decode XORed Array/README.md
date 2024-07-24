---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1720.Decode%20XORed%20Array/README.md
rating: 1284
source: 第 223 场周赛 Q1
tags:
    - 位运算
    - 数组
---

<!-- problem:start -->

# [1720. 解码异或后的数组](https://leetcode.cn/problems/decode-xored-array)

[English Version](/solution/1700-1799/1720.Decode%20XORed%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p><strong>未知</strong> 整数数组 <code>arr</code> 由 <code>n</code> 个非负整数组成。</p>

<p>经编码后变为长度为 <code>n - 1</code> 的另一个整数数组 <code>encoded</code> ，其中 <code>encoded[i] = arr[i] XOR arr[i + 1]</code> 。例如，<code>arr = [1,0,2,1]</code> 经编码后得到 <code>encoded = [1,2,3]</code> 。</p>

<p>给你编码后的数组 <code>encoded</code> 和原数组 <code>arr</code> 的第一个元素 <code>first</code>（<code>arr[0]</code>）。</p>

<p>请解码返回原数组 <code>arr</code> 。可以证明答案存在并且是唯一的。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>encoded = [1,2,3], first = 1
<strong>输出：</strong>[1,0,2,1]
<strong>解释：</strong>若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>encoded = [6,2,7,3], first = 4
<strong>输出：</strong>[4,2,0,7,4]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 10<sup>4</sup></code></li>
	<li><code>encoded.length == n - 1</code></li>
	<li><code>0 <= encoded[i] <= 10<sup>5</sup></code></li>
	<li><code>0 <= first <= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

根据题目描述，有：

$$
\textit{encoded}[i] = \textit{arr}[i] \oplus \textit{arr}[i + 1]
$$

如果我们将等式两边同时异或上 $\textit{arr}[i]$，那么就会得到：

$$
\textit{arr}[i] \oplus \textit{arr}[i] \oplus \textit{arr}[i + 1] = \textit{arr}[i] \oplus \textit{encoded}[i]
$$

即：

$$
\textit{arr}[i + 1] = \textit{arr}[i] \oplus \textit{encoded}[i]
$$

根据上述推导，我们可以从 $\textit{first}$ 开始，依次计算出数组 $\textit{arr}$ 的每一个元素。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def decode(self, encoded: List[int], first: int) -> List[int]:
        ans = [first]
        for e in encoded:
            ans.append(ans[-1] ^ e)
        return ans
```

#### Java

```java
class Solution {
    public int[] decode(int[] encoded, int first) {
        int n = encoded.length;
        int[] ans = new int[n + 1];
        ans[0] = first;
        for (int i = 0; i < n; ++i) {
            ans[i + 1] = ans[i] ^ encoded[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> decode(vector<int>& encoded, int first) {
        vector<int> ans = {{first}};
        for (int x : encoded) {
            ans.push_back(ans.back() ^ x);
        }
        return ans;
    }
};
```

#### Go

```go
func decode(encoded []int, first int) []int {
	ans := []int{first}
	for i, x := range encoded {
		ans = append(ans, ans[i]^x)
	}
	return ans
}
```

#### TypeScript

```ts
function decode(encoded: number[], first: number): number[] {
    const ans: number[] = [first];
    for (const x of encoded) {
        ans.push(ans.at(-1)! ^ x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
