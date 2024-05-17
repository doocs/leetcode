---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2794.Create%20Object%20from%20Two%20Arrays/README.md
---

<!-- problem:start -->

# [2794. 从两个数组中创建对象 🔒](https://leetcode.cn/problems/create-object-from-two-arrays)

[English Version](/solution/2700-2799/2794.Create%20Object%20from%20Two%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个数组 <code>keysArr </code>和 <code>valuesArr</code>，返回一个新的对象 <code>obj</code>。<code>obj</code> 中的每个键值对都来自 <code>keysArr[i]</code> 和 <code>valuesArr[i]</code>。</p>

<p>如果前面的索引中存在重复的键，则应该跳过该键值对。换句话说，只有第一次出现的键会被添加到对象中。</p>

<p>如果键不是字符串，则应通过调用 <code>String()</code> 方法将其转换为字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>keysArr = ["a", "b", "c"], valuesArr = [1, 2, 3]
<b>输出：</b>{"a": 1, "b": 2, "c": 3}
<b>解释：</b>键 "a"、"b" 和 "c" 分别与值 1、2 和 3 配对。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>keysArr = ["1", 1, false], valuesArr = [4, 5, 6]
<b>输出：</b>{"1": 4, "false": 6}
<b>解释：</b>首先，将 arr1 中的所有元素转换为字符串。我们可以看到有两个 "1" 的出现。使用第一次出现 "1" 的关联值：4。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>keysArr = [], valuesArr = []
<b>输出：</b>{}
<b>解释：</b>没有键，因此返回一个空对象。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>keysArr</code> 和 <code>valuesArr</code> 都是有效的 JSON 数组</li>
	<li><code>2 &lt;= JSON.stringify(keysArr).length,&nbsp;JSON.stringify(valuesArr).length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>keysArr.length === valuesArr.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```ts
function createObject(keysArr: any[], valuesArr: any[]): Record<string, any> {
    const ans: Record<string, any> = {};
    for (let i = 0; i < keysArr.length; ++i) {
        const k = String(keysArr[i]);
        if (ans[k] === undefined) {
            ans[k] = valuesArr[i];
        }
    }
    return ans;
}
```

```js
/**
 * @param {Array} keysArr
 * @param {Array} valuesArr
 * @return {Object}
 */
var createObject = function (keysArr, valuesArr) {
    const ans = {};
    for (let i = 0; i < keysArr.length; ++i) {
        const k = keysArr[i] + '';
        if (ans[k] === undefined) {
            ans[k] = valuesArr[i];
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
