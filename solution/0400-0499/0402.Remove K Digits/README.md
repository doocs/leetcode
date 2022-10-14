# [402. 移掉 K 位数字](https://leetcode.cn/problems/remove-k-digits)

[English Version](/solution/0400-0499/0402.Remove%20K%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个以字符串表示的非负整数 <code>num</code> 和一个整数 <code>k</code> ，移除这个数中的 <code>k</code><em> </em>位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。</p>
 

<p><strong>示例 1 ：</strong></p>

<pre>
<strong>输入：</strong>num = "1432219", k = 3
<strong>输出：</strong>"1219"
<strong>解释：</strong>移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
</pre>

<p><strong>示例 2 ：</strong></p>

<pre>
<strong>输入：</strong>num = "10200", k = 1
<strong>输出：</strong>"200"
<strong>解释：</strong>移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
</pre>

<p><strong>示例 3 ：</strong></p>

<pre>
<strong>输入：</strong>num = "10", k = 2
<strong>输出：</strong>"0"
<strong>解释：</strong>从原数字移除所有的数字，剩余为空就是 0 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= k <= num.length <= 10<sup>5</sup></code></li>
	<li><code>num</code> 仅由若干位数字（0 - 9）组成</li>
	<li>除了 <strong>0</strong> 本身之外，<code>num</code> 不含任何前导零</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心算法**

前置知识：两个相同位数的数字大小关系取决于第一个不同位的数的大小。

基本的思路如下：

-   从左到右遍历数组元素；
-   对于遍历到的当前元素，选择保留；
-   但可以选择性丢弃前面的相邻元素，丢弃与否取决于当前元素和前面相邻元素的大小；
-   根据前置知识可知当当前元素小于前面相邻元素时可以移除前面相邻的元素。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeKdigits(self, num: str, k: int) -> str:
        stack, remain = [], len(num)-k
        for value in num:
            while k and stack and stack[-1] > value:
                k = k-1
                stack.pop()
            stack.append(value)
        return "".join(stack[:remain]).lstrip('0') or '0'
```

### **Go**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```go
func removeKdigits(num string, k int) string {
	stack, remain := make([]byte, 0), len(num)-k
	for i := 0; i < len(num); i++ {
		n := len(stack)
		for k > 0 && n > 0 && stack[n-1] > num[i] {
			stack = stack[:n-1]
			n, k = n-1, k-1
		}
		stack = append(stack, num[i])
	}
	// 返回删除 k 个字符之后的字符串，需要去除可能存在的前置 0
	for i := 0; i < len(stack) && i < remain; i++ {
		if stack[i] != '0' {
			return string(stack[i:remain])
		}
	}
	return "0"
}
```

### **TypeScript**

```ts
function removeKdigits(num: string, k: number): string {
    let nums = [...num];
    while (k > 0) {
        let idx = 0;
        while (idx < nums.length - 1 && nums[idx + 1] >= nums[idx]) {
            idx++;
        }
        nums.splice(idx, 1);
        k--;
    }
    return nums.join('').replace(/^0*/g, '') || '0';
}
```

### **...**

```

```

<!-- tabs:end -->
