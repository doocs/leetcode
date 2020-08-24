# [1061. 按字典序排列最小的等效字符串](https://leetcode-cn.com/problems/lexicographically-smallest-equivalent-string)

[English Version](/solution/1000-1099/1061.Lexicographically%20Smallest%20Equivalent%20String/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给出长度相同的两个字符串：<code>A</code> 和 <code>B</code>，其中 A[i] 和 B[i] 是一组等价字符。举个例子，如果 <code>A = "abc"</code> 且 <code>B = "cde"</code>，那么就有 <code>'a' == 'c', 'b' == 'd', 'c' == 'e'</code>。</p>

<p>等价字符遵循任何等价关系的一般规则：</p>

<ul>
	<li>自反性：'a' == 'a'</li>
	<li>对称性：'a' == 'b' 则必定有 'b' == 'a'</li>
	<li>传递性：'a' == 'b' 且 'b' == 'c' 就表明 'a' == 'c'</li>
</ul>

<p>例如，<code>A</code> 和 <code>B</code> 的等价信息和之前的例子一样，那么 <code>S = "eed"</code>, <code>"acd"</code> 或 <code>"aab"</code>，这三个字符串都是等价的，而 <code>"aab"</code> 是 <code>S</code> 的按字典序最小的等价字符串</p>

<p>利用 <code>A</code> 和 <code>B</code> 的等价信息，找出并返回 <code>S</code> 的按字典序排列最小的等价字符串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>A = "parker", B = "morris", S = "parser"
<strong>输出：</strong>"makkek"
<strong>解释：</strong>根据 <code>A</code> 和 <code>B 中的等价信息，</code>我们可以将这些字符分为 <code>[m,p]</code>, <code>[a,o]</code>, <code>[k,r,s]</code>, <code>[e,i] 共 4 组</code>。每组中的字符都是等价的，并按字典序排列。所以答案是 <code>"makkek"</code>。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>A = "hello", B = "world", S = "hold"
<strong>输出：</strong>"hdld"
<strong>解释：</strong>根据 <code>A</code> 和 <code>B 中的等价信息，</code>我们可以将这些字符分为 <code>[h,w]</code>, <code>[d,e,o]</code>, <code>[l,r] 共 3 组</code>。所以只有 S 中的第二个字符 <code>'o'</code> 变成 <code>'d'，最后答案为<span style=""> </span></code><code>"hdld"</code>。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>A = "leetcode", B = "programs", S = "sourcecode"
<strong>输出：</strong>"aauaaaaada"
<strong>解释：</strong>我们可以把 A 和 B 中的等价字符分为 <code>[a,o,e,r,s,c]</code>, <code>[l,p]</code>, <code>[g,t]</code> 和 <code>[d,m] 共 4 组</code>，因此 <code>S</code> 中除了 <code>'u'</code> 和 <code>'d'</code> 之外的所有字母都转化成了 <code>'a'</code>，最后答案为 <code>"aauaaaaada"</code>。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ol>
	<li>字符串 <code>A</code>，<code>B</code> 和 <code>S</code> 仅有从 <code>'a'</code> 到 <code>'z'</code> 的小写英文字母组成。</li>
	<li>字符串 <code>A</code>，<code>B</code> 和 <code>S</code> 的长度在 <code>1</code> 到 <code>1000</code> 之间。</li>
	<li>字符串 <code>A</code> 和 <code>B</code> 长度相同。</li>
</ol>



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