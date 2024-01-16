# [2633. 将对象转换为 JSON 字符串](https://leetcode.cn/problems/convert-object-to-json-string)

[English Version](/solution/2600-2699/2633.Convert%20Object%20to%20JSON%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现给定一个值，返回该值的有效 JSON 字符串。你可以假设这个值只包括字符串、整数、数组、对象、布尔值和 null。返回的字符串不能包含额外的空格。键的返回顺序应该与 <code>Object.keys()</code> 的顺序相同。</p>

<p>请你在不使用内置方法 <code>JSON.stringify</code> 的前提下解决这个问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>object = {"y":1,"x":2}
<strong>输出：</strong>{"y":1,"x":2}
<strong>解释：</strong>
返回该对象的 JSON 表示形式。
注意，键的返回顺序应该与 Object.keys() 的顺序相同。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>object = {"a":"str","b":-12,"c":true,"d":null}
<strong>输出：</strong>{"a":"str","b":-12,"c":true,"d":null}
<strong>解释：</strong>
JSON 的基本类型是字符串、数字型、布尔值和 null。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>object = {"key":{"a":1,"b":[{},null,"Hello"]}}
<strong>输出：</strong>{"key":{"a":1,"b":[{},null,"Hello"]}}
<strong>解释：</strong>
对象和数组可以包括其他对象和数组。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>object = true
<strong>输出：</strong>true
<strong>解释：</strong>
基本类型是有效的输入</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>value</code>&nbsp;是一个有效的 JSON 值</li>
	<li><code>1 &lt;= JSON.stringify(object).length &lt;= 10<sup>5</sup></code></li>
	<li><code>maxNestingLevel &lt;= 1000</code></li>
	<li>所有字符串只包含字母数字字符</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```ts
function jsonStringify(object: any): string {
    if (object === null) {
        return 'null';
    }
    if (typeof object === 'string') {
        return `"${object}"`;
    }
    if (typeof object === 'number' || typeof object === 'boolean') {
        return object.toString();
    }
    if (Array.isArray(object)) {
        return `[${object.map(jsonStringify).join(',')}]`;
    }
    if (typeof object === 'object') {
        return `{${Object.entries(object)
            .map(([key, value]) => `${jsonStringify(key)}:${jsonStringify(value)}`)
            .join(',')}}`;
    }
    return '';
}
```

<!-- tabs:end -->

<!-- end -->
