# [3016. 输入单词需要的最少按键次数 II](https://leetcode.cn/problems/minimum-number-of-pushes-to-type-word-ii)

[English Version](/solution/3000-3099/3016.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>word</code>，由 <strong>不同 </strong>小写英文字母组成。</p>

<p>电话键盘上的按键与 <strong>不同 </strong>小写英文字母集合相映射，可以通过按压按键来组成单词。例如，按键 <code>2</code> 对应 <code>["a","b","c"]</code>，我们需要按一次键来输入 <code>"a"</code>，按两次键来输入 <code>"b"</code>，按三次键来输入 <code>"c"</code><em>。</em></p>

<p>现在允许你将编号为 <code>2</code> 到 <code>9</code> 的按键重新映射到 <strong>不同 </strong>字母集合。每个按键可以映射到<strong> 任意数量 </strong>的字母，但每个字母 <strong>必须</strong> <strong>恰好</strong> 映射到 <strong>一个 </strong>按键上。你需要找到输入字符串 <code>word</code> 所需的<strong> 最少 </strong>按键次数。</p>

<p>返回重新映射按键后输入 <code>word</code> 所需的 <strong>最少 </strong>按键次数。</p>

<p>下面给出了一种电话键盘上字母到按键的映射作为示例。注意 <code>1</code>，<code>*</code>，<code>#</code> 和 <code>0</code> <strong>不</strong> 对应任何字母。</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3016.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20II/images/keypaddesc.png" style="width: 329px; height: 313px;" />
<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3016.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20II/images/keypadv1e1.png" style="width: 329px; height: 313px;" />
<pre>
<strong>输入：</strong>word = "abcde"
<strong>输出：</strong>5
<strong>解释：</strong>图片中给出的重新映射方案的输入成本最小。
"a" -&gt; 在按键 2 上按一次
"b" -&gt; 在按键 3 上按一次
"c" -&gt; 在按键 4 上按一次
"d" -&gt; 在按键 5 上按一次
"e" -&gt; 在按键 6 上按一次
总成本为 1 + 1 + 1 + 1 + 1 = 5 。
可以证明不存在其他成本更低的映射方案。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3016.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20II/images/keypadv2e2.png" style="width: 329px; height: 313px;" />
<pre>
<strong>输入：</strong>word = "xyzxyzxyzxyz"
<strong>输出：</strong>12
<strong>解释：</strong>图片中给出的重新映射方案的输入成本最小。
"x" -&gt; 在按键 2 上按一次
"y" -&gt; 在按键 3 上按一次
"z" -&gt; 在按键 4 上按一次
总成本为 1 * 4 + 1 * 4 + 1 * 4 = 12 。
可以证明不存在其他成本更低的映射方案。
注意按键 9 没有映射到任何字母：不必让每个按键都存在与之映射的字母，但是每个字母都必须映射到按键上。
</pre>

<p><strong class="example">示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3016.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20II/images/keypadv2.png" style="width: 329px; height: 313px;" />
<pre>
<strong>输入：</strong>word = "aabbccddeeffgghhiiiiii"
<strong>输出：</strong>24
<strong>解释：</strong>图片中给出的重新映射方案的输入成本最小。
"a" -&gt; 在按键 2 上按一次
"b" -&gt; 在按键 3 上按一次
"c" -&gt; 在按键 4 上按一次
"d" -&gt; 在按键 5 上按一次
"e" -&gt; 在按键 6 上按一次
"f" -&gt; 在按键 7 上按一次
"g" -&gt; 在按键 8 上按一次
"h" -&gt; 在按键 9 上按两次
"i" -&gt; 在按键 9 上按一次
总成本为 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 2 * 2 + 6 * 1 = 24 。
可以证明不存在其他成本更低的映射方案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 10<sup>5</sup></code></li>
	<li><code>word</code> 仅由小写英文字母组成。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
