# [2952. 需要添加的硬币的最小数量](https://leetcode.cn/problems/minimum-number-of-coins-to-be-added)

[English Version](/solution/2900-2999/2952.Minimum%20Number%20of%20Coins%20to%20be%20Added/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0 </strong>开始的整数数组 <code>coins</code>，表示可用的硬币的面值，以及一个整数 <code>target</code> 。</p>

<p>如果存在某个 <code>coins</code> 的子序列总和为 <code>x</code>，那么整数 <code>x</code> 就是一个 <strong>可取得的金额 </strong>。</p>

<p>返回需要添加到数组中的<strong> 任意面值 </strong>硬币的 <strong>最小数量 </strong>，使范围 <code>[1, target]</code> 内的每个整数都属于 <strong>可取得的金额</strong> 。</p>

<p>数组的 <strong>子序列</strong> 是通过删除原始数组的一些（<strong>可能不删除</strong>）元素而形成的新的 <strong>非空</strong> 数组，删除过程不会改变剩余元素的相对位置。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>coins = [1,4,10], target = 19
<strong>输出：</strong>2
<strong>解释：</strong>需要添加面值为 2 和 8 的硬币各一枚，得到硬币数组 [1,2,4,8,10] 。
可以证明从 1 到 19 的所有整数都可由数组中的硬币组合得到，且需要添加到数组中的硬币数目最小为 2 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>coins = [1,4,10,5,7,19], target = 19
<strong>输出：</strong>1
<strong>解释：</strong>只需要添加一枚面值为 2 的硬币，得到硬币数组 [1,2,4,5,7,10,19] 。
可以证明从 1 到 19 的所有整数都可由数组中的硬币组合得到，且需要添加到数组中的硬币数目最小为 1 。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>coins = [1,1,1], target = 20
<strong>输出：</strong>3
<strong>解释：</strong>
需要添加面值为 4 、8 和 16 的硬币各一枚，得到硬币数组 [1,1,1,4,8,16] 。 
可以证明从 1 到 20 的所有整数都可由数组中的硬币组合得到，且需要添加到数组中的硬币数目最小为 3 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= coins.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= coins[i] &lt;= target</code></li>
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
