# [402. 移掉K位数字](https://leetcode-cn.com/problems/remove-k-digits)

[English Version](/solution/0400-0499/0402.Remove%20K%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个以字符串表示的非负整数&nbsp;<em>num</em>，移除这个数中的 <em>k </em>位数字，使得剩下的数字最小。</p>

<p><strong>注意:</strong></p>

<ul>
	<li><em>num</em> 的长度小于 10002 且&nbsp;&ge; <em>k。</em></li>
	<li><em>num</em> 不会包含任何前导零。</li>
</ul>

<p><strong>示例 1 :</strong></p>

<pre>
输入: num = &quot;1432219&quot;, k = 3
输出: &quot;1219&quot;
解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
</pre>

<p><strong>示例 2 :</strong></p>

<pre>
输入: num = &quot;10200&quot;, k = 1
输出: &quot;200&quot;
解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
</pre>

<p>示例<strong> 3 :</strong></p>

<pre>
输入: num = &quot;10&quot;, k = 2
输出: &quot;0&quot;
解释: 从原数字移除所有的数字，剩余为空就是0。
</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

贪心算法

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

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
};
```

### **...**

```

```

<!-- tabs:end -->
