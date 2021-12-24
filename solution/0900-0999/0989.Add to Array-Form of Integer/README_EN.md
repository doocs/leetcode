# [989. Add to Array-Form of Integer](https://leetcode.com/problems/add-to-array-form-of-integer)

[中文文档](/solution/0900-0999/0989.Add%20to%20Array-Form%20of%20Integer/README.md)

## Description

<p>The <strong>array-form</strong> of an integer <code>num</code> is an array representing its digits in left to right order.</p>

<ul>
	<li>For example, for <code>num = 1321</code>, the array form is <code>[1,3,2,1]</code>.</li>
</ul>

<p>Given <code>num</code>, the <strong>array-form</strong> of an integer, and an integer <code>k</code>, return <em>the <strong>array-form</strong> of the integer</em> <code>num + k</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = [1,2,0,0], k = 34
<strong>Output:</strong> [1,2,3,4]
<strong>Explanation:</strong> 1200 + 34 = 1234
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = [2,7,4], k = 181
<strong>Output:</strong> [4,5,5]
<strong>Explanation:</strong> 274 + 181 = 455
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = [2,1,5], k = 806
<strong>Output:</strong> [1,0,2,1]
<strong>Explanation:</strong> 215 + 806 = 1021
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> num = [9,9,9,9,9,9,9,9,9,9], k = 1
<strong>Output:</strong> [1,0,0,0,0,0,0,0,0,0,0]
<strong>Explanation:</strong> 9999999999 + 1 = 10000000000
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= num[i] &lt;= 9</code></li>
	<li><code>num</code>&nbsp;does not contain any leading zeros except for the zero itself.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
