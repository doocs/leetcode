# [2506. 统计相似字符串对的数目](https://leetcode.cn/problems/count-pairs-of-similar-strings)

[English Version](/solution/2500-2599/2506.Count%20Pairs%20Of%20Similar%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串数组 <code>words</code> 。</p>

<p>如果两个字符串由相同的字符组成，则认为这两个字符串 <strong>相似</strong> 。</p>

<ul>
	<li>例如，<code>"abca"</code> 和 <code>"cba"</code> 相似，因为它们都由字符 <code>'a'</code>、<code>'b'</code>、<code>'c'</code> 组成。</li>
	<li>然而，<code>"abacba"</code> 和 <code>"bcfd"</code> 不相似，因为它们不是相同字符组成的。</li>
</ul>

<p>请你找出满足字符串&nbsp;<code>words[i]</code><em> </em>和<em> </em><code>words[j]</code> 相似的下标对<em> </em><code>(i, j)</code><em> </em>，并返回下标对的数目，其中 <code>0 &lt;= i &lt; j &lt;= word.length - 1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>words = ["aba","aabb","abcd","bac","aabc"]
<strong>输出：</strong>2
<strong>解释：</strong>共有 2 对满足条件：
- i = 0 且 j = 1 ：words[0] 和 words[1] 只由字符 'a' 和 'b' 组成。 
- i = 3 且 j = 4 ：words[3] 和 words[4] 只由字符 'a'、'b' 和 'c' 。 
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>words = ["aabb","ab","ba"]
<strong>输出：</strong>3
<strong>解释：</strong>共有 3 对满足条件：
- i = 0 且 j = 1 ：words[0] 和 words[1] 只由字符 'a' 和 'b' 组成。 
- i = 0 且 j = 2 ：words[0] 和 words[2] 只由字符 'a' 和 'b' 组成。 
- i = 1 且 j = 2 ：words[1] 和 words[2] 只由字符 'a' 和 'b' 组成。 
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>words = ["nba","cba","dba"]
<strong>输出：</strong>0
<strong>解释：</strong>不存在满足条件的下标对，返回 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成</li>
</ul>

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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
