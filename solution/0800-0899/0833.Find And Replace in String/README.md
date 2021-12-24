# [833. 字符串中的查找与替换](https://leetcode-cn.com/problems/find-and-replace-in-string)

[English Version](/solution/0800-0899/0833.Find%20And%20Replace%20in%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>某个字符串 <code>S</code> 需要执行一些替换操作，用新的字母组替换原有的字母组（不一定大小相同）。</p>

<p>每个替换操作具有 3 个参数：起始索引 <code>i</code>，源字 <code>x</code> 和目标字 <code>y</code>。规则是：如果 <code>x</code> 从<strong>原始字符串 <code>S</code></strong> 中的位置 <code>i</code> 开始，那么就用 <code>y</code> 替换出现的 <code>x</code>。如果没有，则什么都不做。</p>

<p>举个例子，如果 <code>S = “abcd”</code> 并且替换操作 <code>i = 2，x = “cd”，y = “ffff”</code>，那么因为 <code>“cd”</code> 从原始字符串 <code>S</code> 中的位置 <code>2</code> 开始，所以用 <code>“ffff”</code> 替换它。</p>

<p>再来看 <code>S = “abcd”</code> 上的另一个例子，如果一个替换操作<code> i = 0，x = “ab”，y = “eee”</code>，以及另一个替换操作 <code>i = 2，x = “ec”，y = “ffff”</code>，那么第二个操作将不会执行，因为原始字符串中 <code>S[2] = 'c'</code>，与 <code>x[0] = 'e'</code> 不匹配。</p>

<p>所有这些操作同时发生。保证在替换时不会有任何重叠： <code>S = "abc", indexes = [0, 1], sources = ["ab","bc"]</code> 不是有效的测试用例。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
<strong>输出：</strong>"eeebffff"
<strong>解释：
</strong>"a" 从 S 中的索引 0 开始，所以它被替换为 "eee"。
"cd" 从 S 中的索引 2 开始，所以它被替换为 "ffff"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
<strong>输出：</strong>"eeecd"
<strong>解释：
</strong>"ab" 从 S 中的索引 0 开始，所以它被替换为 "eee"。
"ec" 没有从<strong>原始的</strong> S 中的索引 2 开始，所以它没有被替换。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= S.length <= 1000</code></li>
	<li><code>S</code> 仅由小写英文字母组成</li>
	<li><code>0 <= indexes.length <= 100</code></li>
	<li><code>0 <= indexes[i] < S.length</code></li>
	<li><code>sources.length == indexes.length</code></li>
	<li><code>targets.length == indexes.length</code></li>
	<li><code>1 <= sources[i].length, targets[i].length <= 50</code></li>
	<li><code>sources[i]</code> 和 <code>targets[i]</code> 仅由小写英文字母组成</li>
</ul>

<p> </p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
