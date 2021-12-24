# [989. 数组形式的整数加法](https://leetcode-cn.com/problems/add-to-array-form-of-integer)

[English Version](/solution/0900-0999/0989.Add%20to%20Array-Form%20of%20Integer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>对于非负整数&nbsp;<code>X</code>&nbsp;而言，<em><code>X</code></em>&nbsp;的<em>数组形式</em>是每位数字按从左到右的顺序形成的数组。例如，如果&nbsp;<code>X = 1231</code>，那么其数组形式为&nbsp;<code>[1,2,3,1]</code>。</p>

<p>给定非负整数 <code>X</code> 的数组形式&nbsp;<code>A</code>，返回整数&nbsp;<code>X+K</code>&nbsp;的数组形式。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>A = [1,2,0,0], K = 34
<strong>输出：</strong>[1,2,3,4]
<strong>解释：</strong>1200 + 34 = 1234
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>A = [2,7,4], K = 181
<strong>输出：</strong>[4,5,5]
<strong>解释：</strong>274 + 181 = 455
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>A = [2,1,5], K = 806
<strong>输出：</strong>[1,0,2,1]
<strong>解释：</strong>215 + 806 = 1021
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>A = [9,9,9,9,9,9,9,9,9,9], K = 1
<strong>输出：</strong>[1,0,0,0,0,0,0,0,0,0,0]
<strong>解释：</strong>9999999999 + 1 = 10000000000
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 10000</code></li>
	<li><code>0 &lt;= A[i] &lt;= 9</code></li>
	<li><code>0 &lt;= K &lt;= 10000</code></li>
	<li>如果&nbsp;<code>A.length &gt; 1</code>，那么&nbsp;<code>A[0] != 0</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

数组从尾到头遍历，分别与 `K` 中的每一位相加，进位保存在 `carry` 中，不进位和则添加到结果列表中。最后逆序结果列表即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def addToArrayForm(self, A: List[int], K: int) -> List[int]:
        n = len(A) - 1
        carry, res = 0, []
        while n >= 0 or K != 0 or carry != 0:
            carry += (0 if n < 0 else A[n]) + (K % 10)
            res.append(carry % 10)
            K //= 10
            carry //= 10
            n -= 1
        return res[::-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        int n = A.length - 1;
        List<Integer> res = new ArrayList<>();
        int carry = 0;
        while (n >= 0 || K != 0 || carry != 0) {
            carry += (n < 0 ? 0 : A[n]) + (K % 10);
            res.add(carry % 10);
            K /= 10;
            carry /= 10;
            --n;
        }
        Collections.reverse(res);
        return res;
    }
}
```

### **TypeScript**

```ts
function addToArrayForm(num: number[], k: number): number[] {
    let arr2 = [...String(k)].map(Number);
    let ans = [];
    let sum = 0;
    while (num.length || arr2.length || sum) {
        let a = num.pop() || 0,
            b = arr2.pop() || 0;
        sum += a + b;
        ans.unshift(sum % 10);
        sum = Math.floor(sum / 10);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
