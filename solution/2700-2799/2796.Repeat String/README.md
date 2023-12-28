# [2796. 重复字符串](https://leetcode.cn/problems/repeat-string)

[English Version](/solution/2700-2799/2796.Repeat%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写代码实现字符串方法 <code>string.replicate(x)</code> ，它将返回重复的字符串 <code>x</code> 次。</p>

<p>请尝试在不使用内置方法 <code>string.repeat</code> 的情况下实现它。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>str = "hello", times = 2
<b>输出：</b>"hellohello"
<b>解释：</b>"hello" 被重复了 2 次
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>str = "code", times = 3
<b>输出：</b>codecodecode"
<strong>Explanation:</strong> "code" 被重复了 3 次
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>str = "js", times = 1
<b>输出：</b>"js"
<b>解释：</b>"js" 被重复了 1 次
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= str.length,&nbsp;times &lt;=&nbsp;10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
declare global {
    interface String {
        replicate(times: number): string;
    }
}

String.prototype.replicate = function (times: number) {
    return new Array(times).fill(this).join('');
};
```

### **JavaScript**

```js
String.prototype.replicate = function (times) {
    return Array(times).fill(this).join('');
};
```

<!-- tabs:end -->
