# [2691. 不可变辅助工具](https://leetcode.cn/problems/immutability-helper)

[English Version](/solution/2600-2699/2691.Immutability%20Helper/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>创建带有微小修改的不可变对象的克隆副本是一个繁琐的过程。请你编写一个名为 <code>ImmutableHelper</code> 的类，作为满足这一要求的工具。构造函数接受一个不可变对象 <code>obj</code> ，该对象将是一个 JSON 对象或数组。</p>

<p>该类有一个名为 <code>produce</code> 的方法，它接受一个名为 <code>mutator</code> 的函数。该函数返回一个新的对象，它与原始对象相似，但应用了这些变化。</p>

<p><code>mutator</code> 函数接受 <code>obj</code> 的 <strong>代理</strong> 版本。函数的使用者可以（看起来）对该对象进行修改，但原始对象 <code>obj</code> 实际上没有被改变。</p>

<p>例如，用户可以编写如下代码：</p>

<pre>
const originalObj = {"x": 5};
const helper = new ImmutableHelper(originalObj);
const newObj = helper.produce((proxy) =&gt; {
  proxy.x = proxy.x + 1;
});
console.log(originalObj); // {"x": 5}
console.log(newObj); // {"x": 6}</pre>

<p><code>mutator</code> 函数的属性：</p>

<ul>
	<li>它始终返回 <code>undefined</code> 。</li>
	<li>它永远不会访问不存在的键。</li>
	<li>它永远不会删除键（ <code>delete obj.key</code> ）。</li>
	<li>它永远不会在代理对象上调用方法（ <code>push</code> 、<code>shift</code> 等）。</li>
	<li>它永远不会将键设置为对象（ <code>proxy.x = {}</code> ）。</li>
</ul>

<p><strong>关于如何测试解决方案的说明：</strong>解决方案验证器仅分析返回结果与原始 <code>obj</code> 之间的差异。进行完全比较的计算开销太大。此外，对原始对象进行的任何变更都将导致答案错误。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>
obj = {"val": 10}, 
mutators = [
&nbsp; proxy =&gt; { proxy.val += 1; },
&nbsp; proxy =&gt; { proxy.val -= 1; }
]
<strong>输出：</strong>
[
  {"val": 11},
&nbsp; {"val": 9}
]
<strong>解释：</strong>
const helper = new ImmutableHelper({val: 10});
helper.produce(proxy =&gt; { proxy.val += 1; }); // { "val": 11 }
helper.produce(proxy =&gt; { proxy.val -= 1; }); // { "val": 9 }
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>
obj = {"arr": [1, 2, 3]} 
mutators = [
&nbsp;proxy =&gt; { 
&nbsp;  proxy.arr[0] = 5; 
&nbsp;  proxy.newVal = proxy.arr[0] + proxy.arr[1];
 }
]
<strong>输出：</strong>
[
  {"arr": [5, 2, 3], "newVal": 7 } 
]
<strong>解释：</strong>对原始数组进行了两次编辑。首先将数组的第一个元素设置为 5。然后添加了一个值为 7 的新键。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>
obj = {"obj": {"val": {"x": 10, "y": 20}}}
mutators = [
&nbsp; proxy =&gt; { 
&nbsp;   let data = proxy.obj.val; 
&nbsp;   let temp = data.x; 
&nbsp;   data.x = data.y; 
&nbsp;   data.y = temp; 
&nbsp; }
]
<strong>输出：</strong>
[
  {"obj": {"val": {"x": 20, "y": 10}}}
]
<strong>解释：</strong>交换了 "x" 和 "y" 的值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= JSON.stringify(obj).length &lt;= 4 * 10<sup>5</sup></code></li>
	<li><code>produce() 的总调用次数 &lt; <font face="monospace">10<sup>5</sup></font></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

<!-- tabs:end -->
