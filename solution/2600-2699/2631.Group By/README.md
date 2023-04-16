# [2631. 分组](https://leetcode.cn/problems/group-by)

[English Version](/solution/2600-2699/2631.Group%20By/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一段可应用于所有数组的代码，使任何数组调用 <code>array. groupBy(fn)</code> 方法时，它返回对该数组 <strong>分组后</strong> 的结果。</p>

<p>数组 <strong>分组</strong> 是一个对象，其中的每个键都是 <code>fn(arr[i])</code> 的输出的一个数组，该数组中含有原数组中具有该键的所有项。</p>

<p>提供的回调函数 <code>fn</code> 将接受数组中的项并返回一个字符串类型的键。</p>

<p>每个值列表的顺序应该与元素在数组中出现的顺序相同。任何顺序的键都是可以接受的。</p>

<p>请在不使用 lodash 的&nbsp;<code>_.groupBy</code> 函数的前提下解决这个问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>
array = [
&nbsp; {"id":"1"},
&nbsp; {"id":"1"},
&nbsp; {"id":"2"}
], 
fn = function (item) { 
&nbsp; return item.id; 
}
<b>输出：</b>
{ 
&nbsp; "1": [{"id": "1"}, {"id": "1"}], &nbsp; 
&nbsp; "2": [{"id": "2"}] 
}
<strong>解释：</strong>
输出来自函数 array.groupBy(fn)。
分组选择方法是从数组中的每个项中获取 "id" 。
有两个 "id" 为 1 的对象。所以将这两个对象都放在第一个数组中。
有一个 "id" 为 2 的对象。所以该对象被放到第二个数组中。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>
array = [
&nbsp; [1, 2, 3],
&nbsp; [1, 3, 5],
&nbsp; [1, 5, 9]
]
fn = function (list) { 
&nbsp; return String(list[0]); 
}
<b>输出：</b>
{ 
&nbsp; "1": [[1, 2, 3], [1, 3, 5], [1, 5, 9]] 
}
<strong>解释：</strong>
数组可以是任何类型的。在本例中，分组选择方法是将键定义为数组中的第一个元素。
所有数组的第一个元素都是1，所以它们被组合在一起。
{
  "1": [[1, 2, 3], [1, 3, 5], [1, 5, 9]]
}
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输出：</b>
array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
fn = function (n) { 
&nbsp; return String(n &gt; 5);
}
<strong>输入：</strong>
{
&nbsp; "true": [6, 7, 8, 9, 10],
&nbsp; "false": [1, 2, 3, 4, 5]
}
<strong>解释：</strong>
分组选择方法是根据每个数字是否大于 5 来分割数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= array.length &lt;= 10<sup>5</sup></code></li>
	<li><code>fn 返回一个字符串</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
declare global {
    interface Array<T> {
        groupBy(fn: (item: T) => string): Record<string, T[]>;
    }
}

Array.prototype.groupBy = function (fn) {
    return this.reduce((acc, item) => {
        const key = fn(item);
        if (acc[key]) {
            acc[key].push(item);
        } else {
            acc[key] = [item];
        }
        return acc;
    }, {});
};

/**
 * [1,2,3].groupBy(String) // {"1":[1],"2":[2],"3":[3]}
 */
```

### **...**

```

```

<!-- tabs:end -->
