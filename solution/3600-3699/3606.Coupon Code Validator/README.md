---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3606.Coupon%20Code%20Validator/README.md
---

<!-- problem:start -->

# [3606. 优惠券校验器](https://leetcode.cn/problems/coupon-code-validator)

[English Version](/solution/3600-3699/3606.Coupon%20Code%20Validator/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你三个长度为 <code>n</code> 的数组，分别描述 <code>n</code> 个优惠券的属性：<code>code</code>、<code>businessLine</code> 和 <code>isActive</code>。其中，第 <code>i</code> 个优惠券具有以下属性：</p>

<ul>
	<li><code>code[i]</code>：一个 <strong>字符串</strong>，表示优惠券的标识符。</li>
	<li><code>businessLine[i]</code>：一个 <strong>字符串</strong>，表示优惠券所属的业务类别。</li>
	<li><code>isActive[i]</code>：一个 <strong>布尔值</strong>，表示优惠券是否当前有效。</li>
</ul>

<p>当以下所有条件都满足时，优惠券被认为是&nbsp;<strong>有效的&nbsp;</strong>：</p>

<ol>
	<li><code>code[i]</code> 不能为空，并且仅由字母数字字符（a-z、A-Z、0-9）和下划线（<code>_</code>）组成。</li>
	<li><code>businessLine[i]</code> 必须是以下四个类别之一：<code>"electronics"</code>、<code>"grocery"</code>、<code>"pharmacy"</code>、<code>"restaurant"</code>。</li>
	<li><code>isActive[i]</code> 为 <strong>true&nbsp;</strong>。</li>
</ol>

<p>返回所有&nbsp;<strong>有效优惠券的标识符&nbsp;</strong>组成的数组，按照以下规则排序：</p>

<ul>
	<li>先按照其 <strong>businessLine</strong> 的顺序排序：<code>"electronics"</code>、<code>"grocery"</code>、<code>"pharmacy"</code>、<code>"restaurant"</code>。</li>
	<li>在每个类别内，再按照 <strong>标识符的字典序（升序）</strong>排序。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">code = ["SAVE20","","PHARMA5","SAVE@20"], businessLine = ["restaurant","grocery","pharmacy","restaurant"], isActive = [true,true,true,true]</span></p>

<p><strong>输出：</strong> <span class="example-io">["PHARMA5","SAVE20"]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第一个优惠券有效。</li>
	<li>第二个优惠券的标识符为空（无效）。</li>
	<li>第三个优惠券有效。</li>
	<li>第四个优惠券的标识符包含特殊字符 <code>@</code>（无效）。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">code = ["GROCERY15","ELECTRONICS_50","DISCOUNT10"], businessLine = ["grocery","electronics","invalid"], isActive = [false,true,true]</span></p>

<p><strong>输出：</strong> <span class="example-io">["ELECTRONICS_50"]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第一个优惠券无效，因为它未激活。</li>
	<li>第二个优惠券有效。</li>
	<li>第三个优惠券无效，因为其业务类别无效。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == code.length == businessLine.length == isActive.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= code[i].length, businessLine[i].length &lt;= 100</code></li>
	<li><code>code[i]</code> 和 <code>businessLine[i]</code> 由可打印的 ASCII 字符组成。</li>
	<li><code>isActive[i]</code> 的值为 <code>true</code> 或 <code>false</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
