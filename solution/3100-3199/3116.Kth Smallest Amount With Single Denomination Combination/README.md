# [3116. 单面值组合的第 K 小金额](https://leetcode.cn/problems/kth-smallest-amount-with-single-denomination-combination)

[English Version](/solution/3100-3199/3116.Kth%20Smallest%20Amount%20With%20Single%20Denomination%20Combination/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>coins</code> 表示不同面额的硬币，另给你一个整数 <code>k</code> 。</p>

<p>你有无限量的每种面额的硬币。但是，你<strong> 不能 </strong>组合使用不同面额的硬币。</p>

<p>返回使用这些硬币能制造的<strong> 第 </strong><code>k<sup>th</sup></code><strong> 小</strong> 金额。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;">
<p><strong>输入：</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;">coins = [3,6,9], k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;">9</span></p>

<p><strong>解释：</strong>给定的硬币可以制造以下金额：<br />
3元硬币产生3的倍数：3, 6, 9, 12, 15等。<br />
6元硬币产生6的倍数：6, 12, 18, 24等。<br />
9元硬币产生9的倍数：9, 18, 27, 36等。<br />
所有硬币合起来可以产生：3, 6, <u><strong>9</strong></u>, 12, 15等。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;">coins = [5,2], k = 7</span></p>

<p><strong>输出：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;">12</span></p>

<p><strong>解释：</strong>给定的硬币可以制造以下金额：<br />
5元硬币产生5的倍数：5, 10, 15, 20等。<br />
2元硬币产生2的倍数：2, 4, 6, 8, 10, 12等。<br />
所有硬币合起来可以产生：2, 4, 5, 6, 8, 10, <u><strong>12</strong></u>, 14, 15等。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= coins.length &lt;= 15</code></li>
	<li><code>1 &lt;= coins[i] &lt;= 25</code></li>
	<li><code>1 &lt;= k &lt;= 2 * 10<sup>9</sup></code></li>
	<li><code>coins</code> 包含两两不同的整数。</li>
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
