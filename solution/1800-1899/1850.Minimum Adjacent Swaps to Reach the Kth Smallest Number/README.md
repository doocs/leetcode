# [1850. 邻位交换的最小次数](https://leetcode.cn/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number)

[English Version](/solution/1800-1899/1850.Minimum%20Adjacent%20Swaps%20to%20Reach%20the%20Kth%20Smallest%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个表示大整数的字符串 <code>num</code> ，和一个整数 <code>k</code> 。</p>

<p>如果某个整数是 <code>num</code> 中各位数字的一个 <strong>排列</strong> 且它的 <strong>值大于</strong> <code>num</code> ，则称这个整数为 <strong>妙数</strong> 。可能存在很多妙数，但是只需要关注 <strong>值最小</strong> 的那些。</p>

<ul>
	<li>例如，<code>num = "5489355142"</code> ：
    <ul>
    	<li>第 1 个最小妙数是 <code>"5489355214"</code></li>
    	<li>第 2 个最小妙数是 <code>"5489355241"</code></li>
    	<li>第 3 个最小妙数是 <code>"5489355412"</code></li>
    	<li>第 4 个最小妙数是 <code>"5489355421"</code></li>
    </ul>
    </li>
</ul>

<p>返回要得到第 <code>k</code> 个 <strong>最小妙数</strong> 需要对 <code>num</code> 执行的 <strong>相邻位数字交换的最小次数</strong> 。</p>

<p>测试用例是按存在第 <code>k</code> 个最小妙数而生成的。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = "5489355142", k = 4
<strong>输出：</strong>2
<strong>解释：</strong>第 4 个最小妙数是 "5489355421" ，要想得到这个数字：
- 交换下标 7 和下标 8 对应的位："5489355<strong>14</strong>2" -&gt; "5489355<strong>41</strong>2"
- 交换下标 8 和下标 9 对应的位："54893554<strong>12</strong>" -&gt; "54893554<strong>21</strong>"
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = "11112", k = 4
<strong>输出：</strong>4
<strong>解释：</strong>第 4 个最小妙数是 "21111" ，要想得到这个数字：
- 交换下标 3 和下标 4 对应的位："111<strong>12</strong>" -&gt; "111<strong>21</strong>"
- 交换下标 2 和下标 3 对应的位："11<strong>12</strong>1" -&gt; "11<strong>21</strong>1"
- 交换下标 1 和下标 2 对应的位："1<strong>12</strong>11" -&gt; "1<strong>21</strong>11"
- 交换下标 0 和下标 1 对应的位："<strong>12</strong>111" -&gt; "<strong>21</strong>111"
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>num = "00123", k = 1
<strong>输出：</strong>1
<strong>解释：</strong>第 1 个最小妙数是 "00132" ，要想得到这个数字：
- 交换下标 3 和下标 4 对应的位："001<strong>23</strong>" -&gt; "001<strong>32</strong>"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= num.length &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
	<li><code>num</code> 仅由数字组成</li>
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

### **...**

```

```

<!-- tabs:end -->
