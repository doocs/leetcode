# [989. 数组形式的整数加法](https://leetcode-cn.com/problems/add-to-array-form-of-integer)

[English Version](/solution/0900-0999/0989.Add%20to%20Array-Form%20of%20Integer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>整数的 <strong>数组形式</strong> &nbsp;<code>num</code>&nbsp;是按照从左到右的顺序表示其数字的数组。</p>

<ul>
	<li>例如，对于 <code>num = 1321</code> ，数组形式是 <code>[1,3,2,1]</code> 。</li>
</ul>

<p>给定 <code>num</code> ，整数的 <strong>数组形式</strong> ，和整数 <code>k</code> ，返回 <em>整数 <code>num + k</code> 的 <strong>数组形式</strong></em> 。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = [1,2,0,0], k = 34
<strong>输出：</strong>[1,2,3,4]
<strong>解释：</strong>1200 + 34 = 1234
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = [2,7,4], k = 181
<strong>输出：</strong>[4,5,5]
<strong>解释：</strong>274 + 181 = 455
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>num = [2,1,5], k = 806
<strong>输出：</strong>[1,0,2,1]
<strong>解释：</strong>215 + 806 = 1021
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= num[i] &lt;= 9</code></li>
	<li><code>num</code>&nbsp;不包含任何前导零，除了零本身</li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

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
