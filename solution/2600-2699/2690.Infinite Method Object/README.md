# [2690. 无穷方法对象 🔒](https://leetcode.cn/problems/infinite-method-object)

[English Version](/solution/2600-2699/2690.Infinite%20Method%20Object/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一个函数，返回一个 <strong>无穷方法对象</strong> 。</p>

<p><strong>无穷方法对象</strong> 被定义为一个对象，它允许您调用任何方法，并始终返回方法的名称。</p>

<p>例如，如果执行 <code>obj.abc123()</code> ，它将返回 <code>"abc123"</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>method = "abc123"
<b>输出：</b>"abc123"
<strong>解释：</strong>
const obj = createInfiniteObject();
obj['abc123'](); // "abc123"
返回的字符串应始终与方法名称匹配。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>method = ".-qw73n|^2It"
<strong>输出：</strong>".-qw73n|^2It"
<b>解释：</b>返回的字符串应始终与方法名称匹配。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= method.length &lt;= 1000</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```ts
function createInfiniteObject(): Record<string, () => string> {
    return new Proxy(
        {},
        {
            get: (_, prop) => () => prop.toString(),
        },
    );
}

/**
 * const obj = createInfiniteObject();
 * obj['abc123'](); // "abc123"
 */
```

<!-- tabs:end -->

<!-- end -->
